package ra.web.dao.student;

import ra.web.dao.IGenericDao;
import ra.web.dto.student.StudentDTO;
import ra.web.entity.Student;

import java.util.List;

public interface IStudentDao extends IGenericDao<Student, Integer> {
    List<Student> searchAndSort(String keyword, String sortBy, String sortDirection, int page, int size);
    Long totalPage(int size, String keyword);

    void changeStatus(int id);

    Long totalStudent();


}
