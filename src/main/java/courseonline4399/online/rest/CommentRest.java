package courseonline4399.online.rest;

import courseonline4399.online.model.Comment;
import courseonline4399.online.model.Course;
import courseonline4399.online.model.SalesCourse;
import courseonline4399.online.model.User;
import courseonline4399.online.repository.UserRepository;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.CommentService;
import courseonline4399.online.service.CourseService;
import courseonline4399.online.service.SalesCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentRest {
    @Autowired
    CommentService service;
    @Autowired
    AccountService accountService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseService courseService;
    @PostMapping("/save/{courseid}")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment,@PathVariable("courseid") Integer courseid) {
        System.out.println("Received Comment Object: " + comment);
        User account = accountService.getCurrentUserInfo();
//        Comment comment2 = comment;
        Course course = courseService.findById(courseid);
        if (course != null){
            comment.setCourse(course);
        }
        if(account != null ){
            comment.setUser(account);
            Comment savedComment = service.create(comment);
            //return ResponseEntity.ok(comment);
            return ResponseEntity.ok(savedComment);
        }
    return null;
    }
    @GetMapping("/display/{id}")
    public ResponseEntity<List<Comment>> GetAllByCourseIdAndStatusOrderByCreatedDate(@PathVariable int id){
        List<Comment> comments = service.getCommentsByCourseIdSortedByCreatedDate(id);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/comment")
           public ResponseEntity<User> GetCurrentUser(){
        User user = accountService.getCurrentUserInfo();
        return  ResponseEntity.ok(user);
    }
    @PostMapping("/deleteStatus/{commentId}")
        public ResponseEntity<Comment> deleteComment(@RequestBody Comment comment,@PathVariable("commentId") Integer id){
        if(comment !=null){
            comment.setStatus(true);
            Comment commentDel = service.changeStatus(comment);
            return ResponseEntity.ok(commentDel);
        }
        return null;
        }
        @Autowired
    SalesCourseService salesCourseService;




        @GetMapping("/date")
    public ResponseEntity<List<SalesCourse>> getsalecourses(){
        List<SalesCourse> salesCourse = salesCourseService.getSalesCoursesWithEndSaleDateAfterCurrentDateAndStatusSaleIsFalse();
        return ResponseEntity.ok(salesCourse);
        }




}
