package ra.web.utils.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePhoneValidator.class)
@Target(ElementType.FIELD)

public @interface UniquePhone {
    String message() default "{Số điện thoại đã tồn tại}";
    Class<?>[] groups() default {};
    // Dùng cho validation nhóm (ít khi dùng ở mức cơ bản)

    Class<? extends Payload>[] payload() default {};
    // Dùng để thêm metadata nếu cần (ít khi dùng)
}
