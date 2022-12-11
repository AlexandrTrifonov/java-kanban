package interfaces;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import tasks.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;

public interface HistoryManager {

    void add(Task task);

    List<Task> getHistory();
}
