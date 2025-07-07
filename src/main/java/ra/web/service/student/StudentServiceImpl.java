package ra.web.service.student;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.web.dao.student.IStudentDao;
import ra.web.dto.auth.RegisterRequest;
import ra.web.dto.page.PageDto;
import ra.web.dto.student.StudentDTO;
import ra.web.dto.student.UpdateStudentRequest;
import ra.web.entity.Student;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentDao studentDao;

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public void add(RegisterRequest registerRequest) {

    }

    @Override
    public Student findById(Integer id) {
        return studentDao.findById(id);
    }

    @Transactional
    @Override
    public void update(Integer id, UpdateStudentRequest request) {
        Student student = studentDao.findById(id);
        if (student != null) {
            student.setName(request.getName());
            student.setEmail(request.getEmail());
            student.setDob(request.getDob());
            student.setPhone(request.getPhone());
            student.setSex(request.getSex());
            studentDao.update(student);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public PageDto<StudentDTO> searchAndSort(String keyword, String sortBy, String direction, int page, int size) {
        PageDto<StudentDTO> pageDto = new PageDto<>();

        List<Student> studentList= studentDao.searchAndSort(keyword, sortBy, direction, page, size);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<StudentDTO> studentDTOList = studentList
                .stream()
                .map(s -> new StudentDTO(s.getId(),
                        s.getName(),
                        s.getDob().format(formatter),
                        s.getEmail(),
                        s.getSex(),
                        s.getPhone(),
                        s.getStatus()))
                .collect(Collectors.toList());

        pageDto.setContent(studentDTOList);
        pageDto.setCurrentPage(page);
        pageDto.setTotalPages(studentDao.totalPage(size, keyword));
        pageDto.setSize(size);
        pageDto.setKeyword(keyword);
        pageDto.setSortBy(sortBy);
        pageDto.setDirection(direction);

        return pageDto;
    }

    @Transactional
    @Override
    public void changeStatus(Integer id) {
        studentDao.changeStatus(id);
    }

    @Override
    public Long getCount() {
        return studentDao.totalStudent();
    }

    @Transactional
    @Override
    public void changePassword(Integer id, String oldPassword, String newPassword) {
        Student student = studentDao.findById(id);
        if (student != null && BCrypt.checkpw(oldPassword, student.getPassword())) {
            String hashNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
            student.setPassword(hashNewPassword);
            studentDao.update(student);
        }
    }
}
