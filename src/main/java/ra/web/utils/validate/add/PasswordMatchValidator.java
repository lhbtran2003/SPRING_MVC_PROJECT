package ra.web.utils.validate.add;

import org.springframework.stereotype.Component;
import ra.web.dto.auth.RegisterRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterRequest> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {

    }

    @Override
    public boolean isValid(RegisterRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request.getPassword() == null || request.getConfirmPassword() == null) {
            return true;
        }
        boolean isMatch = request.getPassword().equals(request.getConfirmPassword());

        if (!isMatch) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Xác nhận mật khẩu không trùng khớp")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }
        return isMatch;
    }
}
