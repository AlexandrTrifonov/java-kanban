package tasks;

import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> idSubtask;
    private Status statusEpic;

    public Epic (String name, String description) {
        super(name, description);
        this.idSubtask = new ArrayList<>();
        this.status = Status.NEW;
    }

    @Override
    public Status getStatus() {
        return  statusEpic;
    }

    public void setStatus(Status statusEpic) {
        this.statusEpic=statusEpic;
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
                "} ";
    }
/*    @Override
    public String toString() {
        return "Epic{" + super.toString() +
                "idSubtask=" + idSubtask +
                "} ";
    }*/
}
