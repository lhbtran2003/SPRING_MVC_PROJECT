package ra.web.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ra.web.utils.validate.update.UniqueEmailForUpdate;
import ra.web.utils.validate.update.UniquePhoneForUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@UniquePhoneForUpdate
@UniqueEmailForUpdate
public class UpdateStudentRequest {
    private Integer id;

    @NotBlank(message = "Tên không được để trống")
    private String name;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    @NotBlank(message = "Email không được để trống")

    private String email;

    @NotNull(message = "Chọn một giới tính")
    private Boolean sex;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate dob;
}
