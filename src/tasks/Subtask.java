package tasks;

public class Subtask extends Task{

    private Integer idEpic;

    public Subtask (String name, String description, Status status, Integer idEpic) {
        super(name, description, status);
        this.idEpic=idEpic;
    }

    public void setIdEpic(Integer idEpic) {
        this.idEpic=idEpic;
    }

    public Integer getIdEpic() {
        return idEpic;
    }

    @Override
    public String toString() {
        return "Subtask{" + super.toString() +
                "idEpic=" + idEpic +
                "} ";
    }
}
