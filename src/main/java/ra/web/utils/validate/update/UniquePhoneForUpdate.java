package ra.web.utils.validate.update;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePhoneForUpdateValidator.class)
public @interface UniquePhoneForUpdate {
    String message() default "Số điện thoại đã được sử dụng bởi người khác";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}