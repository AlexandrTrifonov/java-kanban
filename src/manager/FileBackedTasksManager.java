package manager;

import exceptions.ManagerSaveException;
import interfaces.HistoryManager;
import interfaces.TaskManager;
import tasks.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class FileBackedTasksManager extends InMemoryTaskManager {
    static String pathFile = "test.csv";

    public FileBackedTasksManager(String pathFile) {
    this.pathFile = pathFile;
    }

    public String toString(Task task) {
        return task.toString();
    }
    public static Task fromString(String value) {
        String[] lineContents = value.split(",");
        switch (Type.valueOf(lineContents[1])) {
            case TASK:
                Task task = new Task(lineContents[2],lineContents[4]);
                task.setId(Integer.parseInt(lineContents[0]));
                task.setStatus(Status.valueOf(lineContents[3]));
                return task;
            case EPIC:
                Task epic = new Epic(lineContents[2],lineContents[4]);
                epic.setId(Integer.parseInt(lineContents[0]));
                epic.setStatus(Status.valueOf(lineContents[3]));
                return epic;
            case SUBTASK:
                Task subtask = new Subtask(lineContents[2],lineContents[4],Integer.parseInt(lineContents[5]));
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
                fileWriter.write("id,type,name.status.description,epic\n");
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
                    newManager.historyFromString(lineLast);
                    break;
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
        save();
        return returnCreateSubtask;
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

    public static void main(String[] args){ // в ТЗ указали, что проверку выполнить в мейне данного класса

        FileBackedTasksManager managerFileBack = new FileBackedTasksManager(pathFile);

        Task task11 = new Task("Название1","Описание1");
        managerFileBack.createTask(task11);

        Task task51 = new Task("Название5","Описание5");
        managerFileBack.createTask(task51);

        Epic epic11 = new Epic("Эпик1", "Описание епика1");
        managerFileBack.createEpic(epic11);

        Epic epic10 = new Epic("Эпик", "Описание епика");
        managerFileBack.createEpic(epic10);

        Subtask subtask20 = new Subtask("Сабтаск эпика", "Описание сабтаска", epic10.getId());
        managerFileBack.createSubtask(subtask20);

        Subtask subtask23 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic10.getId());
        managerFileBack.createSubtask(subtask23);

        Integer idEpicForSubtask10 = epic10.getId();
        Subtask subtask21 = new Subtask("Сабтаск1 эпика", "Описание сабтаска1", idEpicForSubtask10);
        managerFileBack.createSubtask(subtask21);

        managerFileBack.getTaskById(task11.getId());
        managerFileBack.getTaskById(task51.getId());
        managerFileBack.getEpicById(epic10.getId());
        managerFileBack.getEpicById(epic11.getId());
        managerFileBack.getEpicById(epic10.getId());
        managerFileBack.getSubtaskById(subtask20.getId());
        managerFileBack.getSubtaskById(subtask23.getId());
        managerFileBack.getSubtaskById(subtask21.getId());
        managerFileBack.getSubtaskById(subtask20.getId());

        managerFileBack.deleteTaskById(task11.getId());
        managerFileBack.deleteSubtaskById(subtask21.getId());
        managerFileBack.deleteEpicById(epic11.getId());

        TaskManager newManager = null;
        try {
            newManager = loadFromFile(pathFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(newManager.getHistory());
    }
}


