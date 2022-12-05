import java.util.ArrayList;

import tasks.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        Task task = new Task();
        task.setName("Название");
        task.setDescription("Описание");
        task.setStatus(Status.NEW);
        manager.createTask(task);

        Task task1 = new Task();
        task1.setName("Название1");
        task1.setDescription("Описание1");
        task1.setStatus(Status.NEW);
        manager.createTask(task1);

        ArrayList <Integer> idSubtask = new ArrayList<>();
        Epic epic = new Epic();
        epic.setName("Название епика");
        epic.setDescription("Описание епика");
        epic.setStatus(Status.NEW);
        epic.setIdSubtask(idSubtask);
        manager.createEpic(epic);

        ArrayList <Integer> idSubtask1 = new ArrayList<>();
        Epic epic1 = new Epic();
        epic1.setName("Название епика");
        epic1.setDescription("Описание епика");
        epic1.setStatus(Status.NEW);
        epic1.setIdSubtask(idSubtask1);
        manager.createEpic(epic1);

        Subtask subtask = new Subtask();
        subtask.setName("Название сабтаска");
        subtask.setDescription("Описание сабтаска");
        subtask.setStatus(Status.NEW);
        subtask.setIdEpic(2);
        manager.createSubtask(subtask);

        Subtask subtask1 = new Subtask();
        subtask1.setName("Название сабтаска1");
        subtask1.setDescription("Описание сабтаска1");
        subtask1.setStatus(Status.NEW);
        subtask1.setIdEpic(2);
        manager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask();
        subtask2.setName("Название сабтаска2");
        subtask2.setDescription("Описание сабтаска2");
        subtask2.setStatus(Status.NEW);
        subtask2.setIdEpic(3);
        manager.createSubtask(subtask2);

        Subtask subtask3 = new Subtask();
        subtask3.setName("Название сабтаска2");
        subtask3.setDescription("Описание сабтаска2");
        subtask3.setStatus(Status.DONE);
        subtask3.setIdEpic(3);
        manager.updateSubtask(6, subtask3);

    //    manager.deleteTaskId(1);
    //    manager.deleteEpic(3);

    //    manager.getTaskId(1);

        Task task2 = new Task();
        task2.setName("Название новое1");
        task2.setDescription("Описание новое1");
        task2.setStatus(Status.IN_PROGRESS);
        manager.updateTask(1,task2);

    //    manager.getTaskId(1);

    //    manager.deleteTasks();

    //    manager.printSubtaskOfEpic(2);

    /*    manager.printTask();
        manager.printEpic();
        manager.printSubtask();*/
    }
}
