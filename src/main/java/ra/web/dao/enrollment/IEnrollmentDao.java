package ra.web.dao.enrollment;

import ra.web.dao.IGenericDao;
import ra.web.dto.enrollment.EnrollmentDTO;
import ra.web.entity.Enrollment;
import ra.web.entity.Status;

import java.util.List;

public interface IEnrollmentDao extends IGenericDao<Enrollment, Integer> {
    List<EnrollmentDTO> getCountStudentByCourse();
    List<EnrollmentDTO> getFiveCourseBestSeller();
    List<Enrollment> searchAndSort(Integer studentId, Status status, String name, int page, int size);
    Long totalPage(Integer studentId, int size, String keyword, Status status);
    boolean isEnrollmentExists(Integer courseId, Integer studentId);

}
