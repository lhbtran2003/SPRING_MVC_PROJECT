package ra.web.utils.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.web.dao.auth.IAuthDao;

@Component
public class UniquePhoneValidator extends AbstractUniqueValidator<UniquePhone, String> {
   @Autowired
   private IAuthDao studentDao;

    @Override
    protected boolean isValueExisted(String value) {
        return studentDao.isExistPhone(value);
    }
}
