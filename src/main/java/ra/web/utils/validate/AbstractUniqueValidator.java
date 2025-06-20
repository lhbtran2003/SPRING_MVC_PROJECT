package ra.web.utils.validate;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public abstract class AbstractUniqueValidator<T extends Annotation> implements ConstraintValidator<T, String> {
    protected abstract boolean isValueExisted(String value);
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || !isValueExisted(s);
    }
}
