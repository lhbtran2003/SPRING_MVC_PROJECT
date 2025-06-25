package ra.web.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class StudentDTO {
    private int id;
    private String name;
    private String dob;
    private String email;
    private Boolean sex; // Nam = true (1), Nữ = false (0)
    private String phone;
    private Boolean status; // Active = true (1), Inactive = false (0)


}
