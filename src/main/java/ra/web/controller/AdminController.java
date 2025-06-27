package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.web.dto.course.AddCourseRequest;
import ra.web.dto.course.CourseDTO;
import ra.web.dto.course.UpdateCourseRequest;
import ra.web.dto.enrollment.EnrollmentDTO;
import ra.web.dto.enrollment.UpdateEnrollmentRequest;
import ra.web.dto.student.StudentDTO;
import ra.web.entity.Course;
import ra.web.entity.Status;
import ra.web.entity.Student;
import ra.web.service.course.ICourseService;
import ra.web.service.enrollment.IEnrollmentService;
import ra.web.service.student.IStudentService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ICourseService courseService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IEnrollmentService enrollmentService;

    @GetMapping()
    public String admin(Model model, HttpSession session) {
        model.addAttribute("totalStudent", studentService.getCount());
        model.addAttribute("totalCourse", courseService.getCount());
        List<EnrollmentDTO> list =  enrollmentService.getTotalStudentByCourse();
        model.addAttribute("totalStudentByCourse", list);
        model.addAttribute("bestSeller", enrollmentService.getFiveBestSellerCourse());
        Student student = (Student) session.getAttribute("userLogin");
        model.addAttribute("student", student);
        return "/admin/dashboard/dashboard";
    }

    @GetMapping("/courses")
    public String showListCourse(@RequestParam(required = false) String name,
                                 @RequestParam(required = false, defaultValue = "id") String sortBy,
                                 @RequestParam(required = false, defaultValue = "asc") String order,
                                 Model model
    ) {
        List<Course> courses = courseService.searchAndSort(name, sortBy, order);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<CourseDTO> courseDTOS = courses.stream().map(c -> new CourseDTO(
                c.getId(),
                c.getName(),
                c.getInstructor(),
                c.getDuration(),
                c.getImage(),
                c.getCreateAt().format(formatter)
        )).collect(Collectors.toList());

        model.addAttribute("listCourse", courseDTOS);
        model.addAttribute("name", name);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);

        return "admin/course/listCourse";
    }

    @GetMapping("/courses/add")
    public String showFormAddCourse(Model model) {
        model.addAttribute("addCourseRequest", new AddCourseRequest());
        return "admin/course/formAddCourse";
    }

    @PostMapping("/courses/add")
    public String addCourse(@Valid @ModelAttribute("addCourseRequest") AddCourseRequest request,
                            BindingResult bind,
                            Model model) {
        if (bind.hasErrors()) {
            model.addAttribute("addCourseRequest", request);
            return "admin/course/formAddCourse";
        }
        courseService.add(request);
        return "redirect:/admin/courses";
    }

    @GetMapping("courses/update/{id}")
    public String showFormUpdateCourse(Model model, @PathVariable int id) {
        Course course = courseService.findById(id);
        if (course == null) {
            throw new RuntimeException("Không tìm thấy khóa học có ID: " + id);
        }

        UpdateCourseRequest request = new UpdateCourseRequest();
        request.setInstructor(course.getInstructor());
        request.setDuration(course.getDuration());

        // Đổ dữ liệu cũ ra
        model.addAttribute("courseId", id);
        model.addAttribute("updateCourseRequest", request);

        return "admin/course/formUpdateCourse";

    }

    @PostMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable int id, @ModelAttribute @Valid UpdateCourseRequest request,
                               BindingResult bind, Model model) {
        if (bind.hasErrors()) {
            model.addAttribute("updateCourseRequest", request);
            return "admin/course/formUpdateCourse";
        }
        courseService.update(id, request);
        return "redirect:/admin/courses";
    }

    @PostMapping("courses/delete")
    public String deleteCourse(@RequestParam int id) {
        courseService.delete(id);
        return "redirect:/admin/courses";
    }

    @GetMapping("/students")
    public String showListStudent(Model model,
                                  @RequestParam(required = false) String searchBy,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false, defaultValue = "id") String sortBy,
                                  @RequestParam(required = false, defaultValue = "asc") String order
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<StudentDTO> list = studentService.searchAndSort(searchBy, name, sortBy, order)
                .stream()
                        .map(s -> new StudentDTO(
                                    s.getId(),
                                    s.getName(),
                                    s.getDob().format(formatter),
                                    s.getEmail(),
                                    s.getSex(),
                                    s.getPhone(),
                                    s.getStatus()
                            )).collect(Collectors.toList());
        model.addAttribute("studentList", list);
        return "admin/student/listStudent";
    }

    @PostMapping("/student/change-status")
    public String changeStatus(@RequestParam int id) {
        studentService.changeStatus(id);
        return "redirect:/admin/students";
    }

    @GetMapping("/enrollments")
    public String showListEnrollment (Model model,
                                      @RequestParam(required = false) Integer id,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String status) {
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
        model.addAttribute("enrollments", enrollmentService.searchAndSort(id, status1, name));
        return "admin/enrollment/listEnrollment";
    }

    @PostMapping("/enrollment/approve")
    public String approveEnrollment(@RequestParam Integer enrollmentApprovedId,
                                    @RequestParam String status,
                                    Model model) {
        UpdateEnrollmentRequest request = new UpdateEnrollmentRequest();
        request.setStatus(status.equals("CONFIRM") ? Status.valueOf("CONFIRM") : Status.valueOf(status));
        enrollmentService.update(enrollmentApprovedId, request);
        return "redirect:/admin/enrollments";
    }
}
