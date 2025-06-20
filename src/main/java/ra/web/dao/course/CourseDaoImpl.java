package ra.web.dao.course;

import org.springframework.stereotype.Repository;
import ra.web.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Transactional
    @Override
    public void save(Course course) {
        em.persist(course);
    }

    @Override
    public Course findById(Integer id) {
        return em.find(Course.class, id);
    }

    @Transactional
    @Override
    public void update(Course course) {
        em.merge(course);
    }

    @Override
    public boolean isExistName(String name) {
        Long count =  em.createQuery("SELECT COUNT(*) FROM Course c WHERE c.name = :name", Long.class)
                .setParameter("name", name )
                .getSingleResult();
        return count > 0;
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Course course = findById(id);
        if (course != null) {
            em.remove(course);
        }
    }

    @Override
    public List<Course> getCourseByName(String name) {
        StringBuilder jpql = new StringBuilder("SELECT c FROM Course c");

        if (name != null && !name.trim().isEmpty()) {
            jpql.append(" WHERE c.name LIKE :name");
        }

        TypedQuery<Course> query = em.createQuery(jpql.toString(), Course.class);
        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        return query.getResultList();
    }

    @Override
    public List<Course> searchAndSort(String name, String sortBy, String sortOrder) {
        String jpql = "SELECT c FROM Course c";
        List<String> conditions = new ArrayList<String>();

        if (name != null && !name.trim().isEmpty()) {
            conditions.add("c.name LIKE :name");
        }
        if (!conditions.isEmpty()) {
            jpql = jpql + " WHERE " + String.join(" AND ", conditions);
        }

        // Sắp xếp
        String sortField = (sortBy != null && sortBy.equals("name")) ? "c.name" : "c.id";
        String direction = (sortOrder != null && sortOrder.equals("desc")) ? "desc" : "asc";

        jpql = jpql + " ORDER BY " + sortField + " " + direction;

        TypedQuery<Course> query = em.createQuery(jpql, Course.class);
        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        return query.getResultList();
    }
}
