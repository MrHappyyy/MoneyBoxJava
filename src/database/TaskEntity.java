package database;

/**
 * Created by mrhappyyy on 15.05.16.
 */
public class TaskEntity {
    private int id;
    private String name;
    private double price;
    private int count;
    private int numberPriority;
    private String description;
    private String dataAdd;

    public TaskEntity(int id, String name, double price, int count, int numberPriority, String description, String dataAdd) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.dataAdd = dataAdd;
        this.numberPriority = numberPriority;
        this.count = count;
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

    public int getNumberPriority() {
        return numberPriority;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public void setNumberPriority(int numberPriority) {
        this.numberPriority = numberPriority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", numberPriority=" + numberPriority +
                ", description='" + description + '\'' +
                ", dataAdd='" + dataAdd + '\'' +
                '}';
    }
}
