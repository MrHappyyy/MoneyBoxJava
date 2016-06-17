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
        db.getTaskDAO().add(new TaskEntity(1, "sfsddsg", 1.2, 1, "description", "fs"));
        db.getTaskDAO().add(new TaskEntity(1, "jkn", 12.0, 2, "description", "sfdsf"));
        db.getTaskDAO().add(new TaskEntity(1, "mm.n", 12.0, 3, "description", "sfdsf"));
        List<TaskEntity> list = db.getTaskDAO().getAll();

        for (int i =0; i < list.size();  i++) {
            System.out.println(list.get(i).toString());
        }/*
        System.out.println(db.getTaskDAO().getById(1).toString());
        System.out.println(db.getTaskDAO().getByName("jkn").toString());
        db.getTaskDAO().update(1, new TaskEntity(1, "newName", 25.6, 5, "newdescription", "new date"));
        System.out.println(db.getTaskDAO().getById(1));
        db.getTaskDAO().delete(2);*/
    }
}
