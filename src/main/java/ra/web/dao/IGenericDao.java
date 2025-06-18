package ra.web.dao;

import java.util.List;

public interface IGenericDao<T,E>{
    List<T> findAll();
    void save(T t);
    T findById(E id);
}
