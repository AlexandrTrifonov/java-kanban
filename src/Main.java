import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Task task = new Task("Название", "Описание", "new");
        manager.createTask(task);
        Task task1 = new Task("Название1", "Описание1", "new");
        manager.createTask(task1);

        ArrayList <Integer> idSubtask = new ArrayList<>();
        Epic epic = new Epic("Название епика", "Описание епика", "new", idSubtask);
        manager.createEpic(epic);
        ArrayList <Integer> idSubtask1 = new ArrayList<>();
        Epic epic1 = new Epic("Название епика1", "Описание епика1", "new", idSubtask1);
        manager.createEpic(epic1);

        Subtask subtask = new Subtask("Название сабтаска", "Описание сабтаска", "new", 2);
        manager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("Название сабтаска1", "Описание сабтаска1", "new", 2);
        manager.createSubtask(subtask1);
        Subtask subtask2 = new Subtask("Название сабтаска2", "Описание сабтаска2 - он у епика1", "new", 3);
        manager.createSubtask(subtask2);

        manager.printTask();
        manager.printEpic();
        manager.printSubtask();

        Subtask subtask3 = new Subtask("Название сабтаска2", "Описание сабтаска2 - он у епика1", "done", 2);
        manager.updateSubtask(4, subtask3);
        Subtask subtask4 = new Subtask("Название сабтаска1", "Описание сабтаска1 - он у епика1", "in progress", 2);
        manager.updateSubtask(5, subtask4);
        Subtask subtask5 = new Subtask("Название сабтаска3", "Описание сабтаска3 - он у епика2", "done", 3);
        manager.updateSubtask(6, subtask5);

        manager.printTask();
        manager.printEpic();
        manager.printSubtask();

        manager.deleteTaskId(1);
        manager.deleteEpic(3);

    /*    manager.getTaskId(1);

        Task task2 = new Task("Название новое1", "Описание новое1", "in progress");
        manager.updateTask(1,task2);

        manager.printTask();

        manager.deleteTaskId(1);

        manager.deleteTasks();

        manager.printSubtaskOfEpic(2);*/
    }
}
