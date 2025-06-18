package ra.web.dto.course;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
@Setter
@Getter
public class UpdateCourseRequest {
    @NotNull(message = "Không được để trống")
    @Min(value = 10, message = "Khóa học phải có thời lượng tối thiểu 10 giờ")
    private Integer duration;

    @NotBlank(message = "Không được để trống.")
    private String instructor;

    private MultipartFile image;
}
