package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.web.dao.course.ICourseDao;

@Controller
@RequestMapping("/")
public class StudentController {
    @Autowired
    private ICourseDao courseDao;

    @GetMapping("all-course")
    public String home(Model model, @RequestParam(required = false) String name) {
        model.addAttribute("listCourse", courseDao.getCourseByName(name));
        model.addAttribute("name", name);
        return "student/listCourse";
    }
}
