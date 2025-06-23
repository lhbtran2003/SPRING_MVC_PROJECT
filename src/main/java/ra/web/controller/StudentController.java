package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.web.dao.course.ICourseDao;
import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.entity.Course;
import ra.web.entity.Student;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class StudentController {
    @Autowired
    private ICourseDao courseDao;

    @GetMapping()
    public String home(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("userLogin");
        model.addAttribute("student", student);
        return "home";
    }

    @GetMapping("all-course")
    public String home(Model model, @RequestParam(required = false) String name) {
        model.addAttribute("listCourse", courseDao.getCourseByName(name));
        model.addAttribute("name", name);
        return "student/listCourse";
    }

    @GetMapping("all-course/detail")
    public String showCourseDetail(Model model, @RequestParam(required = true, value = "name") String name) {
        Course course = courseDao.getCourseByName(name).stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
        model.addAttribute("course", course);
        model.addAttribute("enrollment", new AddEnrollmentRequest());
        return "student/courseDetail";
    }
}
