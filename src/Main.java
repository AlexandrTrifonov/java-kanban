import java.util.ArrayList;
import java.util.Collection;

import tasks.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;
import manager.Manager;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        // создать 2 задачи, один эпик с 2 подзадачами и один эпик с 1 подзадачей
        Task task = new Task("Название","Описание",Status.NEW);
        manager.createTask(task);

        Task task1 = new Task("Название1","Описание1",Status.NEW);
        manager.createTask(task1);

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

        //распечатать списки эпиков, задач и подзадач
        Collection<Epic> listEpics = manager.getListEpics();
        for (Epic epics : listEpics) {
            System.out.println(epics);
        }

        Collection<Task> listTasks = manager.getListTasks();
        for (Task tasks : listTasks) {
            System.out.println(tasks);
        }

        Collection<Subtask> listSubtasks = manager.getListSubtasks();
        for (Subtask subtasks : listSubtasks) {
            System.out.println(subtasks);
        }

        //изменить статусы созданных объектов - task1 и subtask2
        Task task2 = new Task("Название новое1", "Описание новое1", Status.IN_PROGRESS);
        int idTaskToUpdate = task1.getId(); //id таска который надо обновить
        task2.setId(idTaskToUpdate);
        manager.updateTask(task2);

        Task task3 = new Task("Название новое1", "Описание новое1", Status.DONE);
        int idTaskToUpdate1 = task.getId(); //id таска который надо обновить
        task3.setId(idTaskToUpdate1);
        manager.updateTask(task3);

        Integer idEpicForSubtask3 = epic1.getId(); //id эпика в котором обновить сабтаск
        Subtask subtask3 = new Subtask("Название сабтаска222", "Описание сабтаска2", Status.DONE, idEpicForSubtask3);
        int idSubtaskToUpdate = subtask2.getId(); //id сабтаска который надо обновить
        subtask3.setId(idSubtaskToUpdate);
        manager.updateSubtask(subtask3);

        Integer idEpicForSubtask4 = epic.getId(); //id эпика в котором обновить сабтаск
        Subtask subtask4 = new Subtask("Название сабтаска222", "Описание сабтаска2", Status.DONE, idEpicForSubtask4);
        int idSubtaskToUpdate1 = subtask.getId(); //id сабтаска который надо обновить
        subtask4.setId(idSubtaskToUpdate1);
        manager.updateSubtask(subtask4);

        //распечатать  заново после обновления
        Collection<Epic> listEpicsNew = manager.getListEpics();
        for (Epic epics : listEpicsNew) {
            System.out.println(epics);
        }

        Collection<Task> listTasksNew = manager.getListTasks();
        for (Task tasks : listTasksNew) {
            System.out.println(tasks);
        }

        Collection<Subtask> listSubtasksNew = manager.getListSubtasks();
        for (Subtask subtasks : listSubtasksNew) {
            System.out.println(subtasks);
        }

/*        // получение списка всех задач, епиков, подзадач
        Collection<Epic> listEpics = manager.getListEpics();
        for (Epic epics : listEpics) {
            System.out.println(epics);
        }

        Collection<Task> listTasks = manager.getListTasks();
        for (Task tasks : listTasks) {
            System.out.println(tasks);
        }

        Collection<Subtask> listSubtasks = manager.getListSubtasks();
        for (Subtask subtasks : listSubtasks) {
            System.out.println(subtasks);
        }

        // удаление всех задач, епиков, подзадач
        manager.deleteTasks();
        manager.deleteEpics();
        manager.deleteSubtasks();

        // получение по id
        int idTaskToGet = task1.getId();
        System.out.println(manager.getTaskById(idTaskToGet));

        int idEpicToGet = epic1.getId();
        System.out.println(manager.getEpicById(idEpicToGet));

        int idSubtaskToGet = subtask2.getId();
        System.out.println(manager.getSubtaskById(idSubtaskToGet));

        // обновление задачи, эпика, подзадачи
        Task task2 = new Task("Название новое1", "Описание новое1", Status.IN_PROGRESS);
        int idTaskToUpdate = task1.getId();
        task2.setId(idTaskToUpdate);
        manager.updateTask(task2);

        ArrayList <Integer> idSubtask2 = new ArrayList<>();
        Epic epic2 = new Epic("Название НОВОЕ епика1", "Описание НОВОЕ епика1", Status.NEW, idSubtask2);
        int idEpicToUpdate = epic1.getId();
        epic2.setId(idEpicToUpdate);
        manager.updateEpic(epic2);

        Integer idEpicForSubtask3 = epic1.getId(); //id эпика в котором обновить сабтаск
        Subtask subtask3 = new Subtask("Название сабтаска222", "Описание сабтаска2", Status.DONE, idEpicForSubtask3);
        int idSubtaskToUpdate = subtask2.getId(); //id сабтаска который надо обновить
        subtask3.setId(idSubtaskToUpdate);
        manager.updateSubtask(subtask3);

        // удаление по id
        int idTaskToDelete = task1.getId();
        manager.deleteTaskById(idTaskToDelete);

        int idEpicToDelete = epic1.getId();
        manager.deleteEpicById(idEpicToDelete);

        int idSubtaskToDelete = subtask2.getId();
        manager.deleteSubtaskById(idSubtaskToDelete);

        // получить списка подтасков эпика с определенным id
        int idEpicToGetSubtasksByEpic = epic.getId();
        System.out.println(manager.getSubtasksByEpic(idEpicToGetSubtasksByEpic));*/

    }
}
