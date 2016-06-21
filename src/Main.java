import database.DataBase;
import database.TaskEntity;
import gui.GeneralWindow;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String NAME_WINDOW = "MoneyBox";

    public static void main(String[] args) {
        GeneralWindow generalWindow = new GeneralWindow(NAME_WINDOW);
        /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        System.out.println(dateFormat.format( new Date(System.currentTimeMillis())));*/
/*
        DataBase db = new DataBase();
        List<TaskEntity> arr = db.getTaskDAO().getAll();

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).toString());
        }*/
    }
}