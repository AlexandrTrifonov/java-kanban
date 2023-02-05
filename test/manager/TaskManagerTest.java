package manager;

import interfaces.HistoryManager;
import interfaces.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Status;
import tasks.Subtask;
import tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

abstract class TaskManagerTest <T extends TaskManager> {

    protected T manager;

    @Test
    void getListTasks() {

        Task task = new Task("Тест НазваниеЗадачи","Тест ОписаниеЗадачи", 50);
        manager.createTask(task);
        Task task1 = new Task("Тест НазваниеЗадачи1","Тест ОписаниеЗадачи1", 50);
        manager.createTask(task1);

        final Collection<Task> tasks = manager.getListTasks();
        assertNotNull(tasks, "Список задач пустой");
        assertEquals(2, tasks.size(), "Размер списка не совпадает");
    }

    @Test
    void getListTasksEmpty() {

        final Collection<Task> tasksEmpty = manager.getListTasks();
        assertTrue(tasksEmpty.isEmpty(), "Список задач не пустой");
        assertEquals(0, tasksEmpty.size(), "Размер списка не совпадает");
    }

    @Test
    void getListEpics() {

        Epic epic = new Epic("Тест НазваниеЭпика","Тест ОписаниеЭпика");
        manager.createEpic(epic);
        Epic epic1 = new Epic("Тест НазваниеЭпика1","Тест ОписаниеЭпика1");
        manager.createEpic(epic1);

        final Collection<Epic> epics = manager.getListEpics();
        assertNotNull(epics, "Список эпиков пустой");
        assertEquals(2, epics.size(), "Размер списка не совпадает");
    }

    @Test
    void getListEpicsEmpty() {

        final Collection<Epic> epicsEmpty = manager.getListEpics();
        assertTrue(epicsEmpty.isEmpty(), "Список эпиков не пустой");
        assertEquals(0, epicsEmpty.size(), "Размер списка не совпадает");
    }

    @Test
    void getListSubtasks() {

        Epic epic = new Epic("Тест НазваниеЭпика","Тест ОписаниеЭпика");
        manager.createEpic(epic);
        Subtask subtask = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", epic.getId(), 50);
        manager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", epic.getId(), 50);
        manager.createSubtask(subtask1);

        final Collection<Subtask> subtasks = manager.getListSubtasks();
        assertNotNull(subtasks, "Список подзадач пустой");
        assertEquals(2, subtasks.size(), "Размер списка не совпадает");
    }

    @Test
    void getListSubtasksNull() {

        final Collection<Subtask> subtasksEmpty = manager.getListSubtasks();
        assertTrue(subtasksEmpty.isEmpty(), "Список подзадач не пустой");
        assertEquals(0, subtasksEmpty.size(), "Размер списка не совпадает");
    }

    @Test
    void deleteTasks() {

        Task task = new Task("Тест НазваниеЗадачи","Тест ОписаниеЗадачи", 50,"2023-03-01 04:10");
        manager.createTask(task);
        Task task1 = new Task("Тест НазваниеЗадачи1","Тест ОписаниеЗадачи1" , 50,"2023-03-01 00:10");
        manager.createTask(task1);

        manager.deleteTasks();

        final Collection<Task> tasksEmpty = manager.getListTasks();
        assertTrue(tasksEmpty.isEmpty(), "Список задач не пустой");
        assertEquals(0, tasksEmpty.size(), "Размер списка не совпадает");
    }

    @Test
    void deleteTasksNull() {

        manager.deleteTasks();

        final Collection<Task> tasksEmpty = manager.getListTasks();
        assertTrue(tasksEmpty.isEmpty(), "Список задач не пустой");
        assertEquals(0, tasksEmpty.size(), "Размер списка не совпадает");
    }

    @Test
    void deleteEpics() {

        Epic epic = new Epic("Тест НазваниеЭпика","Тест ОписаниеЭпика");
        manager.createEpic(epic);
        Epic epic1 = new Epic("Тест НазваниеЭпика1","Тест ОписаниеЭпика1");
        manager.createEpic(epic1);
        Subtask subtask = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", epic.getId());
        manager.createSubtask(subtask);

        manager.deleteEpics();

        final Collection<Epic> epicsEmpty = manager.getListEpics();
        assertTrue(epicsEmpty.isEmpty(), "Список эпиков не пустой");
        assertEquals(0, epicsEmpty.size(), "Размер списка не совпадает");
        final Collection<Subtask> subtask1 = manager.getListSubtasks();
        assertTrue(subtask1.isEmpty(), "Список подзадач не пустой");
        assertEquals(0, subtask1.size(), "Размер списка не совпадает");
    }

    @Test
    void deleteEpicsNull() {

        manager.deleteEpics();

        final Collection<Epic> epicsEmpty = manager.getListEpics();
        assertTrue(epicsEmpty.isEmpty(), "Список эпиков не пустой");
        assertEquals(0, epicsEmpty.size(), "Размер списка не совпадает");
        final Collection<Subtask> subtask1 = manager.getListSubtasks();
        assertTrue(subtask1.isEmpty(), "Список подзадач не пустой");
        assertEquals(0, subtask1.size(), "Размер списка не совпадает");
    }

    @Test
    void deleteSubtasks() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);
        Subtask subtask = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", epic.getId());
        manager.createSubtask(subtask);

        manager.deleteSubtasks();

        final Collection<Subtask> subtasks1 = manager.getListSubtasks();
        assertTrue(subtasks1.isEmpty(), "Список задач не пустой");
        assertEquals(0, subtasks1.size(), "Размер списка не совпадает");
    }

    @Test
    void deleteSubtasksNull() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);

        manager.deleteSubtasks();

        final Collection<Subtask> subtasksNull = manager.getListSubtasks();
        assertTrue(subtasksNull.isEmpty(), "Список задач не пустой");
        assertEquals(0, subtasksNull.size(), "Размер списка не совпадает");

        final Collection<Epic> epics = manager.getListEpics();
        assertFalse(epics.isEmpty(), "Список задач пустой");
        assertEquals(1, epics.size(), "Размер списка не совпадает");
    }

    @Test
    void getTaskById() {

        Task task = new Task("Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        final int id = manager.createTask(task);
        final Task newTask = manager.getTaskById(id);

        assertNotNull(newTask, "Задача не найдена");
        assertEquals(task, newTask, "Задачи не совпадают");
    }

    @Test
    void getTaskByIdNull() {

        Task task = new Task("Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        task.setId(1);
        manager.createTask(task);
        manager.deleteTasks();

        final Task newTaskIdFalse = manager.getTaskById(1);

        assertNull(newTaskIdFalse, "Задача не найдена");
    }

    @Test
    void getTaskByIdWrong() {

        Task task = new Task("Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        task.setId(1);
        manager.createTask(task);

        final Task newTaskIdFalse = manager.getTaskById(15);
        assertNull(newTaskIdFalse, "Задача не найдена");
    }

    @Test
    void getEpicById() {

        Epic epic = new Epic("Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        final int id = manager.createEpic(epic);
        final Epic newEpic = manager.getEpicById(id);

        assertNotNull(newEpic, "Эпик не найден");
        assertEquals(epic, newEpic, "Эпики не совпадают");

        final Epic newEpicIdFalse = manager.getEpicById(15); //не существующий ижентификатор

        assertNull(newEpicIdFalse, "Эпик не найден");

        final Integer idEmpty = null;
        final Task newEpicIdFalse1 = manager.getEpicById(idEmpty); //пустой идентификатор

        assertNull(newEpicIdFalse1, "Ошибка при id равном null");
    }

    @Test
    void getSubtaskById() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);
        Subtask subtask = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", epic.getId());
        final int id = manager.createSubtask(subtask);
        final Subtask newSubtask = manager.getSubtaskById(id);

        assertNotNull(newSubtask, "ПодЗадача не найдена");
        assertEquals(subtask, newSubtask, "ПодЗадачи не совпадают");

        final Integer idEmpty = null;
        final Subtask newSubtaskIdFalse1 = manager.getSubtaskById(idEmpty); //пустой идентификатор

        assertNull(newSubtaskIdFalse1, "Ошибка при id равном null");
    }

    @Test
    void createTask() {

        Task task = new Task("Тест НазваниеЗадачи","Тест ОписаниеЗадачи");

        final int id = manager.createTask(task);
        final Task savedTask = manager.getTaskById(id);

        assertNotNull(savedTask, "Задача не найдена");
        assertEquals(task, savedTask, "Задачи не совпадают");
    }
    @Test
    void createEpic() {

        Epic epic = new Epic("Тест НазваниеЗадачи","Тест ОписаниеЗадачи");

        int id = manager.createEpic(epic);
        final Epic savedEpic = manager.getEpicById(id);

        assertNotNull(savedEpic, "Задача не найдена");
        assertEquals(epic, savedEpic, "Задачи не совпадают");
    }

    @Test
    void createSubtask() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);
        Subtask subtask = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", epic.getId());

        final int id = manager.createSubtask(subtask);
        final Subtask savedSubtask = manager.getSubtaskById(id);

        assertNotNull(savedSubtask, "Задача не найдена");
        assertEquals(subtask, savedSubtask, "Задачи не совпадают");

        List<Subtask> lists = manager.getSubtasksByEpic(epic.getId());

        assertNotNull(lists.get(0), "Задача не найдена");
        assertEquals(subtask, lists.get(0), "Задачи не совпадают");
    }

    @Test
    void updateTask() {

        Task task = new Task("Тест Название задачи", "Тест Описание задачи");
        int taskId = manager.createTask(task);
        Task newTask = new Task("Тест НОВОЕ Название задачи", "Тест НОВОЕ Описание задачи");
        newTask.setId(taskId);
        manager.updateTask(newTask);
        assertEquals(newTask, manager.getTaskById(taskId), "Задачи не совпадают");
    }

    @Test
    void updateEpic() {

        Epic epic = new Epic("Тест Название эпика", "Тест Описание эпика");
        int epicId = manager.createEpic(epic);
        Epic newEpic = new Epic("Тест НОВОЕ Название эпика", "Тест НОВОЕ Описание эпика");
        newEpic.setId(epicId);
        manager.updateEpic(newEpic);
        assertEquals(newEpic, manager.getEpicById(epicId), "Эпики не совпадают");
    }

    @Test
    void updateSubtask() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);
        Subtask subtask = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", epic.getId());
        int subtaskId = manager.createSubtask(subtask);

        Subtask newSubtask = new Subtask("Тест НОВОЕ Название сабтаска", "Тест НОВОЕ Описание сабтаска", epic.getId());
        newSubtask.setId(subtaskId);
        manager.updateSubtask(newSubtask);
        assertEquals(newSubtask, manager.getSubtaskById(subtaskId), "Сабтаски не совпадают");
    }

    @Test
    void updateEpicStatusNew() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);
        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-01 18:10");
        manager.createSubtask(subtask3);
        Subtask subtask2 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-01 18:10");
        manager.createSubtask(subtask2);

        assertEquals(epic.getStat(), Status.NEW, "Статусы эпика и сабтаска не совпадают");
    }

    @Test
    void deleteTaskById() {

        Task task = new Task("Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        final int id = manager.createTask(task);
        manager.deleteTaskById(id);

        final Collection<Task> tasks = manager.getListTasks();
        assertTrue(manager.getListTasks().isEmpty(), "Список не пустой");
        assertEquals(0, tasks.size(), "Размер списка не совпадает");
    }

    @Test
    void deleteEpicById() {

        Epic epic = new Epic("Эпик", "Описание епика");
        final int id = manager.createEpic(epic);
        Subtask subtask = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", epic.getId());
        manager.createSubtask(subtask);

        manager.deleteEpicById(id);

        final Collection<Epic> epics = manager.getListEpics();
        assertTrue(manager.getListEpics().isEmpty(), "Список не пустой");
        assertEquals(0, epics.size(), "Размер списка не совпадает");

        final Collection<Subtask> subtasks = manager.getListSubtasks();
        assertEquals(0, subtasks.size(), "Размер списка не совпадает");
    }

    @Test
    void deleteSubtaskById() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);
        Subtask subtask = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", epic.getId());

        final int id = manager.createSubtask(subtask);
        manager.deleteSubtaskById(id);

        final Collection<Subtask> subtasks = manager.getListSubtasks();
        assertEquals(0, subtasks.size(), "Размер списка не совпадает");
    }

    @Test
    void getSubtasksByEpic() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);
        Subtask subtask = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", epic.getId(), 50);
        manager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("Тест Сабтаск эпика1", "Тест Описание сабтаска1", epic.getId());
        manager.createSubtask(subtask1);

        List<Subtask> lists = manager.getSubtasksByEpic(epic.getId());
        assertEquals(2,lists.size(),"Размер списка подзадач не воспадает");
    }

    abstract void hello ();
}