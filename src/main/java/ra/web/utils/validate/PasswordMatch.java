package ra.web.utils.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatch {
    String message() default "{Mật khẩu không trùng khớp}";
    Class<?>[] groups() default {};
    // Dùng cho validation nhóm (ít khi dùng ở mức cơ bản)

    Class<? extends Payload>[] payload() default {};
    // Dùng để thêm metadata nếu cần (ít khi dùng)
}
