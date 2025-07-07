package ra.web.utils.validate.add;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.service.enrollment.IEnrollmentService;
import ra.web.utils.validate.AbstractUniqueValidator;

@Component
public class UniqueEnrollmentValidator extends AbstractUniqueValidator<UniqueElements, AddEnrollmentRequest> {
    @Autowired
    private IEnrollmentService enrollmentService;

    @Override
    protected boolean isValueExisted(AddEnrollmentRequest value) {
        return enrollmentService.isExitEnrollment(value);
    }
}
