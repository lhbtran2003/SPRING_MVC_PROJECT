package ra.web.service.student;

import ra.web.dto.auth.RegisterRequest;
import ra.web.dto.page.PageDto;
import ra.web.dto.student.StudentDTO;
import ra.web.dto.student.UpdateStudentRequest;
import ra.web.entity.Course;
import ra.web.entity.Student;
import ra.web.service.IGenericService;

import java.util.List;

public interface IStudentService extends IGenericService<Student,Integer, RegisterRequest, UpdateStudentRequest> {
    PageDto<StudentDTO> searchAndSort(String name, String sortBy, String direction, int page, int size);
    void changeStatus(Integer id);
    Long getCount();
    void changePassword(Integer id ,String oldPassword, String newPassword);
}
