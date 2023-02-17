package manager;

import com.google.gson.Gson;
import exceptions.ManagerSaveException;
import interfaces.HistoryManager;
import interfaces.TaskManager;
import tasks.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FileBackedTasksManager extends InMemoryTaskManager {
    static String pathFile = "test.csv";

    public FileBackedTasksManager(String pathFile) {
    this.pathFile = pathFile;
    }

    public FileBackedTasksManager() {
    }

    private String toString(Task task) {
        return task.toString();
    }
    private static Task fromString(String value) {
        String[] lineContents = value.split(",");
        switch (Type.valueOf(lineContents[1])) {
            case TASK:
                Task task = new Task(lineContents[2],lineContents[6],Integer.parseInt(lineContents[4]),lineContents[5]);
            //    Task task = new Task(lineContents[2],lineContents[6],Integer.parseInt(lineContents[4]));
                task.setId(Integer.parseInt(lineContents[0]));
                task.setStatus(Status.valueOf(lineContents[3]));
                return task;
            case EPIC:
                Task epic = new Epic(lineContents[2],lineContents[6],Integer.parseInt(lineContents[4]),lineContents[5]);
                epic.setId(Integer.parseInt(lineContents[0]));
                epic.setStatus(Status.valueOf(lineContents[3]));
                return epic;
            case SUBTASK:
                Task subtask = new Subtask(lineContents[2],lineContents[6],Integer.parseInt(lineContents[7]), Integer.parseInt(lineContents[4]),lineContents[5]);
                subtask.setId(Integer.parseInt(lineContents[0]));
                subtask.setStatus(Status.valueOf(lineContents[3]));
                return subtask;
        }
        return null;
    }
    void save() {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(pathFile))){
            String[] lineContents = pathFile.split(",");
            if(!lineContents[0].contains("id")) {
                fileWriter.write("id,type,name,status,duration,startTime,description,epic\n");
            }
            for(Integer key : getHmTask().keySet()) {
                fileWriter.write(toString(getHmTask().get(key)));
            }
            for(Integer key : getHmEpic().keySet()) {
                fileWriter.write(toString(getHmEpic().get(key)));
            }
            for(Integer key : getHmSubtask().keySet()) {
                fileWriter.write(toString(getHmSubtask().get(key)));
            }
            fileWriter.write("\n");
            fileWriter.write(historyToString(historyManager));
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка", e);
        }
    }
    static String historyToString(HistoryManager manager) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < manager.getHistory().size(); i++) {
            sb.append(manager.getHistory().get(i).getId());
            if (i < manager.getHistory().size()-1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    List<Integer> historyFromString(String value) {
        String[] history = value.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < history.length; i++) {
            Integer idHistory = Integer.parseInt(history[i]);
            list.add(idHistory);
            if(getHmTask().containsKey(idHistory)) {
                historyManager.add(getHmTask().get(idHistory));
            }
            if(getHmEpic().containsKey(idHistory)) {
                historyManager.add(getHmEpic().get(idHistory));
            }
            if(getHmSubtask().containsKey(idHistory)) {
                historyManager.add(getHmSubtask().get(idHistory));
            }
        }
        return list;
    }
    static FileBackedTasksManager loadFromFile(String pathFile) throws IOException {
        FileBackedTasksManager newManager = new FileBackedTasksManager(pathFile);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile));
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if(line.equals("")) {
                    String lineLast = bufferedReader.readLine();
                    if(lineLast.equals("")) {
                        break;
                    } else {
                        newManager.historyFromString(lineLast);
                        break;
                    }
                }
                if(line.contains("id")) {
                    continue;
                }
                if(fromString(line).getType().equals(Type.TASK)) {
                    newManager.getHmTask().put(fromString(line).getId(),fromString(line));
                }
                if(fromString(line).getType().equals(Type.EPIC)) {
                    newManager.getHmEpic().put(fromString(line).getId(),(Epic)fromString(line));
                }
                if(fromString(line).getType().equals(Type.SUBTASK)) {
                    newManager.getHmSubtask().put(fromString(line).getId(),(Subtask)fromString(line));
                }
            }
        return newManager;
    }
    @Override
    public int createTask(Task task) {
        int returnCreateTask = super.createTask(task);
    //    treeSetPrioritized.add(task);
        save();
        return returnCreateTask;
    }
    @Override
    public int createEpic(Epic epic) {
        int returnCreateEpic = super.createEpic(epic);
        save();
        return returnCreateEpic;
    }
    @Override
    public int createSubtask(Subtask subtask) {
        int returnCreateSubtask = super.createSubtask(subtask);
    //    treeSetPrioritized.add(subtask);
        save();
        return returnCreateSubtask;
    }
    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }
    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }
    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }
    @Override
    public Task getTaskById(Integer id) {
        Task task = super.getTaskById(id);
        save();
        return task;
    }
    @Override
    public Epic getEpicById(Integer id) {
        Epic epic = super.getEpicById(id);
        save();
        return epic;
    }
    @Override
    public Subtask getSubtaskById(Integer id) {
        Subtask subtask = super.getSubtaskById(id);
        save();
        return subtask;
    }
    @Override
    public void deleteTaskById(Integer id) {
        super.deleteTaskById(id);
        save();
    }
    @Override
    public void deleteEpicById(Integer id) {
        super.deleteEpicById(id);
        save();
    }
    @Override
    public void deleteSubtaskById(Integer id) {
        super.deleteSubtaskById(id);
        save();
    }

    public static void main(String[] args){

        FileBackedTasksManager manager = new FileBackedTasksManager(pathFile);

        Task task11 = new Task("Название1","Описание1",150,"2023-03-01 10:00");
        manager.createTask(task11);

        Task task51 = new Task("Название5","Описание5",50,"2023-03-01 16:00");
        manager.createTask(task51);

    /*    Task task31 = new Task("Название5","Описание5",50,"2023-03-01 12:00");
        manager.createTask(task31);

        Task task21 = new Task("Название555","Описание555",50);
        manager.createTask(task21);

        Epic epic11 = new Epic("Эпик1", "Описание епика1");
        manager.createEpic(epic11);

        Epic epic10 = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic10);

        Subtask subtask20 = new Subtask("Сабтаск эпика", "Описание сабтаска", epic10.getId(),50,"2023-03-03 12:00");
        manager.createSubtask(subtask20);

        Subtask subtask23 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic10.getId(),50,"2023-03-03 10:00");
        manager.createSubtask(subtask23);*/

        manager.getTaskById(0);
        manager.getTaskById(1);

   /*     Gson gson = new Gson();
        String jsonString = gson.toJson(manager.getListTasks());
        System.out.println(jsonString);*/

        TaskManager newManager = null;
        try {
            newManager = loadFromFile(pathFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(newManager.getHistory());
    }
}


