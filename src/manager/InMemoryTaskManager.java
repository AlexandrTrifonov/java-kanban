package manager;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import interfaces.HistoryManager;
import interfaces.TaskManager;
import tasks.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;

public class InMemoryTaskManager implements TaskManager {
    private Integer id;
    private final HashMap <Integer, Task> hmTask;
    private final HashMap <Integer, Epic> hmEpic;
    private final HashMap <Integer, Subtask> hmSubtask;

    public HashMap<Integer, Task> getHmTask() {
        return hmTask;
    }

    public HashMap<Integer, Epic> getHmEpic() {
        return hmEpic;
    }

    public HashMap<Integer, Subtask> getHmSubtask() {
        return hmSubtask;
    }

    public InMemoryTaskManager() {
        id=0;
        hmTask = new HashMap<>();
        hmEpic = new HashMap<>();
        hmSubtask = new HashMap<>();
    }
    HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public Task getTaskById(Integer id) {
        Task task = hmTask.get(id);
        historyManager.add(task);
        return task;
    }
    @Override
    public Epic getEpicById(Integer id) {
        Epic epic = hmEpic.get(id);
        historyManager.add(epic);
        return epic;
    }
    @Override
    public Subtask getSubtaskById(Integer id) {
        Subtask subtask = hmSubtask.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    @Override
    public Collection<Task> getListTasks() {
        return hmTask.values();
    }
    @Override
    public Collection<Epic> getListEpics() {
        return hmEpic.values();
    }
    @Override
    public Collection<Subtask> getListSubtasks() {
        return hmSubtask.values();
    }
    @Override
    public void deleteTasks() {
        hmTask.clear();
    }
    @Override
    public void deleteEpics() {
        hmEpic.clear();
        hmSubtask.clear();
    }
    @Override
    public void deleteSubtasks() {
        hmSubtask.clear();
        for (Integer key : hmEpic.keySet()) {
            hmEpic.get(key).getIdSubtask().clear();
        }
    }

    @Override
    public int createTask(Task task) {
        task.setId(id);
        hmTask.put(task.getId(),task);
        this.id ++;
        return id;
    }
    @Override
    public int createEpic(Epic epic) {
        epic.setId(id);
        hmEpic.put(epic.getId(),epic);
        this.id ++;
        return id;
    }
    @Override
    public int createSubtask(Subtask subtask) {
        subtask.setId(id);
        hmSubtask.put(subtask.getId(),subtask);
        hmEpic.get(subtask.getIdEpic()).getIdSubtask().add(id);
        this.id ++;
        return id;
    }
    @Override
    public void updateTask(Task task) {
        hmTask.put(task.getId(),task);
    }
    @Override
    public void updateEpic(Epic epic) {
        Integer idEpicToUpdate = epic.getId();
        ArrayList <Integer> oldIdSubtask = hmEpic.get(idEpicToUpdate).getIdSubtask();
        epic.setIdSubtask(oldIdSubtask);
        hmTask.put(epic.getId(),epic);
    }
    @Override
    public void updateSubtask(Subtask subtask) {
        hmSubtask.put(subtask.getId(),subtask);
        int size = hmEpic.get(subtask.getIdEpic()).getIdSubtask().size();
        int countnew = 0;
        int countdone = 0;
        for (int i=0; i<size; i++) {
            Integer zz = hmEpic.get(subtask.getIdEpic()).getIdSubtask().get(i);
            if (hmSubtask.get(zz).getStatus().equals(Status.NEW)){
                countnew ++;
            } else if (hmSubtask.get(zz).getStatus().equals(Status.DONE)){
                countdone ++;
            }
        }
        Epic epic = hmEpic.get(subtask.getIdEpic());
        if (size==countnew) {
            epic.setStatus(Status.NEW);
        } else if (size==countdone) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
        hmEpic.put(subtask.getIdEpic(),epic);
    }
    @Override
    public void deleteTaskById(Integer id) {
        hmTask.remove(id);
        historyManager.remove(id);
    }
    @Override
    public void deleteEpicById(Integer id) {
        ArrayList<Integer> idSubtaskEpic = hmEpic.get(id).getIdSubtask();
        for (int i = 0; i < idSubtaskEpic.size(); i++) {
            hmSubtask.remove(idSubtaskEpic.get(i));
            Integer idSubtask = idSubtaskEpic.get(i);
            historyManager.remove(idSubtask);
        }
        hmEpic.remove(id);
        historyManager.remove(id);
    }
    @Override
    public void deleteSubtaskById(Integer id) {
        int i=0;
        Integer idEpic = hmSubtask.get(id).getIdEpic();
        for (Integer idSubtask : hmEpic.get(idEpic).getIdSubtask()) {
            if (idSubtask.equals(id)) {
                hmEpic.get(idEpic).getIdSubtask().remove(i);
                break;
            } else {
                i++;
            }
        }
        hmSubtask.remove(id);
        historyManager.remove(id);
    }

    @Override
    public ArrayList<Subtask> getSubtasksByEpic(Integer id) {
        ArrayList<Subtask> listSubtasks = new ArrayList<>();
        for (Subtask subtask : hmSubtask.values()) {
            if (subtask.getIdEpic().equals(id)) {
                listSubtasks.add(subtask);
            }
        }
        return listSubtasks;
    }
}
