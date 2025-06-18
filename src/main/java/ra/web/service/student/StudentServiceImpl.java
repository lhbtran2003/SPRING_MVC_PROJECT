package ra.web.service.student;

import org.springframework.stereotype.Service;
import ra.web.entity.Student;

import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Override
    public List<Student> findAll() {
        return Collections.emptyList();
    }
}
