import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import interfaces.HistoryManager;
import interfaces.TaskManager;
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

        Task task1 = new Task("Название1","Описание1");
        manager.createTask(task1);

        Task task5 = new Task("Название5","Описание5");
        manager.createTask(task5);

        Epic epic1 = new Epic("Эпик1", "Описание епика1");
        manager.createEpic(epic1);

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);

        Subtask subtask = new Subtask("Сабтаск эпика", "Описание сабтаска", epic.getId());
        manager.createSubtask(subtask);

        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId());
        manager.createSubtask(subtask3);

        Integer idEpicForSubtask1 = epic.getId();
        Subtask subtask1 = new Subtask("Сабтаск1 эпика", "Описание сабтаска1", idEpicForSubtask1);
        manager.createSubtask(subtask1);

        manager.getTaskById(task1.getId());
        manager.getTaskById(task5.getId());
        manager.getEpicById(epic.getId());
        manager.getEpicById(epic1.getId());
        manager.getEpicById(epic.getId());
        manager.getSubtaskById(subtask.getId());
        manager.getSubtaskById(subtask3.getId());
        manager.getSubtaskById(subtask1.getId());
        manager.getSubtaskById(subtask.getId());

        System.out.println(manager.getHistory());
        manager.deleteTaskById(task1.getId());
        manager.deleteSubtaskById(subtask1.getId());
        manager.deleteEpicById(epic.getId());
        System.out.println(manager.getHistory());
    }
}
