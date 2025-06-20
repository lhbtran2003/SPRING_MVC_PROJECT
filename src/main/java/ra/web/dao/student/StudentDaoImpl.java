package ra.web.dao.student;

import org.springframework.stereotype.Repository;
import ra.web.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
@Repository
public class StudentDaoImpl implements IStudentDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Student> findAll() {
        return em.createQuery("from Student", Student.class).getResultList();
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void save(Student student) {

    }

    @Override
    public Student findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
