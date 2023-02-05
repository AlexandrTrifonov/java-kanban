package tasks;

public class Subtask extends Task{

    private Integer idEpic;

    public Subtask (String name, String description, Integer idEpic) {
        super(name, description);
        this.idEpic=idEpic;
        this.status = Status.NEW;
        this.type = Type.SUBTASK;
        this.duration = 100;
        this.startTime = null;
    }

    public Subtask (String name, String description, Integer idEpic, int duration) {
        super(name, description);
        this.idEpic=idEpic;
        this.status = Status.NEW;
        this.type = Type.SUBTASK;
        this.duration = duration;
        this.startTime = null;
    }

    public Subtask (String name, String description, Integer idEpic, int duration, String startTime) {
    //    super(name, description, duration, startTime);
        super(name, description);
        this.idEpic=idEpic;
        this.status = Status.NEW;
        this.type = Type.SUBTASK;
        this.duration = duration;
        this.startTime = startTime;
    }

    public Subtask (Status status, String name, String description, Integer idEpic, int duration, String startTime) {
        //    super(name, description, duration, startTime);
        super(name, description);
        this.idEpic=idEpic;
        this.status = status;
        this.type = Type.SUBTASK;
        this.duration = duration;
        this.startTime = startTime;
    }

    public void setIdEpic(Integer idEpic) {
        this.idEpic=idEpic;
    }

    public Integer getIdEpic() {
        return idEpic;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId());
        sb.append(",");
        sb.append(Type.SUBTASK);
        sb.append(",");
        sb.append(getName());
        sb.append(",");
        sb.append(getStatus());
        sb.append(",");
        sb.append(getDuration());
        sb.append(",");
        sb.append(getStartTime());
        sb.append(",");
        sb.append(getDescription());
        sb.append(",");
        sb.append(getIdEpic());
        sb.append("\n");
        return sb.toString();
    }

/*    @Override
    public String toString() {
        return "Subtask{" + super.toString() +
                "} ";
    }*/
/*    @Override
    public String toString() {
        return "Subtask{" + super.toString() +
                "idEpic=" + idEpic +
                "} ";
    }*/
}
