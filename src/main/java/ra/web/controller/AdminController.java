package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.web.dto.course.AddCourseRequest;
import ra.web.dto.course.UpdateCourseRequest;
import ra.web.entity.Course;
import ra.web.entity.Student;
import ra.web.service.course.ICourseService;
import ra.web.service.student.IStudentService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ICourseService courseService;

    @Autowired
    private IStudentService studentService;

    @GetMapping("/courses")
    public String showListCourse(@RequestParam(required = false) String name,
                                 @RequestParam(required = false, defaultValue = "id") String sortBy,
                                 @RequestParam(required = false, defaultValue = "asc") String order,
                                 Model model
    ) {

        model.addAttribute("listCourse", courseService.searchAndSort(name, sortBy, order));
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

    @PostMapping ("/courses/add")
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

        return "admin/course/formUpdateCourse :: modal-content";

    }

    @PostMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable int id, @ModelAttribute @Valid UpdateCourseRequest request,
                               BindingResult bind,Model model) {
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
    public String showListStudent(Model model) {
        List<Student> list = studentService.findAll();
        model.addAttribute("studentList",list);
        return "admin/student/listStudent";
    }
}
