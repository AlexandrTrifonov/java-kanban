package tasks;

import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> idSubtask;

    public Epic (String name, String description, Status status, ArrayList<Integer> idSubtask) {
        super(name, description, status);
        this.idSubtask = idSubtask;
    }

    public void setIdSubtask(ArrayList<Integer> idSubtask) {
        this.idSubtask=idSubtask;
    }

    public ArrayList<Integer> getIdSubtask() {
        return idSubtask;
    }

    @Override
    public String toString() {
        return "Epic{" + super.toString() +
                "idSubtask=" + idSubtask +
                "} ";
    }
}
