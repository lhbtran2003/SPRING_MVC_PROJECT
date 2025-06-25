package ra.web.dao.enrollment;

import ra.web.dao.IGenericDao;
import ra.web.dto.enrollment.EnrollmentDTO;
import ra.web.entity.Enrollment;

import java.util.List;

public interface IEnrollmentDao extends IGenericDao<Enrollment, Integer> {
    List<EnrollmentDTO> getCountStudentByCourse();
}
