package courseonline4399.online.controller;

import courseonline4399.online.model.Order;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.CourseService;
import courseonline4399.online.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminHomeController {
    @Autowired
    AccountService acouService;

    @Autowired
    CourseService courseService;

    @Autowired
    OrderService orderService;

    @GetMapping(value = {"/admin/dashboard"})
    public String Home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("countCourse",courseService.findAll().size());
        model.addAttribute("countUser", acouService.findAll().size());
        model.addAttribute("countOrder", orderService.findByStatus1().size());
        model.addAttribute("order", orderService.findAll());
        List<Order> list =  orderService.findAll();
        long tongTien;
        list.forEach(a ->{

        });
        return "admin/home.html";
    }
    @GetMapping(value = {"/form"})
    public String form(){
        return "/admin/form/user-form.html";
    }





//
//    @GetMapping(value = {"/admin/authority"})
//    public String Authority() {
//        return "admin/table/authorities-table";
//    }

    @GetMapping(value = {"/admin/table"})
    public String test() {
        return "admin/table/course-table";
    }

    @RequestMapping(value = "/error/404")
    public String error(){

//        return "layout_user/error404.html";
        return "redirect:/home";
    }
}
