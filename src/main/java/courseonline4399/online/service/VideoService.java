//package courseonline4399.online.service;
//
//import courseonline4399.online.model.CourseVideo;
//import courseonline4399.online.repository.CourseRepository;
//import courseonline4399.online.repository.CourseVideoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class VideoService implements CRUD<CourseVideo,Integer> {
//    @Autowired
//    CourseVideoRepository repository;
//    @Override
//    public CourseVideo create(CourseVideo courseVideo) {
//        return null;
//    }
//
//    @Override
//    public CourseVideo update(CourseVideo courseVideo) {
//        return null;
//    }
//
//    @Override
//    public void delete(Integer integer) {
//
//    }
//
//    @Override
//    public List<CourseVideo> findAll() {
//        return null;
//    }
//
//    @Override
//    public CourseVideo findById(Integer integer) {
//        return null;
//    }
//    public List<CourseVideo> findVideosByCourseId(int courseId) {
//
//        return repository.findByCourseId(courseId);
//    }
//    public List<CourseVideo> findVideosByCourseIdAndChapter(int courseId) {
//        return repository.findByCourseIdAndChapter(courseId, 1);
//    }
//
//
//}
