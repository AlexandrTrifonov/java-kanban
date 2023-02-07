package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> idSubtask;
    private Status statusEpic;


    public Epic (String name, String description) {
        super(name, description);
        this.idSubtask = new ArrayList<>();
        this.status = Status.NEW;
        this.type = Type.EPIC;
        this.duration = 0;
        this.time = "null";
        this.startTime = null;
        this.endTime = null;
    }

    public Epic (String name, String description, int duration, String time) {
        super(name, description);
        this.idSubtask = new ArrayList<>();
        this.status = Status.NEW;
        this.type = Type.EPIC;
        this.duration = duration;
        this.time = time;
        if (time.isEmpty() || time.isBlank() || time != "null") {
            this.startTime = null;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startTime = LocalDateTime.parse(time, formatter);
            this.startTime = startTime;
        }
        this.endTime = getEndTime();
    }


    public Status getStat() {
        return  status;
    }

    public void setStat(Status status) {
        this.status=status;
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

    public void setStartTime(LocalDateTime startTime) {
        this.startTime=startTime;
    }

    public LocalDateTime getStartTime() {
        return  startTime;
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
        sb.append(getDuration());
        sb.append(",");
        sb.append(getTime());
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
