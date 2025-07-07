package ra.web.dao.auth;

import ra.web.dto.auth.LoginRequest;
import ra.web.entity.Student;

public interface IAuthDao {
      void save(Student student);
      boolean isExistEmail(java.lang.String email, Integer studentId);
      boolean isExistPhone(java.lang.String phone, Integer studentId);
      Student findByEmail(String email);
}
