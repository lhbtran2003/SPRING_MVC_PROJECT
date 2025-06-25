package ra.web.dao.student;

import org.springframework.stereotype.Repository;
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
    public List<Student> searchAndSort(String searchBy, String keyword, String sortBy, String sortOrder) {
        String jpql = "SELECT s FROM Student s";
        List<String> conditions = new ArrayList<>();

        // Xác định cột cần tìm kiếm
        if (keyword != null && !keyword.trim().isEmpty()) {
            if ("id".equals(searchBy)) {
                conditions.add("CAST(s.id AS string) LIKE :keyword");
            } else if ("email".equals(searchBy)) {
                conditions.add("s.email LIKE :keyword");
            } else { // Mặc định là tìm theo name
                conditions.add("s.name LIKE :keyword");
            }
        }

     
        if (!conditions.isEmpty()) {
            jpql += " WHERE " + String.join(" AND ", conditions);
        }

        // Sắp xếp
        String sortField = "id".equals(sortBy) ? "s.id" : "s.name";
        String direction = "desc".equals(sortOrder) ? "DESC" : "ASC";
        jpql += " ORDER BY " + sortField + " " + direction;

        // Tạo query
        TypedQuery<Student> query = em.createQuery(jpql, Student.class);

        // Set parameter nếu có tìm kiếm
        if (keyword != null && !keyword.trim().isEmpty()) {
            query.setParameter("keyword", "%" + keyword + "%");
        }

        return query.getResultList();
    }



    @Override
    public void changeStatus(int id) {
        Student student = findById(id);
        if (student!=null) {
            student.setStatus(!student.getStatus());
            em.merge(student);
        }
    }

    @Override
    public Long totalStudent() {
        return em.createQuery("select count(*) from Student", Long.class).getSingleResult();
    }
}
