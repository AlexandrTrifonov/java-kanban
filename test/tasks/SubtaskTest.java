package tasks;

import interfaces.TaskManager;
import manager.Managers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    TaskManager manager = Managers.getDefault();

    @Test
    void setIdEpic() {

        Subtask subtask = new Subtask("Тест Сабтаск эпика", "Тест Описание сабтаска", 1);
        subtask.setIdEpic(10);
        int idEpic = subtask.getIdEpic();
        assertEquals(10, idEpic, "Не установили idEpic=10");
    }

    @Test
    void getIdEpic() {

        Subtask subtask = new Subtask("Сабтаск эпика", "Описание сабтаска", 1);
        int idEpic = subtask.getIdEpic();
        assertEquals(1, idEpic, "idEpic не равен 1");
    }
}