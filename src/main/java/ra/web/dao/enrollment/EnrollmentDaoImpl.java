package ra.web.dao.enrollment;

import com.google.protobuf.Internal;
import org.springframework.stereotype.Repository;
import ra.web.dto.enrollment.EnrollmentDTO;
import ra.web.entity.Enrollment;
import ra.web.entity.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
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
       em.merge(enrollment);
    }


    @Override
    public void save(Enrollment enrollment) {
        em.persist(enrollment);
    }

    @Override
    public Enrollment findById(Integer id) {
        return em.find(Enrollment.class, id);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<EnrollmentDTO> getCountStudentByCourse() {
        return em.createQuery("SELECT new ra.web.dto.enrollment.EnrollmentDTO( " +
                                "e.course.id, " +
                                "e.course.name, " +
                                "e.course.image, " +
                                "e.course.duration," +
                                "COUNT(e.student)) " +
                                "FROM Enrollment e " +
                                "WHERE e.status = 'CONFIRM'" +
                                "GROUP BY e.course.id",
                        EnrollmentDTO.class)
                .getResultList();
    }

    @Override
    public List<EnrollmentDTO> getFiveCourseBestSeller() {
        return em.createQuery("SELECT new  ra.web.dto.enrollment.EnrollmentDTO( " +
                                "e.course.id, " +
                                "e.course.name, " +
                                "e.course.image, " +
                                "e.course.duration, " +
                                "COUNT(e.student)) " +
                                "FROM Enrollment e " +
                                "WHERE e.status = 'CONFIRM'" +
                                "GROUP BY e.course.id, e.course.name, e.course.image, e.course.duration " +
                                "ORDER BY COUNT(e.student) DESC",
                        EnrollmentDTO.class)
                .setMaxResults(5)
                .getResultList();
    }

    // dành cho bên student (user)
    @Override
    public List<Enrollment> searchAndSort(Integer studentId, Status status, String name, int page, int size) {
        StringBuilder jpql = new StringBuilder("SELECT e FROM Enrollment e WHERE 1=1");

        if (status != null) {
            jpql.append(" AND e.status = :status");
        }

        if (name != null && !name.trim().isEmpty()) {
            jpql.append(" AND LOWER(e.course.name) LIKE :name");
        }

        if (studentId != null) {
            jpql.append(" AND e.student.id = :studentId");
        }

        TypedQuery<Enrollment> query = em.createQuery(jpql.toString(), Enrollment.class);

        if (studentId != null) {
            query.setParameter("studentId", studentId);
        }
        if (status != null) {
            query.setParameter("status", status);
        }

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", "%" + name.toLowerCase() + "%");
        }

        query.setFirstResult(page * size).setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Long totalPage(Integer studentId, int size, String keyword, Status status) {
        StringBuilder sql = new StringBuilder("SELECT CEILING(COUNT(e)/:size) FROM Enrollment e JOIN e.course c WHERE c.name LIKE :keyword ");
        if (status != null) {
            sql.append(" AND e.status = :status");
        }
        if (studentId != null) {
            sql.append(" AND e.student.id = :studentId");
        }
        TypedQuery<Integer> query = em.createQuery(sql.toString(), Integer.class);
        query.setParameter("keyword", "%"+keyword+"%").setParameter("size", Long.valueOf(size));

        if (status != null) {
            query.setParameter("status", status);
        }

        if(studentId != null) {
            query.setParameter("studentId", studentId);
        }
        return Long.valueOf(query.getSingleResult());
    }

    @Override
    public boolean isEnrollmentExists(Integer courseId, Integer studentId) {
        String jpql = "SELECT COUNT(e) " +
                "FROM Enrollment e " +
                "WHERE e.course.id = :courseId " +
                "AND e.student.id = :studentId " +
                "AND e.status IN (:status1, :status2)";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("courseId", courseId)
                .setParameter("studentId", studentId)
                .setParameter("status1", Status.WAITING)
                .setParameter("status2", Status.CONFIRM)
                .getSingleResult();

        return count > 0;
    }
}


