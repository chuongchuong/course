package courseonline4399.online.repository;

import courseonline4399.online.model.CourseVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseVideoRepository extends JpaRepository<CourseVideo, Integer> {
    List<CourseVideo> findByCourseId(int courseId);
    CourseVideo findVideoByCourseId(int id);
    List<CourseVideo> findByCourseIdAndChapter(int courseId, int chapter);


}
