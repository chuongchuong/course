package courseonline4399.online.controller;

import courseonline4399.online.constants.CourseSalesDTO;
import courseonline4399.online.model.Course;
import courseonline4399.online.model.SalesCourse;
import courseonline4399.online.model.User;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.CourseService;
import courseonline4399.online.service.SalesCourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class Admin_Sales_CoursesController {
    @Autowired
    SalesCourseService salesCourseService;
    @Autowired
    AccountService accountService;
    @Autowired
    CourseService courseService;
    @GetMapping(value = {"/admin/sales"})
    public String Sales(Model model) {
        model.addAttribute("salescourses" ,new SalesCourse());
        return "admin/form/course_sale-form";
    }

    @ModelAttribute("sales")
    public List<CourseSalesDTO> getAllCourse(){
        User user = accountService.getCurrentUserInfo();
        return salesCourseService.getCoursesAndSalesByUserId(user.getId());
    }
    @PostMapping(value = {"/admin/sales/create/{courseid}"})
    public String createSaleCourse(@Valid @ModelAttribute("salescourses") SalesCourse salesCourse, BindingResult bindingResult,
           @RequestParam("courseName")  String course,@PathVariable int courseid,Model model

    ) {
        if (bindingResult.hasErrors()) {
            if(course.isEmpty())
                model.addAttribute("msg","Vui lòng chọn khóa học");
            return "admin/form/course_sale-form";
        }else{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String saleEndDateStr = dateFormat.format(salesCourse.getEndsaledate());
            Date saleEndDate =null;
            try {
                saleEndDate = dateFormat.parse(saleEndDateStr);
                salesCourseService.createSales(courseid,salesCourse.getSalepercent(),saleEndDate);
            } catch (ParseException e) {

                e.printStackTrace();
            }

            return "redirect:/admin/sales";

        }



    }

    @PostMapping(value = {"/admin/sales/update/{salecourseid}"})
    public String updateCourse(@PathVariable int salecourseid,@RequestParam("courseName") String course,@Valid @ModelAttribute("salescourses") SalesCourse salesCourse, BindingResult bindingResult
             ,Model model            ){
        if (bindingResult.hasErrors()) {
            if(course.isEmpty())
                model.addAttribute("msg","Vui lòng chọn khóa học");
            return "admin/form/course_sale-form";
        }else{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            String saleEndDateStr = dateFormat.format(salesCourse.getEndsaledate());
            Date saleEndDate =null;
            try {
                saleEndDate = dateFormat.parse(saleEndDateStr);
                salesCourseService.updateCourse(salecourseid,salesCourse.getSalepercent(),saleEndDate);

            } catch (ParseException e) {

                e.printStackTrace();
            }

            return "redirect:/admin/sales";

        }

    }
    @PostMapping(value = {"/admin/sales/changeStatus/{saleId}"})
    public String changeCourseStatus(@PathVariable int saleId
                               ){
        SalesCourse salesCourse = salesCourseService.findById(saleId);


            Boolean status = salesCourse.getStatussale();
            salesCourseService.ChangeStatus(saleId, !status);

        return "redirect:/admin/sales";
    }
}
