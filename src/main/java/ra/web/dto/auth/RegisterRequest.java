package ra.web.dto.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import ra.web.utils.validate.PasswordMatch;
import ra.web.utils.validate.UniqueEmail;
import ra.web.utils.validate.UniquePhone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Component
@Getter
@Setter
@PasswordMatch
public class RegisterRequest {
    @NotBlank(message = "Vui lòng nhập họ tên")
    private String name;

    @NotBlank(message = "Vui lòng nhập email")
    @Email(message = "email không đúng định dạng")
    @UniqueEmail(message = "húuuuuuu")
    private String email;

    @NotBlank(message = "Vui lòng nhập số điện thoại")
    @UniquePhone(message = "hahahahha")
    private String phone;

    @NotNull(message = "Vui lòng chọn giới tính")
    private Boolean sex;

    @NotNull(message = "Vui lòng nhập ngày sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotBlank(message = "Vui lòng nhập mật khẩu")
    private String password;

    @NotBlank(message = "Vui lòng xác nhận mật khẩu")
    private String confirmPassword;
}
