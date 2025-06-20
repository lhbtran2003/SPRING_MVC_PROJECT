package ra.web.utils.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.web.dao.auth.IAuthDao;

@Component
public class UniqueEmailValidator extends AbstractUniqueValidator<UniqueEmail> {
    @Autowired
    private IAuthDao studentDao;

    @Override
    protected boolean isValueExisted(String value) {
        return studentDao.isExistEmail(value);
    }
}
