package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public String showListCourse(@RequestParam(required = false) String name,
                                 @RequestParam(required = false, defaultValue = "id") String sortBy,
                                 @RequestParam(required = false, defaultValue = "asc") String order,
                                 Model model
    ) {

        model.addAttribute("listCourse", courseService.searchAndSort(name, sortBy, order));
        model.addAttribute("name", name);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);

        return "course/listCourse";
    }

    @GetMapping("/courses/add")
    public String showFormAddCourse(Model model) {
        model.addAttribute("addCourseRequest", new AddCourseRequest());
        return "course/formAddCourse";
    }

    @PostMapping ("/courses/add")
    public String addCourse(@Valid @ModelAttribute("addCourseRequest") AddCourseRequest request,
                            BindingResult bind,
                            Model model) {
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

        // Đổ dữ liệu cũ ra
        model.addAttribute("courseId", id);
        model.addAttribute("updateCourseRequest", request);
        return "course/formUpdateCourse";

    }

    @PostMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable int id, @ModelAttribute @Valid UpdateCourseRequest request,
                               BindingResult bind,Model model) {
        if (bind.hasErrors()) {
            model.addAttribute("updateCourseRequest", request);
            return "course/formUpdateCourse";
        }
        courseService.update(id, request);
        return "redirect:/admin/courses";
    }

    @PostMapping("courses/delete")
    public String deleteCourse(@RequestParam int id) {
        courseService.delete(id);
        return "redirect:/admin/courses";
    }


}
