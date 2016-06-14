package database;

import com.sun.org.glassfish.external.statistics.Statistic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticDAO implements DAO<StatisticEntity> {
    private static final String ID_COLUMN = "id";
    private static final String EVENT_COLUMN = "event";
    private static final String DATA_EVENT_COLUMN = "dataEvent";
    private static final String TABLE_NAME = "statistic";
    private static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME +
            " ('" + ID_COLUMN + "' integer primary key autoincrement, '" + EVENT_COLUMN + "' text, '" +
            DATA_EVENT_COLUMN + "' text);";
    private Connection connection;

    public StatisticDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean createTableDateBase() {
        try {
            PreparedStatement statistic = connection.prepareStatement(CREATE_TABLE);
            int res = statistic.executeUpdate();
            statistic.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу " + TABLE_NAME);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StatisticEntity> getAll() {
        List<StatisticEntity> statisticList = new ArrayList<StatisticEntity>();

        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM " + TABLE_NAME);

            while (res.next()) {
                statisticList.add(new StatisticEntity(res.getInt(ID_COLUMN), res.getString(EVENT_COLUMN), res.getString(DATA_EVENT_COLUMN)));
            }
            statement.close();
            res.close();
            return statisticList;
        } catch (SQLException e) {
            System.out.println("Не удалось забрать все с таблицы " + TABLE_NAME);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StatisticEntity getById(int id) {

        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = '" + id + "'");
            StatisticEntity statisticEntity = new StatisticEntity(res.getInt(ID_COLUMN), res.getString(EVENT_COLUMN), res.getString(DATA_EVENT_COLUMN));
            statement.close();
            res.close();
            return  statisticEntity;
        } catch (SQLException e) {
            System.out.println("Не удалось забрать по " + ID_COLUMN + " c таблицы " + TABLE_NAME);
            e.printStackTrace();
            return null;
        }
    }

    public StatisticEntity getByDate(String date) {

        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM " + TABLE_NAME +" WHERE " + DATA_EVENT_COLUMN + " = '" + date + "'");
            StatisticEntity statisticEntity = new StatisticEntity(res.getInt(ID_COLUMN), res.getString(EVENT_COLUMN), res.getString(DATA_EVENT_COLUMN));
            statement.close();
            res.close();
            return statisticEntity;
        } catch (SQLException e) {
            System.out.println("Не удалось забрать по " + DATA_EVENT_COLUMN + " c таблицы " + TABLE_NAME);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(StatisticEntity entity) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " (" +
                    EVENT_COLUMN + ", " + DATA_EVENT_COLUMN + ") VALUES (?, ?)");

            statement.setString(1, entity.getEvent());
            statement.setString(2, entity.getData());

            int res = statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось добавить в таблицу " + TABLE_NAME);
            e.printStackTrace();
            return false;
        }
    }

    /*@Override
    public boolean update(int id, StatisticEntity entity) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE " + TABLE_NAME + " SET " +
                            EVENT_COLUMN + " = '" + entity.getEvent() + "', " + DATA_EVENT_COLUMN + " = '" + entity.getData()
                             + "' WHERE " + ID_COLUMN + " = '" + id + "'"
            );
            int res = statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось внести изменения в таблицу " + TABLE_NAME);
            e.printStackTrace();
            return false;
        }
    }*/

    /*@Override
    public boolean delete(int id) {

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + TABLE_NAME + "WHERE " + ID_COLUMN + " = '" + id + "'");
            int res = statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось удалить по " + ID_COLUMN + " с таблицы " + TABLE_NAME);
            e.printStackTrace();
            return false;
        }
    }*/
}
