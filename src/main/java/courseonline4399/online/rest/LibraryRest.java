package courseonline4399.online.rest;

import courseonline4399.online.constants.CourseDetailsResponse;
import courseonline4399.online.model.*;
import courseonline4399.online.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryRest {
    @Autowired
    AccountService accountService;
    @Autowired
    UserCourseService userCourseService;
    @Autowired
    CourseDetailService videoService;
    @Autowired
    CourseService courseService;

    @GetMapping("/user")
    public User getCurrentUser() {
        return accountService.getCurrentUserInfo();
    }

//    @GetMapping("/user-courses")
//    public List<UserCourse> getUserCourses() {
//        User user = accountService.getCurrentUserInfo();
//        return userCourseService.findUserCoursesByUserId(user.getId());
//    }

    @GetMapping("/course/{id}")
    public Course getCourseById(@PathVariable("id") Integer id) {
        return courseService.findById(id);
    }
    @GetMapping("/course/course/{id}")
    public UserCourse getCourseByUserAndCourse(@PathVariable("id") Integer id) {
        User user = accountService.getCurrentUserInfo();

        return userCourseService.findByCourseId(id, user.getId());
    }
    @PostMapping("/setMail/{id}")
    public ResponseEntity<UserCourse> setMailStatus(@PathVariable("id") Integer courseId) {
        User user = accountService.getCurrentUserInfo();
        UserCourse userCourse = userCourseService.findByCourseId(courseId, user.getId());

        try {
                userCourse.setStatusmail(true);
                UserCourse updatedUserCourse = userCourseService.update(userCourse);

                System.out.println("Set thanh cong=============");

            System.out.println(updatedUserCourse.getStatusmail());
                return ResponseEntity.ok(updatedUserCourse);


        } catch (Exception e) {
            // If an exception occurs during the update
            System.out.println("Set Fail===============");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/course/{id}/videos")
    public List<CourseDetail> getCourseVideos(@PathVariable("id") Integer id) {
        Course course = courseService.findById(id);
        return videoService.findVideosByCourseId(course.getId());
    }

    @GetMapping("/videos/{courseId}")
    public ResponseEntity<List<CourseDetail>> getVideosByCourseId(@PathVariable int courseId) {
        List<CourseDetail> videos = videoService.findVideosByCourseId(courseId);

        if (videos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(videos);
    }
}
