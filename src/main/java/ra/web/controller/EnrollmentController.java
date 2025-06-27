package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.dto.enrollment.UpdateEnrollmentRequest;
import ra.web.entity.Course;
import ra.web.entity.Enrollment;
import ra.web.entity.Status;
import ra.web.entity.Student;
import ra.web.service.course.ICourseService;
import ra.web.service.enrollment.IEnrollmentService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    private IEnrollmentService enrollmentService;

    @Autowired
    private ICourseService courseService;


    @PostMapping("/register")
    public String registEnrollment(@RequestParam(value = "courseId") Integer courseId, Model model, HttpSession session) {
        Student s = (Student) session.getAttribute("userLogin");
        if (s == null) {
            return "redirect:/auth/login";
        }
        AddEnrollmentRequest request = new AddEnrollmentRequest();
        request.setCourseId(courseId);
        request.setStudentId(s.getId());
        if (enrollmentService.isExitEnrollment(request)) {
            Course course = courseService.findById(courseId);
            model.addAttribute("course", course);
            model.addAttribute("enrollment", request);
            model.addAttribute("errorRegistEnrollment", "Bạn đã đăng kí khóa học này");
            return "student/courseDetail";
        }
        enrollmentService.add(request);
        return "redirect:/";
    }

    @GetMapping("/history")
    public String enrollmentHistory(Model model,
                                    HttpSession session,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) String status) {

        Student s = (Student) session.getAttribute("userLogin");
        if (s == null) {
            return "redirect:/auth/login";
        }
        Status status1 = null;
        if (status != null) {
            if (status.equals("CONFIRM")) {
                status1 = Status.CONFIRM;
            } else if (status.equals("DENIED")) {
                status1 = Status.DENIED;
            } else if (status.equals("CANCEL")) {
                status1 = Status.CANCEL;
            } else if (status.equals("WAITING")) {
                status1 = Status.WAITING;
            }
        }
        List<Enrollment> history = enrollmentService.searchAndSort(s.getId(), status1, name);
        model.addAttribute("enrollments", history);
        return "student/enrollmentHistory";
    }

    @PostMapping("/cancel")
    public String cancelEnrollment(@RequestParam Integer enrollmentCanceledId, Model model) {
        UpdateEnrollmentRequest request = new UpdateEnrollmentRequest();
        request.setStatus(Status.CANCEL);
        enrollmentService.update(enrollmentCanceledId, request);
        return "redirect:/enrollment/history";
    }

}
