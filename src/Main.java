import java.util.ArrayList;
import java.util.Collection;

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

        Task task = new Task("Название","Описание",Status.NEW);
        manager.createTask(task);

        Task task1 = new Task("Название1","Описание1",Status.NEW);
        manager.createTask(task1);

        Task task5 = new Task("Название5","Описание5",Status.NEW);
        manager.createTask(task5);

        Task task6 = new Task("Название6","Описание6",Status.NEW);
        manager.createTask(task6);

        Task task7 = new Task("Название7","Описание7",Status.NEW);
        manager.createTask(task7);

        Task task8 = new Task("Название8","Описание8",Status.NEW);
        manager.createTask(task8);

        ArrayList <Integer> idSubtask = new ArrayList<>();
        Epic epic = new Epic("Название епика", "Описание епика", Status.NEW, idSubtask);
        manager.createEpic(epic);

        ArrayList <Integer> idSubtask1 = new ArrayList<>();
        Epic epic1 = new Epic("Название епика1", "Описание епика1", Status.NEW, idSubtask1);
        manager.createEpic(epic1);

        Integer idEpicForSubtask = epic.getId();
        Subtask subtask = new Subtask("Название сабтаска", "Описание сабтаска", Status.NEW, idEpicForSubtask);
        manager.createSubtask(subtask);

        Integer idEpicForSubtask1 = epic.getId();
        Subtask subtask1 = new Subtask("Название сабтаска1", "Описание сабтаска1", Status.NEW, idEpicForSubtask1);
        manager.createSubtask(subtask1);

        Integer idEpicForSubtask2 = epic1.getId();
        Subtask subtask2 = new Subtask("Название сабтаска2", "Описание сабтаска2", Status.NEW, idEpicForSubtask2);
        manager.createSubtask(subtask2);

        // получение по id
        manager.getTaskById(task1.getId());
        System.out.println(manager.getHistory());

        manager.getTaskById(task1.getId());
        System.out.println(manager.getHistory());

        manager.getTaskById(task1.getId());
        System.out.println(manager.getHistory());

        manager.getTaskById(task1.getId());
        System.out.println(manager.getHistory());

        manager.getTaskById(task1.getId());
        System.out.println(manager.getHistory());

        manager.getTaskById(task8.getId());
        System.out.println(manager.getHistory());

        manager.getEpicById(epic1.getId());
        System.out.println(manager.getHistory());

        manager.getTaskById(task7.getId());
        System.out.println(manager.getHistory());

        manager.getSubtaskById(subtask2.getId());
        System.out.println(manager.getHistory());

        manager.getTaskById(task6.getId());
        System.out.println(manager.getHistory());

        manager.getTaskById(task6.getId());
        System.out.println(manager.getHistory());

        manager.getTaskById(task6.getId());
        System.out.println(manager.getHistory());
    }
}
