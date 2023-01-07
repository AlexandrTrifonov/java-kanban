package interfaces;

import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

import tasks.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;

import java.util.List;

public interface TaskManager {


    List<Task> getHistory();
    Collection<Task> getListTasks();

    Collection<Epic> getListEpics();

    Collection<Subtask> getListSubtasks();

    void deleteTasks();

    void deleteEpics();

    void deleteSubtasks();

    Task getTaskById(Integer id);

    Epic getEpicById(Integer id);

    Subtask getSubtaskById(Integer id);

    int createTask(Task task);

    int createEpic(Epic epic);

    int createSubtask(Subtask subtask);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    void deleteTaskById(Integer id);

    void deleteEpicById(Integer id);

    void deleteSubtaskById(Integer id);

    List<Subtask> getSubtasksByEpic(Integer id);

}
