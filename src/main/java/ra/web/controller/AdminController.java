package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.web.dto.course.AddCourseRequest;
import ra.web.dto.course.UpdateCourseRequest;
import ra.web.entity.Course;
import ra.web.service.course.ICourseService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ICourseService courseService;

    @GetMapping("/courses")
    public String showListCourse(Model model) {
        model.addAttribute("listCourse", courseService.findAll());
        return "course/listCourse";
    }

    @GetMapping("/courses/add")
    public String showFormAddCourse(Model model) {
        model.addAttribute("addCourseRequest", new AddCourseRequest());
        return "course/formAddCourse";
    }

    @PostMapping ("/courses/add")
    public String addCourse(@Valid @ModelAttribute("addCourseRequest") AddCourseRequest request, BindingResult bind, Model model) {
        if (bind.hasErrors()) {
            model.addAttribute("addCourseRequest", request);
            return "course/formAddCourse";
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

        model.addAttribute("courseId", id);
        model.addAttribute("updateCourseRequest", request);
        return "course/formUpdateCourse";

    }

    @PostMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable int id, @ModelAttribute @Valid UpdateCourseRequest request,
                               BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {

            return "course/formUpdateCourse";
        }

        courseService.update(id, request);
        redirectAttributes.addFlashAttribute("success", "Cập nhật thành công!");
        return "redirect:/admin/courses";
    }

}
