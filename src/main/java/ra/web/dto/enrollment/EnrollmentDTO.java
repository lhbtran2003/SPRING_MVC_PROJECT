package ra.web.dto.enrollment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EnrollmentDTO {
    private Integer courseId;
    private String courseName;
    private String courseImg;
    private Integer courseDuration;
    private Long totalStudents;
}
