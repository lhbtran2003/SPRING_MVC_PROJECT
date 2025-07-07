package ra.web.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.web.dao.course.ICourseDao;
import ra.web.dto.course.AddCourseRequest;
import ra.web.dto.course.CourseDTO;
import ra.web.dto.course.UpdateCourseRequest;
import ra.web.dto.page.PageDto;
import ra.web.entity.Course;
import ra.web.utils.cloudinary.CloudinaryService;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private ICourseDao courseDao;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public void add(AddCourseRequest addCourseRequest) {
        Course newCourse = new Course();
        newCourse.setName(addCourseRequest.getName());
        newCourse.setInstructor(addCourseRequest.getInstructor());
        newCourse.setDuration(addCourseRequest.getDuration());
        MultipartFile imageFile = addCourseRequest.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                newCourse.setImage(cloudinaryService.uploadFile(imageFile));
            } catch (IOException e) {
                throw new RuntimeException("Lỗi khi upload ảnh", e);
            }
        }
        courseDao.save(newCourse);
    }

    @Override
    public Course findById(Integer id) {
        return courseDao.findById(id);
    }

    @Override
    public void update(Integer id, UpdateCourseRequest request) {
        Course course = courseDao.findById(id);
        if (course == null) {
            throw new RuntimeException("Không tìm thấy khóa học");
        }

        course.setInstructor(request.getInstructor());
        course.setDuration(request.getDuration());

        MultipartFile image = request.getImage();
        if (image != null && !image.isEmpty()) {
            try {
                String imageUrl = cloudinaryService.uploadFile(image);
                course.setImage(imageUrl);
            } catch (IOException e) {
                throw new RuntimeException("Upload ảnh thất bại", e);
            }
        }
        courseDao.update(course);

    }

    @Override
    public void delete(Integer id) {
        courseDao.delete(id);
    }

    @Override
    public boolean isExistName(String name) {
        return courseDao.isExistName(name);
    }

    @Override
    public PageDto<CourseDTO> findListByName(String name, int page, int size) {
        PageDto<CourseDTO> pageDto = new PageDto<>();
        List<Course> courseList = courseDao.getListCourseByName(name, page, size);
        List<CourseDTO> courseDTOList = courseList.stream().map(c -> new CourseDTO(
                c.getId(),
                c.getName(),
                c.getInstructor(),
                c.getDuration(),
                c.getImage(),
                c.getCreateAt().toString()
        )).collect(Collectors.toList());
        pageDto.setContent(courseDTOList);
        pageDto.setCurrentPage(page);
        pageDto.setKeyword(name);
        pageDto.setTotalPages(courseDao.totalPage(size,name));
        pageDto.setSize(size);
        return pageDto;
    }

    @Override
    public Course getCourseByName(String name) {
        return courseDao.getCourseByName(name);
    }

    @Override
    public PageDto<CourseDTO> searchAndSort(String keyword, String sortBy, String direction, int page, int size) {
        PageDto<CourseDTO> pageDto = new PageDto<>();

        List<Course> courseList = courseDao.searchAndSort(keyword, sortBy, direction, page, size);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<CourseDTO> courseDTOList = courseList
                .stream()
                .map(c -> new CourseDTO(
                        c.getId(),
                        c.getName(),
                        c.getInstructor(),
                        c.getDuration(),
                        c.getImage(),
                        c.getCreateAt().format(formatter)
                )).collect(Collectors.toList());

        pageDto.setContent(courseDTOList);
        pageDto.setSize(size);
        pageDto.setCurrentPage(page);
        pageDto.setDirection(direction);
        pageDto.setKeyword(keyword);
        pageDto.setSortBy(sortBy);
        pageDto.setTotalPages(courseDao.totalPage(size, keyword));

        return pageDto;
    }

    @Override
    public Long getCount() {
        return courseDao.totalCourse();
    }
}
