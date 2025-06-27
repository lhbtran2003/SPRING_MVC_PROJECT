package ra.web.utils.validate;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.service.enrollment.IEnrollmentService;

@Component
public class UniqueEnrollmentValidator extends AbstractUniqueValidator<UniqueElements, AddEnrollmentRequest> {
    @Autowired
    private IEnrollmentService enrollmentService;

    @Override
    protected boolean isValueExisted(AddEnrollmentRequest value) {
        return enrollmentService.isExitEnrollment(value);
    }
}
