package ra.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phoneNumber;
}
