package ra.web.dto.course;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ra.web.utils.validate.UniqueCourseName;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AddCourseRequest {
    @NotBlank(message = "Không được để trống.")
    @UniqueCourseName()
    private String name;

    @NotNull(message = "Không được để trống")
    @Min(value = 10, message = "Khóa học phải có thời lượng tối thiểu 10 giờ")
    private Integer duration;

    @NotBlank(message = "Không được để trống.")
    private String instructor;

    private MultipartFile image;
}
