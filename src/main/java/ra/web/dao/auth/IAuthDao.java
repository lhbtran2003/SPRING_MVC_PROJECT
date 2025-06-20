package ra.web.dao.auth;

import ra.web.dao.IGenericDao;
import ra.web.dto.auth.LoginRequest;
import ra.web.entity.Student;

public interface IAuthDao {
      void save(Student student);
      boolean isExistEmail(String email);
      boolean isExistPhone(String phone);
      Student login(LoginRequest loginRequest);
}
