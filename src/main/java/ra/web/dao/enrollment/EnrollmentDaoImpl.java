package ra.web.dao.enrollment;

import org.springframework.stereotype.Repository;
import ra.web.dto.enrollment.EnrollmentDTO;
import ra.web.entity.Enrollment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class EnrollmentDaoImpl implements IEnrollmentDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Enrollment> findAll() {
        return em.createQuery("from Enrollment", Enrollment.class).getResultList();
    }

    @Override
    public void update(Enrollment enrollment) {

    }

    @Transactional
    @Override
    public void save(Enrollment enrollment) {
       em.persist(enrollment);
    }

    @Override
    public Enrollment findById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<EnrollmentDTO> getCountStudentByCourse() {
        return em.createQuery("SELECT EnrollmentDTO( e.course.name,COUNT(e.student)) FROM Enrollment e GROUP BY e.course.name", EnrollmentDTO.class).getResultList();
    }
}
