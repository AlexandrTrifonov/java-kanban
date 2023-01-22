package tasks;

import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> idSubtask;
    private Status statusEpic;


    public Epic (String name, String description) {
        super(name, description);
        this.idSubtask = new ArrayList<>();
        this.status = Status.NEW;
        this.type = Type.EPIC;
    }

    public Status getStat() {
        return  status;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId());
        sb.append(",");
        sb.append(Type.EPIC);
        sb.append(",");
        sb.append(getName());
        sb.append(",");
        sb.append(getStat());
        sb.append(",");
        sb.append(getDescription());
        sb.append("\n");
        return sb.toString();
    }
 /*   @Override
    public String toString() {
        return "Epic{" + super.toString() +
                "} ";
    }*/
/*    @Override
    public String toString() {
        return "Epic{" + super.toString() +
                "idSubtask=" + idSubtask +
                "} ";
    }*/
}
