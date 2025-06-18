package ra.web.service.course;

import ra.web.dto.course.AddCourseRequest;
import ra.web.dto.course.UpdateCourseRequest;
import ra.web.entity.Course;
import ra.web.service.IGenericService;

public interface ICourseService extends IGenericService<Course, Integer, AddCourseRequest, UpdateCourseRequest> {

}
