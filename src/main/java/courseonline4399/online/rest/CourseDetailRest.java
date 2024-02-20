package courseonline4399.online.rest;

import courseonline4399.online.model.CourseDetail;
import courseonline4399.online.service.CourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseDetailRest {
    @Autowired
    CourseDetailService courseDetailService;


    @GetMapping("/rest/course-detail")
    public ResponseEntity<List<CourseDetail>> getAll(){
        List<CourseDetail> datas = courseDetailService.findAll();
        return ResponseEntity.ok(datas);
    }
    @GetMapping("/rest/course-detail/course/{id}")
    public ResponseEntity<List<CourseDetail>> getCourse(@PathVariable("id") Integer id){
        List<CourseDetail> datas = courseDetailService.findVideosByCourseId(id);
        return ResponseEntity.ok(datas);
    }

    @PostMapping("/rest/course-detail")
    public ResponseEntity<CourseDetail> save(@RequestBody CourseDetail courseDetail){
        CourseDetail coursedetail = courseDetailService.create(courseDetail);
        return ResponseEntity.ok(coursedetail);
    }
    @DeleteMapping("/rest/course-detail/{id}")
    public void delete(@PathVariable("id") Integer id){
        courseDetailService.delete(id);
    }
    @GetMapping("/rest/course-detail/{id}")
    public ResponseEntity<CourseDetail>getOne(@PathVariable("id") Integer id){
        CourseDetail datas = courseDetailService.findById(id);
        return ResponseEntity.ok(datas);
    }

    @PostMapping("/rest/course-detail/{id}/{filename}")
    public void update (@PathVariable("filename") String filename ,@PathVariable("id") Integer id ){
        CourseDetail data = courseDetailService.findById(id);
        if(data != null){
            if(data.getExercise().compareTo(filename) == 0){
                data.setExercise(null);
            }
            if(data.getExercisesolution().compareTo(filename) == 0){
                data.setExercisesolution(null);
            }
            courseDetailService.update(data);
        }
    }

}
