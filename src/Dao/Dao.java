package Dao;

public interface Dao<T> {
    T save(T T);
    T search(String dni);
    T update(String dni, T t);
    boolean delete(String dni);
}
