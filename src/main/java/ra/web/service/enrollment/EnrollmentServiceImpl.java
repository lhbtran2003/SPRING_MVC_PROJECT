package ra.web.service.enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.web.dao.course.ICourseDao;
import ra.web.dao.enrollment.IEnrollmentDao;
import ra.web.dao.student.IStudentDao;
import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.dto.enrollment.EnrollmentDTO;
import ra.web.entity.Course;
import ra.web.entity.Enrollment;
import ra.web.entity.Student;
import ra.web.service.course.ICourseService;
import ra.web.service.student.IStudentService;

import java.util.Collections;
import java.util.List;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService {
    @Autowired
    private IEnrollmentDao enrollmentDao;
    @Autowired
    private IStudentDao studentDao;
    @Autowired
    private ICourseDao courseDao;

    @Override
    public List<Enrollment> findAll() {
        return Collections.emptyList();
    }

    @Override
    public void add(AddEnrollmentRequest request) {
       Enrollment newEnrollment = new Enrollment();
       Course course =  courseDao.findById(request.getCourseId());
       Student student = studentDao.findById(request.getStudentId());
       newEnrollment.setCourse(course);
       newEnrollment.setStudent(student);
       enrollmentDao.save(newEnrollment);
    }

    @Override
    public Enrollment findById(Integer id) {
        return null;
    }

    @Override
    public void update(Integer id, Enrollment request) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByEnrollmentId() {
        return enrollmentDao.getCountStudentByCourse();
    }
}
