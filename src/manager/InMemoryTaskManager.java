package manager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import interfaces.HistoryManager;
import interfaces.TaskManager;
import tasks.Status;
import tasks.Task;
import tasks.Epic;
import tasks.Subtask;

public class InMemoryTaskManager implements TaskManager {
    private Integer id;
    private final HashMap <Integer, Task> hmTask;
    private final HashMap <Integer, Epic> hmEpic;
    private final HashMap <Integer, Subtask> hmSubtask;
    private final TreeSet<Task> treeSetPrioritized;

    public HashMap<Integer, Task> getHmTask() {
        return hmTask;
    }

    public HashMap<Integer, Epic> getHmEpic() {
        return hmEpic;
    }

    public HashMap<Integer, Subtask> getHmSubtask() {
        return hmSubtask;
    }

    public InMemoryTaskManager() {
        id=0;
        hmTask = new HashMap<>();
        hmEpic = new HashMap<>();
        hmSubtask = new HashMap<>();
        treeSetPrioritized = new TreeSet<>((o1, o2) -> {
        //    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if (o1.getStartTime() == null) return 1;
            if (o2.getStartTime() == null) return -1;
            LocalDateTime startTime1 = o1.getStartTime();
            LocalDateTime startTime2 = o2.getStartTime();
        //    LocalDateTime startTime1 = LocalDateTime.parse(o1.getStartTime(), formatter);
        //    LocalDateTime startTime2 = LocalDateTime.parse(o2.getStartTime(), formatter);
            if (startTime1.isBefore(startTime2)) return -1;
            if (startTime1.isAfter(startTime2)) return 1;
            else return o1.getId() - o2.getId();
        });
    }
    HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public Task getTaskById(Integer id) {
        if (id!=null && id <= this.id) {
            Task task = hmTask.get(id);
            historyManager.add(task);
            return task;
        }
        return null;
    }
    @Override
    public Epic getEpicById(Integer id) {
        Epic epic = hmEpic.get(id);
        historyManager.add(epic);
        return epic;
    }
    @Override
    public Subtask getSubtaskById(Integer id) {
        Subtask subtask = hmSubtask.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    @Override
    public Collection<Task> getListTasks() {
        return hmTask.values();
    }
    @Override
    public Collection<Epic> getListEpics() {
        return hmEpic.values();
    }
    @Override
    public Collection<Subtask> getListSubtasks() {
        return hmSubtask.values();
    }
    @Override
    public void deleteTasks() {
        hmTask.clear();
    }
    @Override
    public void deleteEpics() {
        hmEpic.clear();
        hmSubtask.clear();
    }
    @Override
    public void deleteSubtasks() {
        hmSubtask.clear();
        for (Integer key : hmEpic.keySet()) {
            hmEpic.get(key).getIdSubtask().clear();
        }
    }
    @Override
    public int validation(Task task) {
    //    String start = task.getStartTime();
        LocalDateTime startTime = task.getStartTime();
        if (startTime == null) {
            return 1;
        }
    /*    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(start, formatter);*/
        int durMinusOne = task.getDuration()-1;
        LocalDateTime endTime = startTime.plusMinutes(durMinusOne);
        ArrayList<Task> list = getPrioritizedTasks();
        int mark = 1;
        if (list.isEmpty()) {
            mark = 1;
            return mark;
        } else {
            for(Task tasks : list) {
            //    String startTasks = tasks.getStartTime();
                LocalDateTime startTimeTask = tasks.getStartTime();
                if (startTimeTask != null) {
                //    LocalDateTime startTimeTask = LocalDateTime.parse(startTasks, formatter);
                    int durTaskMinusOne = tasks.getDuration();
                    LocalDateTime endTimeTask = startTimeTask.plusMinutes(durTaskMinusOne);
                    if (startTimeTask.isBefore(startTime) && endTimeTask.isAfter(startTime)) {
                        return 0;
                    //    break;
                    }
                    if (startTimeTask.isBefore(endTime) && endTimeTask.isAfter(endTime)) {
                        return 0;
                   //    break;
                    }
               }
            }
        }
        return mark;
    }
    @Override
    public int createTask(Task task) {
        if (validation(task) == 1) {
            task.setId(id);
            hmTask.put(task.getId(),task);
            treeSetPrioritized.add(task);
            this.id ++;
            return task.getId();
        }
        return -1;
    }
    @Override
    public int createEpic(Epic epic) {
        epic.setId(id);
        hmEpic.put(epic.getId(),epic);
        this.id ++;
        return epic.getId();
    }


    @Override
    public int createSubtask(Subtask subtask) {
        int mark = validation(subtask);
        if (mark == 1) {
            subtask.setId(id);
            hmSubtask.put(subtask.getId(), subtask);
            hmEpic.get(subtask.getIdEpic()).getIdSubtask().add(id);
            Epic epic = hmEpic.get(subtask.getIdEpic());

            int count = 0;
            Integer idEpic = subtask.getIdEpic();
            LocalDateTime startTimeEpic = null;
            int dur = 0;
            for (Integer idSubtask : hmEpic.get(idEpic).getIdSubtask()) {
                LocalDateTime startTime = hmSubtask.get(idSubtask).getStartTime();
                dur = dur + hmSubtask.get(idSubtask).getDuration();
                if (count == 0) {
                    startTimeEpic = startTime;
                    count++;
                } else {
                    if (startTime!=null) {
                        if (startTime.isBefore(startTimeEpic)) {
                            startTimeEpic = startTime;
                        }
                    }
                }
            }

            epic.setDuration(dur);
            epic.setStartTime(startTimeEpic);
            hmEpic.put(subtask.getIdEpic(),epic);

            treeSetPrioritized.add(subtask);
            this.id++;
            return subtask.getId();
        }
        return -1;
    }
    @Override
    public void updateTask(Task task) {
        int mark = validation(task);
        if (mark == 1) {
            hmTask.put(task.getId(),task);
        }
    }
    @Override
    public void updateEpic(Epic epic) {
        Integer idEpicToUpdate = epic.getId();
        ArrayList <Integer> oldIdSubtask = hmEpic.get(idEpicToUpdate).getIdSubtask();
        epic.setIdSubtask(oldIdSubtask);
        hmEpic.put(epic.getId(),epic);
    }
    @Override
    public void updateSubtask(Subtask subtask) {
        int mark = validation(subtask);
        if (mark == 1) {
            hmSubtask.put(subtask.getId(),subtask);
            int size = hmEpic.get(subtask.getIdEpic()).getIdSubtask().size();
            int countnew = 0;
            int countdone = 0;
            for (int i=0; i<size; i++) {
                Integer zz = hmEpic.get(subtask.getIdEpic()).getIdSubtask().get(i);
                if (hmSubtask.get(zz).getStatus().equals(Status.NEW)){
                    countnew ++;
                } else if (hmSubtask.get(zz).getStatus().equals(Status.DONE)){
                    countdone ++;
                }
            }
            Epic epic = hmEpic.get(subtask.getIdEpic());
            if (size==countnew) {
                epic.setStat(Status.NEW);
            } else if (size==countdone) {
                epic.setStat(Status.DONE);
            } else {
                epic.setStat(Status.IN_PROGRESS);
            }
            hmEpic.put(subtask.getIdEpic(),epic);
        }
    }
    @Override
    public void deleteTaskById(Integer id) {
        hmTask.remove(id);
        historyManager.remove(id);
    }
    @Override
    public void deleteEpicById(Integer id) {
        ArrayList<Integer> idSubtaskEpic = hmEpic.get(id).getIdSubtask();
        for (int i = 0; i < idSubtaskEpic.size(); i++) {
            hmSubtask.remove(idSubtaskEpic.get(i));
            Integer idSubtask = idSubtaskEpic.get(i);
            historyManager.remove(idSubtask);
        }
        hmEpic.remove(id);
        historyManager.remove(id);
    }
    @Override
    public void deleteSubtaskById(Integer id) {
        int i=0;
        Integer idEpic = hmSubtask.get(id).getIdEpic();
        for (Integer idSubtask : hmEpic.get(idEpic).getIdSubtask()) {
            if (idSubtask.equals(id)) {
                hmEpic.get(idEpic).getIdSubtask().remove(i);
                break;
            } else {
                i++;
            }
        }
        hmSubtask.remove(id);
        historyManager.remove(id);
    }

    @Override
    public ArrayList<Subtask> getSubtasksByEpic(Integer id) {
        ArrayList<Subtask> listSubtasks = new ArrayList<>();
        for (Subtask subtask : hmSubtask.values()) {
            if (subtask.getIdEpic().equals(id)) {
                listSubtasks.add(subtask);
            }
        }
        return listSubtasks;
    }
    @Override
    public ArrayList<Task> getPrioritizedTasks() {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : treeSetPrioritized) {
            list.add(task);
        }
        return list;
    }
 /*   static TreeSet<Task> treeSetPrioritized = new TreeSet<>((o1, o2) -> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (o1.getStartTime() == null) return 1;
        if (o2.getStartTime() == null) return -1;
        LocalDateTime startTime1 = LocalDateTime.parse(o1.getStartTime(), formatter);
        LocalDateTime startTime2 = LocalDateTime.parse(o2.getStartTime(), formatter);
        if (startTime1.isBefore(startTime2)) return -1;
        if (startTime1.isAfter(startTime2)) return 1;
        else return o1.getId() - o2.getId();
    });*/
}
