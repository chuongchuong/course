package courseonline4399.online.controller;

import courseonline4399.online.model.MailInfo;
import courseonline4399.online.model.Support;
import courseonline4399.online.model.User;
import courseonline4399.online.service.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class AdminSupportController {
    @Autowired
    SupportService supportService;
    @Autowired
    AccountService accountService;

    @Autowired
    CourseService courseService;


    @Autowired
    private MailerService mailer;

    @GetMapping(value = {"/admin/support"})
    public String Lib(Model model){
        User user = accountService.getCurrentUserInfo();
        List<Support> list = supportService.findByEmail(user.getEmail());
        model.addAttribute("supportList",list);
        return "admin/form/support-form";
    }

    @PostMapping("/admin/support/{id}")
    public String  Support1( Model model,@PathVariable Integer id,
                              @RequestParam String content)  {
        System.out.println( " đã đi vào đây");
        Support support = supportService.findById(id);
        support.setVideo(content);
        support.setStatus(1);
        support.setUpdatedate(new Date());
        supportService.update(support);
        return "redirect:/admin/support";
    }

}

