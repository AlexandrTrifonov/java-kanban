package tasks;

public class Task {
    protected Integer id;
    protected String name;
    protected String description;
    protected Status status;

    public Task (String name, String description, Status status) {
        this.name=name;
        this.description=description;
        this.status=status;
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

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
