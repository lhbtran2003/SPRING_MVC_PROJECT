package ra.web.utils.validate;

import org.springframework.beans.factory.annotation.Autowired;
import ra.web.entity.Course;
import ra.web.service.course.ICourseService;

public class UniqueCourseNameValidator extends AbstractUniqueValidator<UniqueCourseName, String> {
    @Autowired
    private ICourseService courseService;
    @Override
    protected boolean isValueExisted(String value) {
        return courseService.isExistName(value);
    }
}
