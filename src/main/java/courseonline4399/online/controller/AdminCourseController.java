package courseonline4399.online.controller;


import courseonline4399.online.model.Category;
import courseonline4399.online.model.Course;
import courseonline4399.online.model.User;
import courseonline4399.online.model.Userole;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.CategoryService;
import courseonline4399.online.service.CourseService;
import courseonline4399.online.service.UserRoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminCourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    AccountService accountService;
    @Autowired
    UserRoleService userRoleService;
    @GetMapping(value = {"/admin/courses"})
    public String CourseHome(Model model) {

        model.addAttribute("course", new Course());
        return "admin/form/course-form";
    }

    @PostMapping("/admin/create")
    public ModelAndView CreateCourse( @RequestParam("imageFile") MultipartFile file, @RequestParam("coursename") String name,
                                      @RequestParam("price") String price , @RequestParam("desc") String desc,
                                      @RequestParam("cate") String cate
            , Model model) {

        Course course = courseService.create(name , price , desc , cate , file , model );
        if(course != null){
            return new ModelAndView("redirect:/admin/courses");
        }

        return new ModelAndView("admin/form/course-form");
    }



    @PostMapping("/admin/update/{id}")
    public ModelAndView UpdateCourse(@RequestParam("coursename") String name,
                                     @RequestParam("price") String price , @RequestParam("desc") String desc,
                                     @RequestParam("cate") String cate, @RequestParam(value = "imageFile",required = false) MultipartFile file ,
                                     @PathVariable("id") Integer id, Model model) {
        courseService.update(id,name , price , desc , cate , file ,model);
        return new ModelAndView("redirect:/admin/courses");
    }
    @ModelAttribute("courses")
    public List<Course> getAllCourse(){
        if(getRole()){
            return courseService.findAll();
        }
        User account = accountService.getCurrentUserInfo();
        return courseService.findByUserId(account.getId());
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories(){
        return categoryService.findAll();
    }

    public Boolean getRole(){
        User account = accountService.getCurrentUserInfo();
        Userole userole = userRoleService.findByUserId(account.getId());
        return userole.getRole().getName().equals("ADMIN") ;
    }
}
