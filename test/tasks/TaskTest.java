package tasks;

import interfaces.TaskManager;
import manager.Managers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TaskTest {

    @Test
    void setId() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        task.setId(10);
        int id = task.getId();
        assertEquals(10, id, "Не установили id=10");
    }

    @Test
    void getId() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        int id = task.getId();
        assertEquals(0, id, "id не равен 0");
    }

    @Test
    void setName() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        task.setName("НОВОЕ");
        String st = task.getName();
        assertEquals("НОВОЕ", st, "Name не совпадает");
    }

    @Test
    void setNameNull() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        task.setName("");
        String st = task.getName();
        assertEquals("", st, "Name не совпадает");
    }

    @Test
    void getName() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        task.setName("НОВОЕ");
        String st = task.getName();
        assertEquals("НОВОЕ", st, "Name не совпадает");
    }

    @Test
    void setDescription() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        task.setDescription("НОВОЕ");
        String st = task.getDescription();
        assertEquals("НОВОЕ", st, "Description не совпадает");
    }

    @Test
    void getDescription() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        String st = task.getDescription();
        assertEquals("Описание1", st, "Description не совпадает");
    }

    @Test
    void setStatus() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        task.setStatus(Status.IN_PROGRESS);
        Status st = task.getStatus();
        assertEquals(Status.IN_PROGRESS, st, "Status не совпадает");
    }

    @Test
    void getStatus() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        Status st = task.getStatus();
        assertEquals(Status.NEW, st, "Status не совпадает");
    }

    @Test
    void getType() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        Type st = task.getType();
        assertEquals(Type.TASK, st, "Type не совпадает");
    }

    @Test
    void setDuration() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        task.setDuration(500);
        int duration = task.getDuration();
        assertEquals(500, duration, "Не установили duration=500");
    }

    @Test
    void getDuration() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        int duration = task.getDuration();
        assertEquals(100, duration, "duration не равен 100");
    }

    @Test
    void setStartTime() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        task.setStartTime("5555,03,01,10,00");
        String startTime = task.getStartTime();
        assertEquals("5555,03,01,10,00", startTime, "Не установили startTime=5555,03,01,10,00");
    }

    @Test
    void setStartTimeEmpty() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        task.setStartTime("");
        String startTime = task.getStartTime();
        assertEquals("", startTime, "Ошибка");
    }

    @Test
    void getStartTime() {

        Task task = new Task(0,"Название1","Описание1", 100, "2023,03,01,10,00");
        String startTime = task.getStartTime();
        assertEquals("2023,03,01,10,00", startTime, "startTime не равен 2023,03,01,10,00");
    }
}