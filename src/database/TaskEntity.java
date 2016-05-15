package database;

/**
 * Created by mrhappyyy on 15.05.16.
 */
public class TaskEntity {
    private int id;
    private String name;
    private double price;
    private String dataAdd;
    private String dataEnd;

    public TaskEntity(int id, String name, double price, String dataAdd, String dataEnd) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dataAdd = dataAdd;
        this.dataEnd = dataEnd;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDataAdd() {
        return dataAdd;
    }

    public String getDataEnd() {
        return dataEnd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDataAdd(String dataAdd) {
        this.dataAdd = dataAdd;
    }

    public void setDataEnd(String dataEnd) {
        this.dataEnd = dataEnd;
    }
}
