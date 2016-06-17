import database.DataBase;
import database.TaskEntity;
import gui.GeneralWindow;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String NAME_WINDOW = "MoneyBox";

    public static void main(String[] args) {
        new GeneralWindow(NAME_WINDOW);
        /*DataBase db = new DataBase();
        db.getTaskDAO().add(new TaskEntity(1, "prior", 12.5, 2, "sd", "fd"));
        List<TaskEntity> list = db.getTaskDAO().getAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }*/
    }
}