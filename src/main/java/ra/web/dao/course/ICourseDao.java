package ra.web.dao.course;

import ra.web.dao.IGenericDao;
import ra.web.entity.Course;

import java.util.List;

public interface ICourseDao extends IGenericDao<Course, Integer> {
    boolean isExistName(String name);

    List<Course> getListCourseByName(String name, int page, int size);
    Course getCourseByName(String name);

    List<Course> searchAndSort(String keyword, String sortBy, String direction, int page, int size);
    Long totalPage(int size, String keyword);

    Long totalCourse();

}
