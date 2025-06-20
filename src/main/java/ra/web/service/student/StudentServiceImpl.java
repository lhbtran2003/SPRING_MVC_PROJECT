package ra.web.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.web.dao.student.IStudentDao;
import ra.web.dto.auth.RegisterRequest;
import ra.web.entity.Student;

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
        return null;
    }

    @Override
    public void update(Integer id, Student request) {

    }

    @Override
    public void delete(Integer id) {

    }
}
