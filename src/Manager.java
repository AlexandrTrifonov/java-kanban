import java.util.HashMap;

import tasks.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;

public class Manager {
    private Integer id = 0;
//    Integer x = 0;
    private final HashMap <Integer, Task> hmTask = new HashMap<>();
    private final HashMap <Integer, Epic> hmEpic = new HashMap<>();
    private final HashMap <Integer, Subtask> hmSubtask = new HashMap<>();

    public void deleteTasks() {
        hmTask.clear();
    }
    public void deleteTaskId(Integer id) {
        hmTask.remove(id);
    }
    public void deleteEpic(Integer id) {
        for (Integer z : hmEpic.get(id).idSubtask) {
            hmSubtask.remove(z);
        }
        hmEpic.remove(id);
    }
    public void printSubtaskOfEpic(Integer idEpic) {
        System.out.println("Список подзадач эпика с id =" + idEpic + ":");
        for (Integer z : hmEpic.get(idEpic).idSubtask) {
            System.out.println(hmSubtask.get(z));
        }
    }
    public void getTaskId(Integer id) {
        Task task = hmTask.get(id);
        System.out.println(task);
    }
    public void createTask(Task task) {
        hmTask.put(id,task);
        this.id ++;
    }
    public void createEpic(Epic epic) {
        hmEpic.put(id,epic);
        this.id ++;
    }
    public void createSubtask(Subtask subtask) {
        hmSubtask.put(id,subtask);
    //    x = subtask.idEpic;
        hmEpic.get(subtask.getIdEpic()).idSubtask.add(id);
        this.id ++;
    }
    public void updateTask(Integer id, Task task) {
        hmTask.put(id,task);
    }
    public void updateSubtask(Integer id, Subtask subtask) {
        hmSubtask.put(id,subtask);
    //    x = subtask.idEpic;
        int size = hmEpic.get(subtask.getIdEpic()).idSubtask.size();
        int countnew = 0;
        int countdone = 0;
        for (int i=0; i<size; i++) {
            Integer zz = hmEpic.get(subtask.getIdEpic()).idSubtask.get(i);
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

    /*    public void printTask() {
        System.out.println("Список всех задач:");
        for (Task tasks : hmTask.values()) {
            System.out.println(tasks);
        }
    }
    public void printEpic() {
        System.out.println("Список всех эпиков:");
        for (Epic epics : hmEpic.values()) {
            System.out.println(epics);
        }
    }
    public void printSubtask() {
        System.out.println("Список всех подзадач:");
        for (Subtask subtasks : hmSubtask.values()) {
            System.out.println(subtasks);
        }
    }*/
}
