package ra.web.service.auth;


import ra.web.dto.auth.LoginRequest;
import ra.web.dto.auth.LoginResult;
import ra.web.dto.auth.RegisterRequest;
import ra.web.entity.Student;

public interface IAuthService {
    LoginResult login(LoginRequest loginRequest);
    void register(RegisterRequest registerRequest);
    boolean isExistEmail(String email, Integer studentId);
}
