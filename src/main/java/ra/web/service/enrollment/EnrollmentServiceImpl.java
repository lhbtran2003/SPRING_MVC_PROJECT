package ra.web.service.enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.web.dao.course.ICourseDao;
import ra.web.dao.enrollment.IEnrollmentDao;
import ra.web.dao.student.IStudentDao;
import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.dto.enrollment.EnrollmentDTO;
import ra.web.dto.enrollment.UpdateEnrollmentRequest;
import ra.web.dto.page.PageDto;
import ra.web.entity.Course;
import ra.web.entity.Enrollment;
import ra.web.entity.Status;
import ra.web.entity.Student;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
        return enrollmentDao.findAll();
    }

    @Transactional
    @Override
    public void add(AddEnrollmentRequest request) {
       Enrollment newEnrollment = new Enrollment();
       Course course =  courseDao.findById(request.getCourseId());
       Student student = studentDao.findById(request.getStudentId());
       newEnrollment.setCourse(course);
       newEnrollment.setStudent(student);
       newEnrollment.setStatus(Status.WAITING);
       enrollmentDao.save(newEnrollment);
    }

    @Override
    public Enrollment findById(Integer id) {
        return null;
    }

    @Transactional
    @Override
    public void update(Integer id, UpdateEnrollmentRequest request) {
       Enrollment enrollment = enrollmentDao.findById(id);
       if (enrollment != null) {
           enrollment.setStatus(request.getStatus());
           enrollmentDao.update(enrollment);
       }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<EnrollmentDTO> getTotalStudentByCourse() {
        return enrollmentDao.getCountStudentByCourse();
    }

    @Override
    public List<EnrollmentDTO> getFiveBestSellerCourse() {
        return enrollmentDao.getFiveCourseBestSeller();
    }

    @Override
    public boolean isExitEnrollment(AddEnrollmentRequest request) {
        return enrollmentDao.isEnrollmentExists(request.getStudentId(), request.getCourseId());
    }

    @Override
    public PageDto<Enrollment> searchAndSort(Integer studentId, Status status, String name, int page, int size) {
        PageDto<Enrollment> pageDto = new PageDto<>();
        List<Enrollment> enrollmentList = enrollmentDao.searchAndSort(studentId, status, name, page, size);

        pageDto.setContent(enrollmentList);
        pageDto.setCurrentPage(page);
        pageDto.setSize(size);
        pageDto.setKeyword(name);
        pageDto.setSortBy(null);
        pageDto.setTotalPages(enrollmentDao.totalPage(studentId,size,name,status));
        return pageDto;
    }
}
