package ra.web.dto.enrollment;

import lombok.Getter;
import lombok.Setter;
import ra.web.utils.validate.add.UniqueEnrollment;

@Getter
@Setter
@UniqueEnrollment
public class AddEnrollmentRequest {
    private Integer studentId;
    private Integer courseId;
}
