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
        Long count = em.createQuery("SELECT COUNT(*) FROM Course c WHERE c.name = :name", Long.class)
                .setParameter("name", name)
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
    public Course getCourseByName(String name) {
        StringBuilder jpql = new StringBuilder("SELECT c FROM Course c");

        if (name != null && !name.trim().isEmpty()) {
            jpql.append(" WHERE c.name LIKE :name");
        }

        TypedQuery<Course> query = em.createQuery(jpql.toString(), Course.class);
        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        return query.getSingleResult();
    }

    @Override
    public List<Course> getListCourseByName(String name, int page, int size) {
        StringBuilder jpql = new StringBuilder("SELECT c FROM Course c");

        if (name != null && !name.trim().isEmpty()) {
            jpql.append(" WHERE c.name LIKE :name");
        }

        TypedQuery<Course> query = em.createQuery(jpql.toString(), Course.class);
        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", "%" + name + "%");
        }
        query.setFirstResult(page * size).setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public List<Course> searchAndSort(String keyword, String sortBy, String direction, int page, int size) {
        StringBuilder jpql = new StringBuilder("SELECT c FROM Course c");
        if (keyword != null && !keyword.trim().isEmpty()) {
            jpql.append(" WHERE c.name LIKE :keyword");
        }
        String sortFiled = sortBy != null && sortBy.trim().equals("id") ? "id" : "name";
        String order = direction != null && direction.trim().equals("asc") ? "asc" : "desc";

        jpql.append(" ORDER BY ").append(sortFiled).append(" ").append(direction);
        TypedQuery<Course> query = em.createQuery(jpql.toString(), Course.class);

        if (keyword != null && !keyword.trim().isEmpty()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }
        query.setFirstResult(page * size).setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Long totalPage(int size, String keyword) {
        String sql = "SELECT CEILING(COUNT(c)/:size) FROM Course c WHERE c.name LIKE :keyword";
        return Long.valueOf(em.createQuery(sql, Integer.class)
                .setParameter("size", Long.valueOf(size))
                .setParameter("keyword", "%" + keyword + "%")
                .getSingleResult());
    }

    @Override
    public Long totalCourse() {
        return em.createQuery("SELECT COUNT(*) FROM Course", Long.class).getSingleResult();
    }
}
