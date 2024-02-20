package courseonline4399.online.repository;


import courseonline4399.online.model.Course;
import courseonline4399.online.model.CourseDetail;
import courseonline4399.online.model.CourseVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDetailResponsitory extends JpaRepository<CourseDetail, Integer> {
    CourseDetail findByExercise(String exercise);

    List<CourseDetail> findByCourseId(int courseId);
    CourseDetail findVideoByCourseId(int id);
    List<CourseDetail> findByCourseIdAndChapter(int courseId, int chapter);

    List<CourseDetail> findCourseDetailsByCourse(Course course);

}
