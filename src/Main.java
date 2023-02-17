import com.google.gson.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import http.KVServer;
import http.KVTaskClient;
import interfaces.HistoryManager;
import interfaces.TaskManager;
import manager.*;
import tasks.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;


public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        new KVServer().start();

        HttpTaskManager manager = Managers.getDefault();

        Task task11 = new Task("Название1","Описание", 50, "2023-03-01 18:10");
        manager.createTask(task11);

        Task task31 = new Task("Название5","Описание5", 50, "2023-03-02 18:10");
        manager.createTask(task31);

        Epic epic = new Epic("Эпик", "Описание епика");
        manager.createEpic(epic);

        Subtask subtask3 = new Subtask("Сабтаск3 эпика", "Описание сабтаска", epic.getId(), 250,"2023-03-01 18:10");
        manager.createSubtask(subtask3);

        System.out.println(manager.getListTasks());
        System.out.println(manager.getListEpics());
        System.out.println(manager.getListSubtasks());

        HttpTaskManager newManager = manager.load();

        System.out.println("Вывод списка задач " + newManager.getListTasks());
        System.out.println("Вывод списка эпиков " + newManager.getListEpics());
        System.out.println("Вывод списка подзадач " + newManager.getListSubtasks());


    }
}
