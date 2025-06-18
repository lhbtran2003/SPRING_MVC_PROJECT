package ra.web.service.student;

import ra.web.dto.auth.RegisterRequest;
import ra.web.entity.Student;
import ra.web.service.IGenericService;

public interface IStudentService extends IGenericService<Student,Integer, RegisterRequest, Student> {

}
