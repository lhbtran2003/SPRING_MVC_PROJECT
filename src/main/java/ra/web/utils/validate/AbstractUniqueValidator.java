package ra.web.utils.validate;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public abstract class AbstractUniqueValidator<T extends Annotation, V> implements ConstraintValidator<T, V> {
    protected abstract boolean isValueExisted(V value);
    @Override
    public boolean isValid(V s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || !isValueExisted(s);
    }
}
