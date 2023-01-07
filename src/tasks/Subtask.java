package tasks;

public class Subtask extends Task{

    private Integer idEpic;

    public Subtask (String name, String description, Integer idEpic) {
        super(name, description);
        this.idEpic=idEpic;
        this.status = Status.NEW;
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
                "} ";
    }
/*    @Override
    public String toString() {
        return "Subtask{" + super.toString() +
                "idEpic=" + idEpic +
                "} ";
    }*/
}
