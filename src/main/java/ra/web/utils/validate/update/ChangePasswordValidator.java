package ra.web.utils.validate.update;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.xml.sax.SAXException;
import ra.web.dao.auth.IAuthDao;
import ra.web.dao.student.IStudentDao;
import ra.web.dto.student.UpadatePasswordRequest;
import ra.web.entity.Student;

import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.IOException;

@Component
public class ChangePasswordValidator implements Validator {
    @Autowired
    private IStudentDao studentDao;

    @Autowired
    private HttpSession session;

    @Override
    public boolean supports(Class<?> clazz) {
        return UpadatePasswordRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UpadatePasswordRequest request = (UpadatePasswordRequest) target;
        Student s = (Student) session.getAttribute("userLogin");
        if (s != null) {
            String oldPassword = studentDao.findById(s.getId()).getPassword(); // mkhau đã hash
            if (request.getOldPassword() !=null && !request.getOldPassword().isEmpty() && !BCrypt.checkpw(request.getOldPassword(), oldPassword)) {
                errors.rejectValue("oldPassword", "oldPassword.not.match", "Mật khẩu cũ không đúng");
            }
            if (request.getNewPassword() !=null && !request.getNewPassword().isEmpty() &&request.getNewPassword().equals(request.getOldPassword())) {
                errors.rejectValue("newPassword", "newPassword.duplicate", "Mật khẩu mới không được trùng mật khẩu cũ");
            }
            if (request.getConfirmPassword() !=null && !request.getConfirmPassword().isEmpty() &&!request.getNewPassword().equals(request.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "confirmPassword.not.match", "Nhập lại mật khẩu mới không khớp");
            }
        }
    }
}
