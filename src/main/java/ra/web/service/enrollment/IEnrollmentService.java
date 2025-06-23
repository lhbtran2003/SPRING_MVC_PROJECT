package ra.web.service.enrollment;

import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.entity.Enrollment;
import ra.web.service.IGenericService;

public interface IEnrollmentService extends IGenericService<Enrollment, Integer, AddEnrollmentRequest, Enrollment> {
}
