package courseonline4399.online.controller;

import courseonline4399.online.model.*;
import courseonline4399.online.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    AccountService accountService;

    @Autowired
    UserCourseService userCourseService;

    @Autowired
    CourseService courseService;

    @Autowired
    VoucherService voucherService;

    @Autowired
    UsedVoucherService usedVoucherService;

    @Autowired
    OrderService orderService;

    @GetMapping(value = {"/cart"})
    public String cart(Model model){
        model.addAttribute("auth",accountService.getCurrentUserInfo());
        //check người học đã mua khoá học trước đó chưa
        model.addAttribute("vouchers", voucherService.getAllVouchers());
        return "user/ShoppingCart";
    }
    @GetMapping(value = {"/checkout"})
    public String checkout(){
        return "user/Check_out.html";
    }

    @PostMapping(value = {"/cart/checkVourcher"})
    public String register(@RequestParam("voucherCode") String voucherCode , Model model){
        // kiểm tra voucher code có hơp lệ
        List<Voucher> lists = voucherService.getAllVouchers();
        model.addAttribute("message","Vourcher không hợp lệ");


        lists.forEach(v->{
            System.out.println("mã voucher   "+v.getVoucherCode());
            if(v.getVoucherCode().equals(voucherCode)){
                Order order = orderService.findByUserIdAndVoucher(accountService.getCurrentUserInfo().getId(),v.getId());
                if(order!=null){
                    if(order.getVoucher()!=0 && order.getStatusorder()!=1){
                        model.addAttribute("message","Vourcher hợp lệ");
                        if(v.getPrice()!=0) {
                            model.addAttribute("giam", v.getPrice());
                            model.addAttribute("percent",0);
                        }else{
                            model.addAttribute("giam", 0);
                            model.addAttribute("percent", v.getPercent());
                        }
                        model.addAttribute("idVoucher",v.getId());
                    }else{
                        model.addAttribute("message","Vourcher đã sử dụng rồi!");
                    }

                }else{
                    model.addAttribute("message","Vourcher hợp lệ");

                    if(v.getPrice()!=0) {
                        model.addAttribute("giam", v.getPrice());
                        model.addAttribute("percent",0);
                    }else{
                        model.addAttribute("giam", 0);
                        model.addAttribute("percent", v.getPercent());
                    }
                    model.addAttribute("idVoucher",v.getId());
                }
            }

        });


        return "user/ShoppingCart";
    }
}
