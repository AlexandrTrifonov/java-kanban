package manager;

import interfaces.HistoryManager;
import interfaces.TaskManager;

import static manager.FileBackedTasksManager.pathFile;
import static manager.HttpTaskManager.url;

public class Managers {

/*    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }*/

    public static HistoryManager getDefaultHistory() { return new InMemoryHistoryManager(); }

    public static FileBackedTasksManager getDefaultFileBackedTasksManager() {
        return new FileBackedTasksManager(pathFile);    }

    public static HttpTaskManager getDefault() {
        return new HttpTaskManager(url);
    }
}
