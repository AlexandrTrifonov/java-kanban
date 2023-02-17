package tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {
    protected Integer id;
    protected String name;
    protected String description;
    protected Status status;
    protected Type type;
    protected int duration;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    protected String time;

    public Task (String name, String description) {
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = 100;
        this.time="";
        this.startTime = null;
        this.endTime = getEndTime();
    }
    public Task (Integer id, String name, String description) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = 100;
        this.startTime = null;
        this.endTime = getEndTime();
    }

    public Task (Integer id, String name, String description, int duration, LocalDateTime startTime) {
        this.id=id;
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = getEndTime();
    }

    public Task (String name, String description, int duration) {
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = duration;
        this.startTime = null;
        this.endTime = getEndTime();

    }
/*    public Task (String name, String description, int duration, LocalDateTime startTime) {
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = duration;
        this.startTime = startTime;
        this.endTime = getEndTime();
    }*/

    public Task (String name, String description, int duration, String time) {
        this.name=name;
        this.description=description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = duration;
        this.time=time;
        if (Objects.equals(time, "null")) {
    //    if (time == "null") {
                this.startTime = null;
        } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime startTime = LocalDateTime.parse(time, formatter);
                this.startTime = startTime;
        }
        this.endTime = getEndTime();
    }

    public LocalDateTime getEndTime() {
        Duration dur = Duration.ofMinutes(duration);
        if (startTime != null) {
            return startTime.plus(dur);
        }
        return null;
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

    public void setTime(String time) {
        this.time=time;
    }

    public String getTime() {
        return  time;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime=startTime;
    }

    public LocalDateTime getStartTime() {
        return  startTime;
    }

    @Override
    public String toString() {
        String result;
        result = getId() + "," + Type.TASK + "," + getName() + "," + getStatus() + "," + getDuration() + ",";
        if (startTime == null) {
            result = result + "null";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            result = result + getStartTime().format(formatter);
        }
        result = result + "," + getDescription() + "\n";
        return result;
    }

 /*   public String toString() {
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
        if (startTime == null) {
            sb.append(null);
        }
    /*    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        sb.append(getStartTime().format(formatter));*/
    /*    sb.append(getStartTime());
        sb.append(",");
        sb.append(getDescription());
        sb.append("\n");
        return sb.toString();
    }*/


/*    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }*/


}
