import database.DataBase;
import database.TaskEntity;
import gui.GeneralWindow;

public class Main {
    private static final String NAME_WINDOW = "MoneyBox";

    public static void main(String[] args) {
        new GeneralWindow(NAME_WINDOW);
        /*DataBase db = new DataBase();
        db.getTaskDAO().add(new TaskEntity(1, "sfdsg", 12.0, "fs", "sfdsf"));
        db.getTaskDAO().add(new TaskEntity(1, "sfdsg", 12.0, "fs", "sfdsf"));
        db.getTaskDAO().add(new TaskEntity(1, "sfdsg", 12.0, "fs", "sfdsf"));*/
    }
}
