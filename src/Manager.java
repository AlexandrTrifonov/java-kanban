import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    Integer id = 0;
    Integer x = 0;
    HashMap <Integer, Task> hmTask = new HashMap<>();
    HashMap <Integer, Epic> hmEpic = new HashMap<>();
    HashMap <Integer, Subtask> hmSubtask = new HashMap<>();



    public void printTask() {
        System.out.println("Список всех задач:");
        for (Task tasks : hmTask.values()) {
            System.out.println(tasks.name);
            System.out.println(tasks.status);
        }
    }
    public void printEpic() {
        System.out.println("Список всех эпиков:");
        for (Epic epics : hmEpic.values()) {
            System.out.println(epics.name);
            System.out.println(epics.status);
        }
    }
    public void printSubtask() {
        System.out.println("Список всех подзадач:");
        for (Subtask subtask : hmSubtask.values()) {
            System.out.println(subtask.name);
            System.out.println(subtask.status);
            System.out.println("id эпика: " + subtask.idEpic);
        }
    }
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
            System.out.println(hmSubtask.get(z).name);
        }
    }
    public void getTaskId(Integer id) {
        System.out.println("Номер" + id + " Название: " + hmTask.get(id).name + " Описание: " + hmTask.get(id).discription + " Статус: " + hmTask.get(id).status);
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
        x = subtask.idEpic;
        hmEpic.get(x).idSubtask.add(id);
        this.id ++;
    }
    public void updateTask(Integer id, Task task) {
        hmTask.put(id,task);
    }
    public void updateSubtask(Integer id, Subtask subtask) {
        hmSubtask.put(id,subtask);
        x = subtask.idEpic;
        int size = hmEpic.get(x).idSubtask.size();
        int countnew = 0;
        int countdone = 0;
        for (int i=0; i<size; i++) {
            Integer zz = hmEpic.get(x).idSubtask.get(i);
            if (hmSubtask.get(zz).status.equals("new")){
                countnew ++;
            } else if (hmSubtask.get(zz).status.equals("done")){
                countdone ++;
            }
        }
        Epic epic = hmEpic.get(x);
        if (size==countnew) {
            epic.status = "new";
        } else if (size==countdone) {
            epic.status = "done";
        } else {
            epic.status = "in progress";
        }
        hmEpic.put(x,epic);
    }
}
