package tasks;

public class Subtask extends Task{

    private Integer idEpic;

    public Subtask (String name, String description, Integer idEpic) {
        super(name, description);
        this.idEpic=idEpic;
        this.status = Status.NEW;
        this.type = Type.SUBTASK;
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
