package ra.web.dto.enrollment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnrollmentDTO {
    private String courseName;
    private Long totalStudents;
}
