package ra.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
@Getter
@Setter
public class ModelRequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Age cannot be null")
    @Min(value = 16, message = "Age must be greater than or equal to 16")
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Input date cannot be null")
    @Past(message = "Input date must be in the past")
    private LocalDate inputDate;
}
