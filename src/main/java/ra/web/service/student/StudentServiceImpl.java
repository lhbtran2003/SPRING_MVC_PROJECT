package ra.web.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.web.dao.student.IStudentDao;
import ra.web.dto.auth.RegisterRequest;
import ra.web.entity.Student;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

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

    @Override
    public void update(Integer id, Student request) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Student> searchAndSort(String searchBy, String name, String sortBy, String order) {
        return studentDao.searchAndSort(searchBy, name, sortBy, order);
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
}
