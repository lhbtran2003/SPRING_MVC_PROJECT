package ra.web.dao.students;

import ra.web.dao.IGenericDao;
import ra.web.dto.LoginRequest;
import ra.web.entity.Student;

public interface IStudentDao extends IGenericDao<Student, Integer> {
      boolean isExistEmail(String email);
      boolean isExistPhone(String phone);
      Student login(LoginRequest loginRequest);
}
