package ra.web.dto.enrollment;

import lombok.Getter;
import lombok.Setter;
import ra.web.entity.Status;

@Getter
@Setter
public class UpdateEnrollmentRequest {
    private Status status;
}
