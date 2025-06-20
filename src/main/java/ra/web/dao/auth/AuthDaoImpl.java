package ra.web.dao.auth;


import org.springframework.stereotype.Repository;
import ra.web.dto.auth.LoginRequest;
import ra.web.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AuthDaoImpl implements IAuthDao {

    @PersistenceContext
    private EntityManager em;



    @Override
    public boolean isExistEmail(String email) {
        Long count = em.createQuery("SELECT COUNT(*) FROM Student s WHERE s.email = :email", Long.class).setParameter("email", email).getSingleResult();
        return count > 0;
    }

    @Override
    public boolean isExistPhone(String phone) {
        Long count = em.createQuery("SELECT COUNT(*) FROM Student s WHERE s.phone = :phone", Long.class).setParameter("phone", phone).getSingleResult();
        return count > 0;
    }

    @Override
    @Transactional
    public void save(Student student) {
        em.persist(student);
    }

    @Override
    public Student login(LoginRequest loginRequest) {
        return em.createQuery("SELECT s FROM Student s WHERE s.email = :email AND s.password = :password", Student.class)
                .setParameter("email", loginRequest.getEmail())
                .setParameter("password", loginRequest.getPassword())
                .getSingleResult();
    }

}
