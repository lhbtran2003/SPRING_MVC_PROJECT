package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.web.dao.course.ICourseDao;
import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.entity.Course;
import ra.web.entity.Student;
import ra.web.service.course.ICourseService;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {
    @Autowired
    private ICourseDao courseDao;

    @Autowired
    private ICourseService courseService;

    @GetMapping()
    public String home(Model model, HttpSession session,  @RequestParam(required = false) String name) {
        Student student = (Student) session.getAttribute("userLogin");
        model.addAttribute("student", student);
        model.addAttribute("listCourse", courseService.findByName(name));
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping({"all-course"})
    public String showAllCourse(Model model, @RequestParam(required = false) String name) {
        model.addAttribute("listCourse", courseService.findByName(name));
        model.addAttribute("name", name);
        return "student/listCourse";
    }

    @GetMapping("all-course/detail")
    public String showCourseDetail(Model model, @RequestParam(required = true, value = "name") String name)  {
        Course course = courseService.findByName(name).stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
        model.addAttribute("course", course);
        model.addAttribute("enrollment", new AddEnrollmentRequest());
        return "student/courseDetail";
    }

    @GetMapping("/personal-information")
    public String showPersonalInformation(Model model, HttpSession session)  {
        Student s = (Student) session.getAttribute("userLogin");
        if (s != null) {
            model.addAttribute("student", s);
            return "student/personalInfor";
        }
        return "redirect:/auth/login";
    }
}
