package ra.web.dao.student;

import org.springframework.stereotype.Repository;
import ra.web.dto.student.StudentDTO;
import ra.web.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
        em.merge(student);
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Student> searchAndSort(String keyword, String sortBy, String sortDirection, int page, int size) {
        StringBuilder jpql = new StringBuilder("SELECT s FROM Student s ");

        if (keyword != null && !keyword.trim().isEmpty()) {
            jpql.append("WHERE s.name LIKE :keyword or s.email LIKE :keyword or cast(s.id as string )LIKE :keyword ");
        }

        if (sortBy != null && sortDirection != null) {
            String sortField = "id".equals(sortBy) ? "s.id" : "s.name";
            String direction = "desc".equals(sortDirection) ? "DESC" : "ASC";
            jpql.append(" ORDER BY ").append(sortField).append(" ").append(direction);
        }

        // Tạo query
        TypedQuery<Student> query = em.createQuery(jpql.toString(), Student.class);

        // Set parameter nếu có tìm kiếm
        if (keyword != null && !keyword.trim().isEmpty()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }
        query.setFirstResult(page * size).setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Long totalPage(int size, String keyword) {
        String sql = "SELECT CEILING(COUNT(s)/:size) FROM Student s WHERE CAST(s.id AS string) LIKE :keyword OR s.email like :keyword OR s.name like :keyword";
        return Long.valueOf(em.createQuery(sql, Integer.class)
                .setParameter("size", Long.valueOf(size))
                .setParameter("keyword", "%" + keyword + "%")
                .getSingleResult());
    }


    @Override
    public void changeStatus(int id) {
        Student student = findById(id);
        if (student != null) {
            student.setStatus(!student.getStatus());
            em.merge(student);
        }
    }

    @Override
    public Long totalStudent() {
        return em.createQuery("select count(*) from Student", Long.class).getSingleResult();
    }
}
