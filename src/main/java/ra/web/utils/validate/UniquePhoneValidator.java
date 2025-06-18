package ra.web.utils.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.web.dao.students.IStudentDao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {
   @Autowired
   private IStudentDao studentDao;

    @Override
    public void initialize(UniquePhone constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !studentDao.isExistPhone(s);
    }
}
