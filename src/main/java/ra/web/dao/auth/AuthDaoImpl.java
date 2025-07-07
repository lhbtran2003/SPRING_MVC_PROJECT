package ra.web.dao.auth;


import org.springframework.stereotype.Repository;
import ra.web.dto.auth.LoginRequest;
import ra.web.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class AuthDaoImpl implements IAuthDao {

    @PersistenceContext
    private EntityManager em;



    @Override
    public boolean isExistEmail(java.lang.String email, Integer studentId) {
        StringBuilder jpql = new StringBuilder("SELECT COUNT(*) FROM Student s WHERE s.email = :email");

        if (studentId != null) {
            jpql.append(" AND s.id <> :studentId");
        }
        TypedQuery<Long> query = em.createQuery(jpql.toString(), Long.class);
        query.setParameter("email", email);

        if (studentId != null) {
            query.setParameter("studentId", studentId);
        }
        Long count = query.getSingleResult();
        return count > 0;
    }

    @Override
    public boolean isExistPhone(java.lang.String phone, Integer studentId) {
        StringBuilder jpql = new StringBuilder("SELECT COUNT(*) FROM Student s WHERE s.phone = :phone");

        if (studentId != null) {
            jpql.append(" AND s.id <> :studentId");
        }
        TypedQuery<Long> query = em.createQuery(jpql.toString(), Long.class);
        query.setParameter("phone", phone);

        if (studentId != null) {
            query.setParameter("studentId", studentId);
        }
        Long count = query.getSingleResult();
        return count > 0;
    }

    @Override
    @Transactional
    public void save(Student student) {
                em.persist(student);
    }



    @Override
    public Student findByEmail(String email) {
        try {
            return em.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
