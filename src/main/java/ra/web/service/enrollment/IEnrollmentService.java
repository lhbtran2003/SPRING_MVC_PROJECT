package ra.web.service.enrollment;

import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.dto.enrollment.EnrollmentDTO;
import ra.web.dto.enrollment.UpdateEnrollmentRequest;
import ra.web.entity.Enrollment;
import ra.web.entity.Status;
import ra.web.service.IGenericService;

import java.util.List;

public interface IEnrollmentService extends IGenericService<Enrollment, Integer, AddEnrollmentRequest, UpdateEnrollmentRequest> {
    List<EnrollmentDTO> getTotalStudentByCourse();
    List<EnrollmentDTO> getFiveBestSellerCourse();
    boolean isExitEnrollment(AddEnrollmentRequest request);
    List<Enrollment> searchAndSort(Integer studentId, Status status, String name);

}
