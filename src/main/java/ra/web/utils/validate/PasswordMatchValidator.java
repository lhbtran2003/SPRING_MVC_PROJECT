package ra.web.utils.validate;

import org.springframework.stereotype.Component;
import ra.web.dto.auth.RegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterRequest> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(RegisterRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request.getPassword() == null || request.getConfirmPassword() == null) {
            return true;
        }
        return request.getPassword().equals(request.getConfirmPassword());
    }
}
