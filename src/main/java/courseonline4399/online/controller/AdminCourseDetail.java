package courseonline4399.online.controller;

import courseonline4399.online.model.Course;
import courseonline4399.online.model.CourseDetail;
import courseonline4399.online.model.User;
import courseonline4399.online.service.CourseDetailService;
import courseonline4399.online.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class AdminCourseDetail {

    @Autowired
    CourseDetailService courseDetailService;

    @Autowired
    CourseService courseService;

    @GetMapping(value = {"/admin/courses/detail/{id}"})
    public String CourseHome(Model model , @PathVariable("id") Integer id) {
        model.addAttribute("coursedetails", new CourseDetail());
        model.addAttribute("course_id" , id);
        model.addAttribute("coursedetail", courseDetailService.findCourseDetailsByCourse(courseService.findById(id)) );

        return "admin/form/course-detail-form.html";
    }
    @PostMapping(value = {"/admin/course/detail/create/{courseid}"})
    public String createCourseDetail(@PathVariable("courseid") Integer courseid, @Valid @ModelAttribute("coursedetails") CourseDetail courseDetails, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()) {
        model.addAttribute("course_id" , courseid);
        model.addAttribute("coursedetail", courseDetailService.findCourseDetailsByCourse(courseService.findById(courseid)) );

            return "admin/form/course-detail-form.html";
        }else {
            courseDetails.setName(courseDetails.getName());
            courseDetails.setChapter(courseDetails.getChapter());
            courseDetails.setUrl(courseDetails.getUrl());
            courseDetails.setCourse(courseService.findById(courseid));
            courseDetailService.create(courseDetails);
            return "redirect:/admin/courses/detail/" + courseid;
      }


    }
    @PostMapping(value={"/admin/course/detail/update/{id}"})
    public String updateCourseDetail(@PathVariable("id") Integer id ,@Valid @ModelAttribute("coursedetails") CourseDetail courseDetails, BindingResult bindingResult,Model model){
        CourseDetail courseDetail = courseDetailService.findById(id);

        if (bindingResult.hasErrors()) {
             Integer course_id =courseDetail.getCourse().getId();
            model.addAttribute("course_id" , course_id);
            model.addAttribute("coursedetail", courseDetailService.findCourseDetailsByCourse(courseService.findById(course_id)) );

            return "admin/form/course-detail-form.html";
        }else {
            courseDetail.setName(courseDetails.getName());
            courseDetail.setChapter(courseDetails.getChapter());
            courseDetail.setUrl(courseDetails.getUrl());
            courseDetail.setUpdatedate(new Date());
            courseDetailService.update(courseDetail);
            return "redirect:/admin/courses/detail/" + courseDetail.getCourse().getId();
        }

    }

    @GetMapping(value = {"/admin/course/detail/delete/{id}"})
    public String deleteCourseDetail(@PathVariable("id") Integer id){
        String courseid = String.valueOf(courseDetailService.findById(id).getCourse().getId());
        courseDetailService.delete(id);
        return "redirect:/admin/courses/detail/"+courseid;
    }


}
