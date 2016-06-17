package database;

import com.sun.javaws.jnl.LibraryDesc;

import java.io.IOError;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO  implements DAO<TaskEntity>{
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String PRICE_COLUMN = "price";
    private static final String NUMBER_PRIORITY_COLUMN = "numberPriority";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String DATA_ADD_COLUMN = "dataAdd";
    private static final String TABLE_NAME = "tasks";
    private static final String CREATE_TABLE = "create table if not exists " + TABLE_NAME +
            " ('" + ID_COLUMN + "' integer primary key autoincrement, '" + NAME_COLUMN + "' text, '" +
            PRICE_COLUMN + "' double, '" + NUMBER_PRIORITY_COLUMN + "' int, '" + DESCRIPTION_COLUMN + "' text, '" +
            DATA_ADD_COLUMN + "' text);";
    private Connection connection;

    public TaskDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public boolean createTableDateBase() {
        try {
            PreparedStatement task = connection.prepareStatement(CREATE_TABLE);
            int result = task.executeUpdate();
            task.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу " + TABLE_NAME);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TaskEntity> getAll() {
        List<TaskEntity> tasksList = new ArrayList<TaskEntity>();

        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM " + TABLE_NAME);

            while (res.next()) {
                tasksList.add(new TaskEntity(res.getInt(ID_COLUMN), res.getString(NAME_COLUMN),
                        res.getDouble(PRICE_COLUMN), res.getInt(NUMBER_PRIORITY_COLUMN),
                        res.getString(DESCRIPTION_COLUMN), res.getString(DATA_ADD_COLUMN)));
            }
            statement.close();
            res.close();
            return tasksList;
        } catch (SQLException e) {
            System.out.println("Не удалось забрать все с таблицы " + TABLE_NAME);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TaskEntity getById(int id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = '" + id + "'");

            TaskEntity task = new TaskEntity(res.getInt(ID_COLUMN), res.getString(NAME_COLUMN),
                    res.getDouble(PRICE_COLUMN), res.getInt(NUMBER_PRIORITY_COLUMN),
                    res.getString(DESCRIPTION_COLUMN), res.getString(DATA_ADD_COLUMN));
            statement.close();
            res.close();
            return task;
        } catch (SQLException e) {
            System.out.println("Не удалось забрать по " + ID_COLUMN + " c таблицы " + TABLE_NAME);
            e.printStackTrace();
            return null;
        }
    }

    public TaskEntity getByName(String name) {
        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COLUMN + " = '" + name + "'");

            TaskEntity task = new TaskEntity(res.getInt(ID_COLUMN), res.getString(NAME_COLUMN),
                    res.getDouble(PRICE_COLUMN), res.getInt(NUMBER_PRIORITY_COLUMN),
                    res.getString(DESCRIPTION_COLUMN), res.getString(DATA_ADD_COLUMN));
            statement.close();
            res.close();
            return task;
        } catch (SQLException e) {
            System.out.println("Не удалось забрать по " + NAME_COLUMN + " c таблицы " + TABLE_NAME);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean add(TaskEntity entity) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " (" +
            NAME_COLUMN + ", " + PRICE_COLUMN + ", " + NUMBER_PRIORITY_COLUMN + ", " +
            DESCRIPTION_COLUMN + ", " + DATA_ADD_COLUMN + ") VALUES (?, ?, ?, ?, ?)");

            statement.setString(1, entity.getName());
            statement.setDouble(2, entity.getPrice());
            statement.setInt(3, entity.getNumberPriority());
            statement.setString(4, entity.getDescription());
            statement.setString(5, entity.getDataAdd());

            int res = statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось добавить в таблицу " + TABLE_NAME);
            e.printStackTrace();
            return false;
        }
    }

    /*@Override*/
    public boolean update(int id, TaskEntity entity) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE " + TABLE_NAME + " SET " +
                    NAME_COLUMN + " = '" + entity.getName() + "', " + PRICE_COLUMN + " = '" + entity.getPrice() +
                    "', " + NUMBER_PRIORITY_COLUMN + " = '" + entity.getNumberPriority() + "', " +
                    DESCRIPTION_COLUMN  + " = '" + entity.getDescription() + "' WHERE " + ID_COLUMN + " = '" + id + "'"
            );
            int res = statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось внести изменения в таблицу " + TABLE_NAME);
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = '" + id + "'");
            int res = statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Не удалось удалить по " + ID_COLUMN + " с таблицы " + TABLE_NAME);
            e.printStackTrace();
            return false;
        }
    }
}
