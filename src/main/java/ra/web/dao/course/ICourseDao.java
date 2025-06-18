package ra.web.dao.course;

import ra.web.dao.IGenericDao;
import ra.web.entity.Course;

public interface ICourseDao extends IGenericDao<Course, Integer> {
    void update(Course course);
}
