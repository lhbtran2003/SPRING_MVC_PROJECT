package ra.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.web.dto.auth.LoginRequest;
import ra.web.dto.auth.RegisterRequest;
import ra.web.entity.Student;
import ra.web.service.auth.AuthServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private HttpServletRequest request;

    // hiện trang đăng nhập (login)
    @GetMapping("/login")
    public String showFormLogin(Model model, HttpSession session) {
        model.addAttribute("request", new LoginRequest());
        return "auth/formLogin";
    }

    // xu lí đăng nhập
    @PostMapping("/login")
    public String processFormLogin(@Valid @ModelAttribute() LoginRequest loginRequest, BindingResult bind, Model model, HttpSession session) {
        if (bind.hasErrors()) {
            model.addAttribute("request", loginRequest);
            return "auth/formLogin";
        }
        boolean existedEmail = authService.isExistEmail(loginRequest.getEmail());
        if (!existedEmail) {
            model.addAttribute("request", loginRequest);
            model.addAttribute("errorEmail", "Email chưa tồn tại");
            return "auth/formLogin";
        }
        Student s = authService.login(loginRequest);
        if (s == null) {
            model.addAttribute("request", loginRequest);
            model.addAttribute("errorPassword", "Mật khẩu không đúng");
            return "auth/formLogin";
        }
        session.invalidate();
        HttpSession newSession = request.getSession(true);
        newSession.setAttribute("userLogin", s);

        Boolean role = s.getRole();
        if (role) {
            return "redirect:/admin";
        }
        return "redirect:/";
    }


    @PostMapping("/logout")
    public String processLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }

    // hiện trang đăng kí (register)
    @GetMapping("/register")
    public String showFormRegister(Model model) {
        model.addAttribute("request", new RegisterRequest());
        return "auth/formRegister";
    }

    // xu lí đăng nhập
    @PostMapping("/register")
    public String processFormRegister(@Valid @ModelAttribute("request") RegisterRequest request, BindingResult bind, Model model) {
        if (bind.hasErrors()) {
            model.addAttribute("request", request);
            return "auth/formRegister";
        }
        authService.register(request);
        return "home";
    }
}
