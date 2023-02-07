import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import interfaces.HistoryManager;
import interfaces.TaskManager;
import manager.FileBackedTasksManager;
import manager.InMemoryHistoryManager;
import manager.Managers;
import tasks.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;
import manager.InMemoryTaskManager;

public class Main {
    public static void main(String[] args) {

        TaskManager manager = Managers.getDefault();

        Task task11 = new Task("Название1","Описание1",150,"2023-03-01 14:00");
        manager.createTask(task11);

        Task task51 = new Task("Название5","Описание5",50,"2023-03-01 14:00");
        manager.createTask(task51);

        Task task31 = new Task("Название5","Описание5",50,"2023-03-01 12:00");
        manager.createTask(task31);

        Task task21 = new Task("Название5","Описание5",50);
        manager.createTask(task21);

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);

        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-01 18:10");
        manager.createSubtask(subtask3);

        System.out.println(manager.getPrioritizedTasks());
    }
}
