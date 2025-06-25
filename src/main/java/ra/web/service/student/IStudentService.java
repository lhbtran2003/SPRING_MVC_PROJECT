package ra.web.service.student;

import ra.web.dto.auth.RegisterRequest;
import ra.web.entity.Course;
import ra.web.entity.Student;
import ra.web.service.IGenericService;

import java.util.List;

public interface IStudentService extends IGenericService<Student,Integer, RegisterRequest, Student> {
    List<Student> searchAndSort(String searchBy, String name, String sortBy, String order);
void changeStatus(Integer id);
    Long getCount();

}
