package ra.web.dao;

import org.springframework.stereotype.Repository;
import ra.web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl {
    @PersistenceContext
    private EntityManager entityManager;
    public List<User> findAll() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
    public Optional<User> findById(int id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }
    public void save(User user){
        entityManager.merge(user);
    }
    public void delete(User user){
        entityManager.remove(user);
    }
}
