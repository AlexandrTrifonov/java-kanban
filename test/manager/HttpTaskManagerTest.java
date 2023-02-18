package manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import http.KVServer;
import http.KVTaskClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpTaskManagerTest extends TaskManagerTest<HttpTaskManager> {

    protected KVServer kvServer;


    @BeforeEach

    public void beforeEach() {
        try {
            kvServer = new KVServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        kvServer.start();
        manager = Managers.getDefault();
    }

    @AfterEach
    void serverStop() {
        kvServer.stop();
    }

    @Test
    void loadFromServerTest() {

        Task task11 = new Task("Название1","Описание", 50, "2023-03-01 18:10");
        manager.createTask(task11);

        Task task31 = new Task("Название5","Описание5", 50, "2023-03-02 18:10");
        manager.createTask(task31);

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);

        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-01 18:10");
        manager.createSubtask(subtask3);

        HttpTaskManager newManager = manager.load();
        var tasks = newManager.getListTasks();

    //    assertNotNull(tasks);
        assertEquals(2, tasks.size(), "Размер списка не совпадает");
    }

    @Override
    void hello() {
        System.out.println("Тестирование HttpTaskManagerTest");
    }
}