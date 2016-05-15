package database;

public class StatisticEntity {
    private int id;
    private String event;
    private String data;

    public StatisticEntity(int id, String event, String data) {
        this.id = id;
        this.event = event;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public String getData() {
        return data;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setData(String data) {
        this.data = data;
    }
}
