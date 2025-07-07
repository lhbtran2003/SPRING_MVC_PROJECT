package ra.web.dto.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import ra.web.utils.validate.add.PasswordMatch;
import ra.web.utils.validate.add.UniqueEmail;
import ra.web.utils.validate.add.UniquePhone;

import javax.validation.constraints.*;
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
    @UniqueEmail(message = "Email đã được sử dụng")
    private String email;

    @NotBlank(message = "Vui lòng nhập số điện thoại")
    @Pattern(regexp = "^(0|\\+84)[0-9]{9}$", message = "Số điện thoại không hợp lệ")
    @UniquePhone(message = "Số điện thoại đã được sử dụng")
    private String phone;

    @NotNull(message = "Vui lòng chọn giới tính")
    private Boolean sex;

    @NotNull(message = "Vui lòng nhập ngày sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Ngày sinh không hợp lệ")
    private LocalDate dob;

    @NotBlank(message = "Vui lòng nhập mật khẩu")
    private String password;

    @NotBlank(message = "Vui lòng xác nhận mật khẩu")
    private String confirmPassword;
}
