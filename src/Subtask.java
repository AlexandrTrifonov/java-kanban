public class Subtask extends Task{

    Integer idEpic;

    public Subtask (String name, String discription, String status, Integer idEpic) {
        super(name, discription, status);
        this.idEpic=idEpic;
    }
}
