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

class InMemoryTaskManagerTest extends TaskManagerTest {

    @BeforeEach
    public void beforeEach() {
        manager = Managers.getDefault();
    }

    @Test
    void validation(){
        Task task = new Task("Тест НазваниеЗадачи","Тест ОписаниеЗадачи", 50,"2023-04-01 04:10");
        manager.createTask(task);
        Task task1 = new Task("Тест НазваниеЗадачи1","Тест ОписаниеЗадачи1" , 50,"2023-04-01 00:10");
        manager.createTask(task1);
        Task task2 = new Task("Тест НазваниеЗадачи2","Тест ОписаниеЗадачи2" , 50,"2023-04-01 00:20");

        int mark = manager.validation(task2);
        assertEquals(0, mark,"Ошибка");

        final Collection<Task> tasks = manager.getListTasks();
        assertEquals(2, tasks.size(), "Размер списка не совпадает");
    }

    @Test
    void updateEpicStatusDone() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);

        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250, "2023-03-15 18:10");

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
        Subtask subtask2 = new Subtask("Сабтаск2 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-16 10:10");
        manager.createSubtask(subtask2);

        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-16 18:10");

        manager.createSubtask(subtask3);
        subtask3.setStatus(Status.DONE);
        manager.updateSubtask(subtask3);

        assertEquals(epic.getStat(), Status.NEW, "Статусы эпика и сабтаска не совпадают");
    }

/*    @Test
    void updateEpicStatusInProgress() {

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);
        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-17 18:10");

        manager.createSubtask(subtask3);
        subtask3.setStatus(Status.DONE);
        manager.updateSubtask(subtask3);

        assertEquals(manager.getEpicById(0).getStat(), manager.getSubtaskById(1).getStatus(), "Сабтаски не совпадают");
    }*/

    @Override
    void hello() {
        System.out.println("Тестирование InMemoryTaskManagerTest");
    }
}