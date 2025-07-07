package ra.web.dto.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@Getter
@Setter
public class LoginRequest {
    @NotBlank (message = "Vui lòng nhập đầy đủ email")
    private java.lang.String email;
    @NotBlank(message = "Mật khẩu không được bỏ trống")
    private java.lang.String password;
}
