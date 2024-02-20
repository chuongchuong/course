package courseonline4399.online.rest;

import courseonline4399.online.model.Course;
import courseonline4399.online.model.SalesCourse;
import courseonline4399.online.model.User;
import courseonline4399.online.model.UserCourse;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.CourseService;
import courseonline4399.online.service.SalesCourseService;
import courseonline4399.online.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseRest {
    @Autowired
    AccountService accountService;
    @Autowired
    UserCourseService userCourseService;
    @Autowired
    CourseService service;
    @Autowired
    SalesCourseService salesCourseService;
    @Autowired
    CourseService courseService;

    @GetMapping("/rest/courses")
    public ResponseEntity<List<Course>> getAll(){
        List<Course> courses = service.findAll();
        return  ResponseEntity.ok(courses);
    }
    @GetMapping("/rest/courses/admin/{id}")
    public Course get(@PathVariable("id") Integer id){
        System.out.println("id của khoá học :"+id);
        Course course = service.findById(id);

        return course;
    }
    @GetMapping("/rest/courses/{id}")
    public Course getOne(@PathVariable("id") Integer id){
        System.out.println("id của khoá học :"+id);
        Course course = service.findById(id);
        // check người học đã mua khoá học trước đó chưa
        // lấy user
        User user = accountService.getCurrentUserInfo();
        List<UserCourse> userCourses = userCourseService.findUserCoursesByUserId2(user.getId());

        // get course của người học
        List<Course> courses = new ArrayList<>();
        for(UserCourse c : userCourses){
            Course course1 = courseService.findById(c.getCourse().getId());
            courses.add(course1);
        }
        // so sánh
        for(Course a : courses){
            if(a.getId()== course.getId()){
                return null;
            }
        }
        //Tìm khóa học có flash sale

        Page<SalesCourse> salesCoursePage = salesCourseService.findNewestSalesByCourseIdAndStatusSaleAndEndSaleDate(course.getId(),Pageable.unpaged());
        for (SalesCourse b : salesCoursePage){
            if(course.getId()==b.getCourse().getId()){
                float giaMoi = course.getPrice()* (1 - b.getSalepercent() / 100);
                course.setPrice(giaMoi);
            }
        }
        return course;
    }

    @PostMapping("/rest/courses")
    public ResponseEntity<Course> create(@RequestBody Course course){
        Course course1 = service.create(course);
        if(course1==null){
            return  ResponseEntity.ofNullable(course1);
        }
        return   ResponseEntity.ok(course);
    }

@PostMapping("/rest/courses/{id}/{status}")/* status = 1 delete image*/
public ResponseEntity<Course> update( @PathVariable("id") Integer id
        , @PathVariable("status") Integer status){

    switch (status) {
        case 1 -> {
            Course course = service.findById(id);
            if(course != null){
                course.setImage(null);
                service.create(course);
                return  ResponseEntity.ok(course);
            }
        }
        default -> { return null; }
    }
    return null;
}

    @DeleteMapping("/rest/courses/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }

        @PostMapping("/rest/courses/{id}")
    public ResponseEntity<Course> update(@RequestBody Course course, @PathVariable("id") Integer id){
        Course course1 = service.update(course);
        if(course1==null){
            return ResponseEntity.ofNullable(course1);
        }
        return  ResponseEntity.ok(course);
    }
//    @Autowired
//    SalesCourseService courseSalesService;
//    @GetMapping("/rest/courses/test/{userId}")
//    public List<Object[]> getCoursesAndSalesByUserId(@PathVariable Integer userId) {
//        return courseSalesService.getCoursesAndSalesByUserId(userId);
//    }
}
