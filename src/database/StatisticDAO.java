package database;

import java.sql.Connection;
import java.util.List;

public class StatisticDAO implements DAO<StatisticEntity> {
    private Connection connection;

    public StatisticDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean createTableDateBase() {
        return false;
    }

    @Override
    public List<StatisticEntity> getAll() {
        return null;
    }

    @Override
    public StatisticEntity getById(int id) {
        return null;
    }

    @Override
    public boolean add(StatisticEntity entity) {
        return false;
    }

    @Override
    public boolean update(int id, StatisticEntity entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
