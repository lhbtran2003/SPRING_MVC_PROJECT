package ra.web.utils.validate.update;

import org.springframework.beans.factory.annotation.Autowired;
import ra.web.dao.auth.IAuthDao;
import ra.web.utils.validate.AbstractUniqueValidator;

import javax.validation.ConstraintValidatorContext;

public class UniqueEmailForUpdateValidator extends AbstractUniqueValidator<UniqueEmailForUpdate, Object> {
    @Autowired
    private IAuthDao studentDao;

    @Override
    protected boolean isValueExisted(Object value) {
        return false;
    }

    @Override
    protected boolean isValueExisted(String email, Integer studentId) {
        return studentDao.isExistEmail(email, studentId);
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        return isValidForObject(s, constraintValidatorContext,"email");
    }
}
