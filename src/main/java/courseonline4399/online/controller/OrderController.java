package courseonline4399.online.controller;

import courseonline4399.online.model.Course;
import courseonline4399.online.model.Order;
import courseonline4399.online.model.OrderDetail;
import courseonline4399.online.model.User;
import courseonline4399.online.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    AccountService accountService;


    @GetMapping(value = {"/order"})
    public String cart(Model model){
        // lịch sử giao dịch
        User user = accountService.getCurrentUserInfo();
        List<Order> list = orderService.findAllIdUser(user.getId());
        list.forEach(order -> {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Status : " + order.getStatusorder());
            // In thêm thông tin khác nếu cần
            System.out.println("--------------------");
        });
        model.addAttribute("orders",list);
        return "user/Order";
    }
    @GetMapping("/order/detail/{id}")
    public String detail(Model model, @PathVariable("id") int id) {
        //model.addAttribute("order", orderService.findById(id));
        //model.addAttribute("orderDetal",orderDetailService.findByOrderId(id));
       // model.addAttribute("user",accountService.getCurrentUserInfo());
        List<Course> list = orderDetailService.findByOrderId(id);
        // list course

        model.addAttribute("listCourse",list);
        return "user/YourOrder";
    }
}
