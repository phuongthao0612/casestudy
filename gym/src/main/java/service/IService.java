package service;


import java.util.List;

public interface IService<T> {
    List<T> getAll();
    void add(T t);
    void update(T t);
    void delete(int id);


    T getById(int id);
}
