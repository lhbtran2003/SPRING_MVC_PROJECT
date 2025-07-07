package ra.web.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpadatePasswordRequest {
    @NotBlank(message = "Không được để trống thông tin")
    private String oldPassword;
    @NotBlank(message = "Không được để trống thông tin")
    private String newPassword;
    @NotBlank(message = "Không được để trống thông tin")
    private String confirmPassword;

}
