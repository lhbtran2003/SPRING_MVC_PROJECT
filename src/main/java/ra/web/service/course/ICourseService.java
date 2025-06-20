package ra.web.service.course;

import ra.web.dto.course.AddCourseRequest;
import ra.web.dto.course.UpdateCourseRequest;
import ra.web.entity.Course;
import ra.web.service.IGenericService;

import java.util.List;

public interface ICourseService extends IGenericService<Course, Integer, AddCourseRequest, UpdateCourseRequest> {
    boolean isExistName(String name);
    List<Course> findByName(String name);
    List<Course> searchAndSort(String name, String sortBy, String order);

}
