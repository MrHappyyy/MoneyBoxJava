package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private Connection connection;

    public boolean onCreateDataBase(String nameDataBase) {
        try {
            connection = null;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + nameDataBase);

            TaskDAO task = new TaskDAO(connection);
            task.createTableDateBase();
            StatisticDAO statistic = new StatisticDAO(connection);
            statistic.createTableDateBase();
            return true;
        } catch (SQLException e) {
            System.out.println("Не найден драйвер SQLite");
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
