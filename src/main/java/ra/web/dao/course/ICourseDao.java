package ra.web.dao.course;

import ra.web.dao.IGenericDao;
import ra.web.entity.Course;

import java.util.List;

public interface ICourseDao extends IGenericDao<Course, Integer> {
    boolean isExistName(String name);

    List<Course> getCourseByName(String name);

    List<Course> searchAndSort(String name, String sortBy, String sortOrder);

    Long totalCourse();
}
