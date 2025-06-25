package ra.web.dto.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class CourseDTO {
    private Integer id;
    private String name;
    private String instructor;
    private Integer duration;
    private String image;
    private String createAt;
}
