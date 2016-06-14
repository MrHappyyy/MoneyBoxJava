package database;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private Connection connection;
    private TaskDAO task;
    private StatisticDAO statistic;
    private MoneyBoxDAO moneyBox;

    private static final String NAME_DATABASE = "MoneyBox";

    public DataBase() {
        onCreateDataBase(NAME_DATABASE);
    }

    public boolean onCreateDataBase(String nameDataBase) {
        try {
            connection = null;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + nameDataBase + ".db");

            task = new TaskDAO(connection);
            if (!task.createTableDateBase())
                System.exit(0);
            statistic = new StatisticDAO(connection);
            if (!statistic.createTableDateBase())
                System.exit(0);
            moneyBox = new MoneyBoxDAO(connection);
            if (!moneyBox.createTableDateBase())
                System.exit(0);
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
    public TaskDAO getTaskDAO() {
        return task;
    }

    public StatisticDAO getStatisticDAO() {
        return statistic;
    }

    public Connection getConnection() {
        return connection;
    }

    public MoneyBoxDAO getMoneyBox() {
        return moneyBox;
    }
}
