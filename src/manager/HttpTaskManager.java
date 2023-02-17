package manager;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import http.KVTaskClient;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpTaskManager extends FileBackedTasksManager {

    static String url = "http://localhost:8078/";
    KVTaskClient kvTaskClient;
    private String key;
    Gson gson;

    public HttpTaskManager (String url) {
        super();
        kvTaskClient = new KVTaskClient(url);
        gson = new GsonBuilder().create();
    }

    public KVTaskClient getKvTaskClient() {
        return kvTaskClient;
    }

    @Override
    public void save() {
        String prioritizedTasks = gson.toJson(getPrioritizedTasks());
        kvTaskClient.put("tasks", prioritizedTasks);

        String history = gson.toJson(getHistory());
        kvTaskClient.put("tasks/history", history);

        String tasks = gson.toJson(getListTasks());
        kvTaskClient.put("tasks/task", tasks);

        String epics = gson.toJson(getListEpics());
        kvTaskClient.put("tasks/epic", epics);

        String subtasks = gson.toJson(getListSubtasks());
        kvTaskClient.put("tasks/subtask", subtasks);
    }

    public HttpTaskManager load() {

        HttpTaskManager newManager = Managers.getDefault();

        String jsonTasks = getKvTaskClient().load("tasks/task");
        JsonElement mainObject = JsonParser.parseString(jsonTasks);
        JsonArray pItem = mainObject.getAsJsonArray();
        for (JsonElement user : pItem) {
            JsonObject userObject = user.getAsJsonObject();
            Task task = gson.fromJson(userObject, Task.class);
            newManager.getHmTask().put(task.getId(), task);
        }

        String jsonEpic = getKvTaskClient().load("tasks/epic");
        JsonElement mainObjectEpic = JsonParser.parseString(jsonEpic);
        JsonArray pItemEpic = mainObjectEpic.getAsJsonArray();
        for (JsonElement userEpic : pItemEpic) {
            JsonObject userObjectEpic = userEpic.getAsJsonObject();
            Epic epic = gson.fromJson(userObjectEpic, Epic.class);
            newManager.getHmEpic().put(epic.getId(), epic);
        }

        String jsonSubtask = getKvTaskClient().load("tasks/subtask");
        JsonElement mainObjectSubtask = JsonParser.parseString(jsonSubtask);
        JsonArray pItemSubtask = mainObjectSubtask.getAsJsonArray();
        for (JsonElement userSubtask : pItemSubtask) {
            JsonObject userObjectSubtask = userSubtask.getAsJsonObject();
            Subtask subtask = gson.fromJson(userObjectSubtask, Subtask.class);
            newManager.getHmSubtask().put(subtask.getId(), subtask);
        }

        String jsonHistory = getKvTaskClient().load("tasks/history");
        JsonElement mainObjectHistory = JsonParser.parseString(jsonHistory);
        JsonArray pItemHistory = mainObjectHistory.getAsJsonArray();
        for (JsonElement userHistory : pItemHistory) {
            JsonObject userObjectHistory = userHistory.getAsJsonObject();
            String typeHistory = userObjectHistory.get("type").getAsString();
            if (typeHistory.equals("TASK")) {
                Task taskHistory = gson.fromJson(userObjectHistory, Task.class);
                historyManager.add(taskHistory);
            }
            if (typeHistory.equals("EPIC")) {
                Epic taskHistory = gson.fromJson(userObjectHistory, Epic.class);
                historyManager.add(taskHistory);
            }
            if (typeHistory.equals("SUBTASK")) {
                Subtask taskHistory = gson.fromJson(userObjectHistory, Subtask.class);
                historyManager.add(taskHistory);
            }
        }
        return newManager;
    }
}
