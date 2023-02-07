package manager;

import exceptions.ManagerSaveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import static manager.FileBackedTasksManager.loadFromFile;
import static manager.FileBackedTasksManager.pathFile;
import static org.junit.jupiter.api.Assertions.*;

class FileBackedTasksManagerTest extends TaskManagerTest<FileBackedTasksManager> {

    @BeforeEach
    public void beforeEach() {
         manager = Managers.getDefaultFileBackedTasksManager();
    }

    @Test
    void validation(){
        Task task = new Task("Тест НазваниеЗадачи","Тест ОписаниеЗадачи", 50,"2023-04-02 04:10");
        manager.createTask(task);
        Task task1 = new Task("Тест НазваниеЗадачи1","Тест ОписаниеЗадачи1" , 50,"2023-04-02 00:10");
        manager.createTask(task1);
        Task task2 = new Task("Тест НазваниеЗадачи2","Тест ОписаниеЗадачи2" , 50,"2023-04-02 00:20");

        int mark = manager.validation(task2);
        assertEquals(0, mark,"Ошибка");

        final Collection<Task> tasks = manager.getListTasks();
        assertEquals(2, tasks.size(), "Размер списка не совпадает");
    }

    @Test
    void updateEpicStatusDone() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);

        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250, "2023-03-05 18:10");

        int idSub = manager.createSubtask(subtask3);
        subtask3.setStatus(Status.DONE);
        Subtask subtaskNew = manager.getSubtaskById(idSub);
        manager.updateSubtask(subtaskNew);

        assertEquals(epic.getStat(), Status.NEW, "Ошибка");
    }

    @Test
    void updateEpicStatusNewDone() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);
        Subtask subtask2 = new Subtask("Сабтаск2 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-06 10:10");
        manager.createSubtask(subtask2);

        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-06 18:10");

        manager.createSubtask(subtask3);
        subtask3.setStatus(Status.DONE);
        manager.updateSubtask(subtask3);

        assertEquals(epic.getStat(), Status.NEW, "Статусы эпика и сабтаска не совпадают");
    }

/*    @Test
    void updateEpicStatusInProgress() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);
        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-07 18:10");

        manager.createSubtask(subtask3);
        subtask3.setStatus(Status.DONE);
        manager.updateSubtask(subtask3);

        assertEquals(manager.getEpicById(0).getStat(), manager.getSubtaskById(1).getStatus(), "Сабтаски не совпадают");
    }*/

    @Test
    void saveAndLoadEpic () throws IOException {

        Epic epic = new Epic("Название1","Описание1",150,"2023-03-01 10:00");
        manager.createEpic(epic); //создали задачу
        manager.getEpicById(0); //добавили вывод по id для истории
        Subtask subtask = new Subtask("Сабтаск эпика", "Описание сабтаска", epic.getId(),50,"2023-03-01 12:30");
        manager.createSubtask(subtask);

        FileBackedTasksManager newManager = loadFromFile(pathFile); //загружаем из файла
        Task newEpic = newManager.getHmEpic().get(0);

        assertEquals(epic.toString(), newEpic.toString(), "Задачи не совпадают");
    }

    @Test
    void saveAndLoadEpicWithoutSubtasks () throws IOException {

        Epic epic = new Epic("Название1","Описание1",150,"2023-03-01 10:00");
        manager.createEpic(epic); //создали задачу
        manager.getEpicById(0); //добавили вывод по id для истории

        FileBackedTasksManager newManager = loadFromFile(pathFile); //загружаем из файла
        Task newEpic = newManager.getHmEpic().get(0);

        assertEquals(epic.toString(), newEpic.toString(), "Задачи не совпадают");
    }

  /*  @Test
    void saveAndLoad () throws IOException {

        Task task = new Task("Название1","Описание1",150,"2023-03-01 10:00");
        manager.createTask(task); //создали задачу
        manager.getTaskById(0); //добавили вывод по id для истории

        FileBackedTasksManager newManager = loadFromFile(pathFile); //загружаем из файла
        Task newTask = newManager.getHmTask().get(0);

        assertEquals(task.toString(), newTask.toString(), "Задачи не совпадают");
    }

    @Test
    void saveAndLoadEmptyHistory () throws IOException {

        Task task = new Task("Название1","Описание1",150,"2023-03-01 10:00");
        manager.createTask(task); //создали задачу

        FileBackedTasksManager newManager = loadFromFile(pathFile); //загружаем из файла
        Task newTask = newManager.getHmTask().get(0);

        assertEquals(task.toString(), newTask.toString(), "Задачи не совпадают");
    }*/

/*    @Test
    void fromString() {
        Task task = new Task("Название1","Описание1",150,"2023-03-01 10:00");
        manager.createTask(task);

        String value = manager.toString(task);
        Task taskNew = manager.fromString(value);

        assertEquals(task.toString(),taskNew.toString(),"Задачи не совпадают");
    }*/


    @Override
    void hello() {
        System.out.println("Тестирование FileBackedTasksManagerTest");
    }
}