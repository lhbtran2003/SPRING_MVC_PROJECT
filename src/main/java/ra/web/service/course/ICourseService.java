package ra.web.service.course;

import ra.web.dto.course.AddCourseRequest;
import ra.web.dto.course.CourseDTO;
import ra.web.dto.course.UpdateCourseRequest;
import ra.web.dto.page.PageDto;
import ra.web.entity.Course;
import ra.web.service.IGenericService;

import java.util.List;

public interface ICourseService extends IGenericService<Course, Integer, AddCourseRequest, UpdateCourseRequest> {
    boolean isExistName(String name);
    PageDto<CourseDTO> findListByName(String name, int page, int size);
    Course getCourseByName(String name);
    PageDto<CourseDTO> searchAndSort(String keyword, String sortBy, String direction, int page, int size);
    Long getCount();

}
