package tasks;

public class Task {
    protected Integer id;
    protected String name;
    protected String description;
    protected Status status;
    protected Type type;
    protected int duration;
    protected String startTime;

    public Task (String name, String description) {
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = 100;
        this.startTime = null;
    }
    public Task (Integer id, String name, String description) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = 100;
        this.startTime = null;
    }

    public Task (Integer id, String name, String description, int duration, String startTime) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = duration;
        this.startTime = startTime;
    }

    public Task (String name, String description, int duration) {
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = duration;
        this.startTime = null;
    }
    public Task (String name, String description, int duration, String startTime) {
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = duration;
        this.startTime = startTime;
    }
    public void setId(Integer id) {
          this.id=id;
    }
    public Integer getId() {
          return id;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public String getDescription() {
        return  description;
    }

    public void setStatus(Status status) {
        this.status=status;
    }

    public Status getStatus() {
        return  status;
    }

    public Type getType() {
        return type;
    }

    public void setDuration(int duration) {
        this.duration=duration;
    }
    public int getDuration() {
        return duration;
    }

    public void setStartTime(String startTime) {
        this.startTime=startTime;
    }

    public String getStartTime() {
        return  startTime;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId());
        sb.append(",");
        sb.append(Type.TASK);
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
        sb.append("\n");
        return sb.toString();
    }


/*    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }*/
    /*
    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
    */
}
