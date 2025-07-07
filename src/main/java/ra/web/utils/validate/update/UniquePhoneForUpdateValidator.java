package ra.web.utils.validate.update;

import org.springframework.beans.factory.annotation.Autowired;
import ra.web.dao.auth.IAuthDao;
import ra.web.utils.validate.AbstractUniqueValidator;

import javax.validation.ConstraintValidatorContext;

public class UniquePhoneForUpdateValidator extends AbstractUniqueValidator<UniquePhoneForUpdate, Object> {
    @Autowired
    private IAuthDao studentDao;

    @Override
    protected boolean isValueExisted(Object value) {
        return false;
    }

    @Override
    protected boolean isValueExisted(String phone, Integer studentId) {
        return studentDao.isExistPhone(phone, studentId);
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        return isValidForObject(s, constraintValidatorContext,"phone");
    }
}
