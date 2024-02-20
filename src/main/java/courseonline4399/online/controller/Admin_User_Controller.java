package courseonline4399.online.controller;

import courseonline4399.online.model.MailInfo;
import courseonline4399.online.model.User;
import courseonline4399.online.model.Userole;
import courseonline4399.online.repository.UserRepository;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.MailerService;
import courseonline4399.online.service.UserRoleService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class Admin_User_Controller {
    @Autowired
    AccountService accountService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private MailerService mailer;
    @GetMapping(value = {"/admin/users"})
    public String UserHome(Model model) {
        model.addAttribute("userList",accountService.findAll());
        model.addAttribute("table","user-table");
        return "admin/form/user-form";

    }
    @GetMapping(value = "/admin/teachers")
    public String TeacherHome(Model model){
        model.addAttribute("userList",accountService.findTeachers());
        model.addAttribute("table","teacher-table");
        return "admin/form/user-form";
    }
    @GetMapping(value = {"/admin/user/chageStatus/{userId}"})
    public String changeStatus(Model model, @PathVariable("userId") Integer userId ) throws MessagingException {
        User user = accountService.findById(userId);
        Userole userrole = userRoleService.findByUserId(userId);

        User currentUser = accountService.getCurrentUserInfo();
        if(currentUser==user ){
            return "redirect:/admin/users";
        }
        if (!user.getStatus()){
            user.setStatus(true);
            accountService.update(user);
        }else {
    if (userrole.getRole().getName().equals("TEACHER")){

        mailer.send(new MailInfo(user.getEmail(), "Đăng ký thành công",
                "Mail của bạn đã kích hoạt quyền giảng viên"));
    }
            user.setStatus(false);
            accountService.update(user);
        }
        return "redirect:/admin/users";
    }
//    @GetMapping(value = {"/admin/users/seach"})
//    public String seachUsers(Model model, @PathVariable("key") String key){
//        //List<User> users = (List<User>) acouService.findByUsername(key);
//    }

}
