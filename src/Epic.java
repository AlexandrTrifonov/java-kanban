import java.util.ArrayList;

public class Epic extends Task {

    ArrayList<Integer> idSubtask;

    public Epic (String name, String discription, String status, ArrayList<Integer> idSubtask) {
        super(name, discription, status);
        this.idSubtask = idSubtask;
    }
}
