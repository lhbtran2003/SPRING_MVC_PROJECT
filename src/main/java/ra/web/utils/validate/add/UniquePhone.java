package ra.web.utils.validate.add;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePhoneValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface UniquePhone {
    String message() default "{Số điện thoại đã tồn tại}";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
