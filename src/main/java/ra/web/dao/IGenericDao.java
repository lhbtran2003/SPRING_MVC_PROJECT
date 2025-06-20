package ra.web.dao;

import ra.web.entity.Course;

import java.util.List;

public interface IGenericDao<T,E>{
    List<T> findAll();
    void update(T t);
    void save(T t);
    T findById(E id);
    void delete(E id);
}
