package database;

import java.sql.*;

public class MoneyBoxDAO {
    private static final String ID_COLUMN = "id";
    private static final String MONEY_COLUMN = "money";
    private static final String TABLE_NAME = "moneyBox";
    private static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME +
            " ('" + ID_COLUMN + "' integer primary key autoincrement, '" + MONEY_COLUMN + "' double);";
    private Connection connection;

    public MoneyBoxDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean createTableDateBase() {
        try {
            PreparedStatement task = connection.prepareStatement(CREATE_TABLE);
            int result = task.executeUpdate();
            task.close();
            try {
                Statement statement = connection.createStatement();
                ResultSet res = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = '" + 1 + "'");
                String s = res.getString(MONEY_COLUMN);
                statement.close();
                res.close();
            } catch (SQLException e) {
                System.out.println("Таблица " + TABLE_NAME + " пустая, пытаюсь создать копилку");
                e.printStackTrace();
                try {
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " (" +
                            MONEY_COLUMN + ") VALUES (?)");
                    statement.setDouble(1, 0);
                    int res = statement.executeUpdate();
                    statement.close();
                    return true;
                } catch (SQLException ex) {
                    System.out.println("Не удалось созддать копилку");
                    ex.printStackTrace();
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу " + TABLE_NAME);
            e.printStackTrace();
            return false;
        }
    }

    public double getMoney() {
        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = '" + 1 + "'");
            double money = Double.parseDouble(res.getString(MONEY_COLUMN));
            statement.close();
            res.close();
            return money;
        } catch (SQLException e) {
            System.out.println("Не удолась достать деньги с копилки");
            e.printStackTrace();
            return 0;
        }
    }

    public boolean addMoney(double money) {
        try {
            double prevMoney = getMoney();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE " + TABLE_NAME + " SET " +
                            MONEY_COLUMN + " = '" + (prevMoney + money) + "' WHERE " + ID_COLUMN + " = '" + 1 + "'"
            );
            int res = statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось положыть деньги в копилку");
            e.printStackTrace();
            return false;
        }
    }

    public boolean pickMoney(double money) {
        try {
            double prevMoney = getMoney();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE " + TABLE_NAME + " SET " +
                            MONEY_COLUMN + " = '" + (prevMoney - money) + "' WHERE " + ID_COLUMN + " = '" + 1 + "'"
            );
            int res = statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось взять деньги с копилки");
            e.printStackTrace();
            return false;
        }
    }

    public boolean resetMoneyBox() {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE " + TABLE_NAME + " SET " +
                            MONEY_COLUMN + " = '" + 0 + "' WHERE " + ID_COLUMN + " = '" + 1 + "'"
            );
            int res = statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось сбросить копилку");
            e.printStackTrace();
            return false;
        }
    }
}
