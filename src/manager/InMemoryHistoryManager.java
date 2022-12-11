package manager;

import interfaces.HistoryManager;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private List <Task> historyTasks;

    public InMemoryHistoryManager() {
        historyTasks = new ArrayList<>();
    }

    @Override
    public List<Task> getHistory() {
        return historyTasks;
    }

    @Override
    public void add(Task task) {
        historyTasks.add(task);
        if (historyTasks.size() > 10) {
            historyTasks.remove(0);
        }
    }
}
