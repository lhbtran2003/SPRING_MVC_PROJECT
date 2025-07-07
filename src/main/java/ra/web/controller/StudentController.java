package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.web.dao.course.ICourseDao;
import ra.web.dto.enrollment.AddEnrollmentRequest;
import ra.web.dto.student.UpadatePasswordRequest;
import ra.web.dto.student.UpdateStudentRequest;
import ra.web.entity.Course;
import ra.web.entity.Student;
import ra.web.service.course.ICourseService;
import ra.web.service.enrollment.IEnrollmentService;
import ra.web.service.student.IStudentService;
import ra.web.utils.validate.update.ChangePasswordValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/")
public class StudentController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ChangePasswordValidator changePasswordValidator;

    @Autowired
    private IEnrollmentService enrollmentService;

    @GetMapping()
    public String home(Model model, HttpSession session,  @RequestParam(required = false) String name) {
        Student student = (Student) session.getAttribute("userLogin");
        model.addAttribute("student", student);
//        model.addAttribute("listCourse", courseService.findByName(name));
        model.addAttribute("listCourse", enrollmentService.getFiveBestSellerCourse());
        model.addAttribute("name", name);
        return "home";
    }

    @GetMapping({"all-course"})
    public String showAllCourse(Model model,
                                HttpSession session,
                                @RequestParam(required = false, defaultValue = "") String name,
                                @RequestParam(required = false, defaultValue = "0") int page) {
        Student student = (Student) session.getAttribute("userLogin");
        model.addAttribute("student", student);
        model.addAttribute("page", courseService.findListByName(name, page, 8));
        model.addAttribute("name", name);
        return "student/listCourse";
    }

    @GetMapping("all-course/detail")
    public String showCourseDetail(Model model, @RequestParam(required = true, value = "name") String name)  {
        model.addAttribute("course", courseService.getCourseByName(name));
        model.addAttribute("enrollment", new AddEnrollmentRequest());
        return "student/courseDetail";
    }

    @GetMapping("/personal-information")
    public String showPersonalInformation(Model model, HttpSession session)  {
        Student s = (Student) session.getAttribute("userLogin");
        if (s != null) {
            UpdateStudentRequest request = new UpdateStudentRequest(
                    s.getId(),
                    s.getName(),
                    s.getPhone(),
                    s.getEmail(),
                    s.getSex(),
                    s.getDob()
            );
            model.addAttribute("student", request);
            return "student/personalInfor";
        }
        return "redirect:/auth/login";
    }

    @PostMapping("/personal-information")
    public String updatePersonalInformation(@Valid @ModelAttribute("student") UpdateStudentRequest request,
                                            BindingResult bind,
                                            Model model, HttpSession session) {
        if (bind.hasErrors()) {
            model.addAttribute("student", request);
            return "student/personalInfor";
        }
        studentService.update(request.getId(), request);
        session.setAttribute("userLogin", studentService.findById(request.getId()));
        return "redirect:/personal-information";
    }

    @GetMapping("/personal-information/change-password")
    public String showFormChangePassword(Model model, HttpSession session) {
        model.addAttribute("changePassword", new UpadatePasswordRequest());
        return "student/changePassword";
    }

    @PostMapping("/personal-information/change-password")
    public String processChangePassword(@Valid @ModelAttribute("changePassword") UpadatePasswordRequest request,
                                        BindingResult bind,
                                        Model model, HttpSession session) {
        Student s = (Student) session.getAttribute("userLogin");
        if (s == null) {
            return "redirect:/auth/login";
        }
        changePasswordValidator.validate(request, bind);

        if (bind.hasErrors()) {
            model.addAttribute("changePassword", request);
            return "student/changePassword";
        }
        studentService.changePassword(s.getId(),request.getOldPassword(),request.getNewPassword());
        return "redirect:/personal-information";
    }


}
