package ra.web.service;

import java.util.List;

public interface IGenericService<T,E,S,U>{
    List<T> findAll();
    void add(S s);
    T findById(E id);
    void update(E id, U request);
}
