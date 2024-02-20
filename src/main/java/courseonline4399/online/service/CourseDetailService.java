package courseonline4399.online.service;

import courseonline4399.online.model.Course;
import courseonline4399.online.model.CourseDetail;
import courseonline4399.online.model.CourseVideo;
import courseonline4399.online.repository.CourseDetailResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseDetailService implements CRUD<CourseDetail,Integer> {
    @Autowired
    CourseDetailResponsitory courseDetailResponsitory;
    @Autowired
    CourseService courseService ;

    @Override
    public CourseDetail create(CourseDetail courseDetail) {
        courseDetail.setCreateddate(new Date());
        Course course = courseService.findById(courseDetail.getCourse().getId());
        if(course != null){
            courseDetail.setCourse( course);

        }
        return courseDetailResponsitory.save(courseDetail);
    }

    @Override
    public CourseDetail update(CourseDetail courseDetail) {
        return courseDetailResponsitory.save(courseDetail);
    }

    @Override
    public void delete(Integer integer) {
        CourseDetail courseDetail = courseDetailResponsitory.findById(integer).get();
        if(courseDetail != null){
            courseDetailResponsitory.delete(courseDetail);
        }
    }

    @Override
    public List<CourseDetail> findAll() {
        return courseDetailResponsitory.findAll();
    }

    @Override
    public CourseDetail findById(Integer integer) {
        return courseDetailResponsitory.findById(integer).get();
    }
    public List<CourseDetail> findVideosByCourseIdAndChapter(int courseId) {
        return courseDetailResponsitory.findByCourseIdAndChapter(courseId, 1);
    }
    public List<CourseDetail> findVideosByCourseId(int courseId) {

        return courseDetailResponsitory.findByCourseId(courseId);
    }

    public List<CourseDetail> findCourseDetailsByCourse(Course course){ return courseDetailResponsitory.findCourseDetailsByCourse(course);}
}
