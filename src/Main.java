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

    /*    FileBackedTasksManager managerFileBack = new FileBackedTasksManager("test.csv");

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
        managerFileBack.deleteEpicById(epic11.getId());*/



    /*    TaskManager manager = Managers.getDefault();

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

    //    System.out.println(manager.getHistory());
        manager.deleteTaskById(task1.getId());
        manager.deleteSubtaskById(subtask1.getId());
        manager.deleteEpicById(epic.getId());
        System.out.println(manager.getHistory());*/
    }
}
