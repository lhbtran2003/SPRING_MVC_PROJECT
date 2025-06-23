package ra.web.dto.enrollment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddEnrollmentRequest {
    private Integer studentId;
    private Integer courseId;
}
