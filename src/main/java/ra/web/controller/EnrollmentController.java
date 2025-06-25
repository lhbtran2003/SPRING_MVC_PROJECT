package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.entity.Enrollment;
import ra.web.entity.Student;
import ra.web.service.enrollment.IEnrollmentService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    private IEnrollmentService enrollmentService;



    @PostMapping("/register")
    public String registEnrollment(@RequestParam(value = "courseId") Integer courseId, Model model, HttpSession session) {
        AddEnrollmentRequest request = new AddEnrollmentRequest();
        request.setCourseId(courseId);
        Student s = (Student) session.getAttribute("userLogin");
        if (s == null) {
            return "redirect:/auth/login";
        }
        request.setStudentId(s.getId());
        enrollmentService.add(request);
        return "redirect:/";
    }

    @GetMapping("/enrollment-history")
    public String enrollmentHistory(Model model) {
        model.addAttribute("enrollments", enrollmentService.findAll());
        return "student/enrollmentHistory";
    }

    @GetMapping ("enrollmentHistory")
    public String history(@PathVariable(required = false) String name, Model model) {
        return  null;
    }
}
