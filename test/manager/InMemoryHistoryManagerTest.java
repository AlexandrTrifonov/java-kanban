package manager;

import interfaces.HistoryManager;
import interfaces.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    HistoryManager historyManager = Managers.getDefaultHistory();

    @Test
    void getHistory() {

        Task task = new Task(0,"Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        historyManager.add(task);
        Task task1 = new Task(1,"Тест НазваниеЗадачи1","Тест ОписаниеЗадачи1");
        historyManager.add(task1);

        final List<Task> listsHistory = historyManager.getHistory();
        assertNotNull(listsHistory, "Список истории пустой");
        assertEquals(2, listsHistory.size(), "Размер списка не совпадает");
    }

    @Test
    void getHistoryEmpty() {

        final List<Task> listsHistory = historyManager.getHistory();
        assertTrue(listsHistory.isEmpty(), "Список истории не пустой");
        assertEquals(0, listsHistory.size(), "Размер списка не совпадает");
    }

    @Test
    void add() {

        Task task = new Task(0,"Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        historyManager.add(task);

        Task task1 = new Task(1,"Тест НОВАЯ","Тест НОВАЯ");
        historyManager.add(task1);

        final List<Task> listsHistory = historyManager.getHistory();
        assertEquals(2, listsHistory.size(), "Размер списка не совпадает");
    }

    @Test
    void addSame() {

        Task task = new Task(0,"Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        historyManager.add(task);

        historyManager.add(task);

        final List<Task> listsHistory = historyManager.getHistory();
        assertEquals(1, listsHistory.size(), "Размер списка не совпадает");
    }

    @Test
    void remove() {

        Task task = new Task(0,"Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        historyManager.add(task);

        historyManager.remove(0);

        final List<Task> listsHistory = historyManager.getHistory();
        assertEquals(0, listsHistory.size(), "Размер списка не совпадает");
    }

    @Test
    void removeFirst() {

        Task task = new Task(0,"Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        historyManager.add(task);
        Task task1 = new Task(1,"Тест НазваниеЗадачи1","Тест ОписаниеЗадачи1");
        historyManager.add(task1);
        Task task2 = new Task(2,"Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        historyManager.add(task2);

        historyManager.remove(0); //

        final List<Task> listsHistory = historyManager.getHistory();
        assertEquals(2, listsHistory.size(), "Размер списка не совпадает");
    }

    @Test
    void removeMiddle() {

        Task task = new Task(0,"Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        historyManager.add(task);
        Task task1 = new Task(1,"Тест НазваниеЗадачи1","Тест ОписаниеЗадачи1");
        historyManager.add(task1);
        Task task2 = new Task(2,"Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        historyManager.add(task2);

        historyManager.remove(1);

        final List<Task> listsHistory = historyManager.getHistory();
        assertEquals(2, listsHistory.size(), "Размер списка не совпадает");
    }

    @Test
    void removeLast() {

        Task task = new Task(0,"Тест НазваниеЗадачи","Тест ОписаниеЗадачи");
        historyManager.add(task);
        Task task1 = new Task(1,"Тест НазваниеЗадачи1","Тест ОписаниеЗадачи1");
        historyManager.add(task1);
        Task task2 = new Task(2,"Тест НазваниеЗадачи","Тест ОписаниеЗадачи");

        historyManager.remove(2);

        final List<Task> listsHistory = historyManager.getHistory();
        assertEquals(2, listsHistory.size(), "Размер списка не совпадает");
    }

    @Test
    void removeFromEmptyHistory() {

        historyManager.remove(0);

        final List<Task> listsHistory = historyManager.getHistory();
        assertTrue(listsHistory.isEmpty(), "Список истории не пустой");
    }
}