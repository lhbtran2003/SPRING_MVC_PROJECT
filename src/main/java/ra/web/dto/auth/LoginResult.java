package ra.web.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.web.entity.Student;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    private LoginStatus status;
    private Student student;
}
