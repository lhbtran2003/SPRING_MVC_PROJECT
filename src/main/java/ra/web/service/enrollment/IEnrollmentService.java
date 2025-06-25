package ra.web.service.enrollment;

import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.dto.enrollment.EnrollmentDTO;
import ra.web.entity.Enrollment;
import ra.web.service.IGenericService;

import java.util.List;

public interface IEnrollmentService extends IGenericService<Enrollment, Integer, AddEnrollmentRequest, Enrollment> {
    List<EnrollmentDTO> getEnrollmentsByEnrollmentId();
}
