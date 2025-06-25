package ra.web.dao.student;

import ra.web.dao.IGenericDao;
import ra.web.entity.Student;

import java.util.List;

public interface IStudentDao extends IGenericDao<Student, Integer> {
    List<Student> searchAndSort(String searchBy, String name, String sortBy, String sortOrder);

    void changeStatus(int id);

    Long totalStudent();
}
