package ra.web.dto.enrollment;

import lombok.Getter;
import lombok.Setter;
import ra.web.utils.validate.UniqueEnrollment;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@UniqueEnrollment
public class AddEnrollmentRequest {
    private Integer studentId;
    private Integer courseId;
}
