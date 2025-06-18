package ra.web.dao.course;

import org.springframework.stereotype.Repository;
import ra.web.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class CourseDaoImpl implements ICourseDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Course> findAll() {
        return em.createQuery("from Course", Course.class).getResultList();
    }

    @Override
    public void save(Course course) {
        em.persist(course);
    }

    @Override
    public Course findById(Integer id) {
        return em.find(Course.class, id);
    }

    @Override
    public void update(Course course) {
        em.merge(course);
    }

}
