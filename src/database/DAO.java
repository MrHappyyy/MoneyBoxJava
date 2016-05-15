package database;

import java.util.List;

public interface DAO<T> {
    boolean createTableDateBase();

    List<T> getAll();
    T getById(int id);

    boolean add(T entity);
    boolean update(int id, T entity);
    boolean delete(int id);
}
