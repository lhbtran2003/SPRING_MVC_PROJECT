package ra.web.service.auth;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.web.dao.auth.IAuthDao;
import ra.web.dto.auth.LoginRequest;
import ra.web.dto.auth.LoginResult;
import ra.web.dto.auth.LoginStatus;
import ra.web.dto.auth.RegisterRequest;
import ra.web.entity.Student;
@Service
public class AuthServiceImpl implements IAuthService{

    @Autowired
    public IAuthDao studentDao;

    @Override
    public void register(RegisterRequest request) {
        Student newStudent = new Student();
        newStudent.setName(request.getName());
        newStudent.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt( 12)));
        newStudent.setEmail(request.getEmail());
        newStudent.setPhone(request.getPhone());
        newStudent.setDob(request.getDob());
        newStudent.setSex(request.getSex());
        newStudent.setRole(false);
        newStudent.setStatus(true);
        studentDao.save(newStudent);
    }

    @Override
    public boolean isExistEmail(java.lang.String email, Integer studentId) {
        return studentDao.isExistEmail(email,studentId);
    }

    @Override
    public LoginResult login(LoginRequest loginRequest) {
        Student student = studentDao.findByEmail(loginRequest.getEmail());
        if (student == null) {
            return new LoginResult(LoginStatus.UNSUCCESSFUL, null);
        }
        if (!student.getStatus()) {
            return new LoginResult(LoginStatus.ACCOUNT_LOCKED, null);
        }
        if (BCrypt.checkpw(loginRequest.getPassword(), student.getPassword())) {
            return new LoginResult(LoginStatus.SUCCESS, student);
        }
        return new LoginResult(LoginStatus.UNSUCCESSFUL, null);
    }

    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("hahaha", BCrypt.gensalt(12)));
    }
}
