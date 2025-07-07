package ra.web.utils.validate.add;

import org.springframework.beans.factory.annotation.Autowired;
import ra.web.service.course.ICourseService;
import ra.web.utils.validate.AbstractUniqueValidator;

public class UniqueCourseNameValidator extends AbstractUniqueValidator<UniqueCourseName, String> {
    @Autowired
    private ICourseService courseService;
    @Override
    protected boolean isValueExisted(String value) {
        return courseService.isExistName(value);
    }
}
