package ra.web.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.web.dao.students.IStudentDao;
import ra.web.dto.LoginRequest;
import ra.web.dto.RegisterRequest;
import ra.web.entity.Student;
@Service
public class AuthServiceImpl implements IAuthService{

    @Autowired
    public IStudentDao studentDao;

    @Override
    public void register(RegisterRequest request) {
        Student newStudent = new Student();
        newStudent.setName(request.getName());
        newStudent.setPassword(request.getPassword());
        newStudent.setEmail(request.getEmail());
        newStudent.setPhone(request.getPhone());
        newStudent.setDob(request.getDob());
        newStudent.setSex(request.getSex());
        newStudent.setRole(false);
        studentDao.save(newStudent);
    }

    @Override
    public boolean isExistEmail(String email) {
        return studentDao.isExistEmail(email);
    }

    @Override
    public Student login(LoginRequest loginRequest) {
        return studentDao.login(loginRequest);
    }
}
