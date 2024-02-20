package courseonline4399.online.controller;

import com.nimbusds.oauth2.sdk.util.JWTClaimsSetUtils;
import courseonline4399.online.Util.JwtTokenUtil;
import courseonline4399.online.model.User;
import courseonline4399.online.service.AccountService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ResetPassController {
    @Autowired
    AccountService accountService;
    @Autowired
    PasswordEncoder pe;

    @GetMapping(value = {"/resetpass"})
    public String forget(Model model ,@RequestParam("token") String token){
        System.out.println(token);

        model.addAttribute("token",token);
        return "user/resetpass.html";
    }
    @PostMapping("/resetpassword")
    public String resetPassword(@ModelAttribute("user") User usertoken,
                                @RequestParam("token") String token,
                                @RequestParam("password") String password,
                                Model model) {



        User user = accountService.finBytoken(token);

        if (user != null ) {

            user.setPassword(pe.encode(password));
            user.setToken(null);
            accountService.create(user);
            System.out.println(user.getFullname());
            model.addAttribute("msg", "Thay đổi pass thành công vui lòng đăng nhập");
            return "user/login.html";
        } else {
            // Token is invalid or expired
            model.addAttribute("error", "Invalid or expired token.");
            return "error";
        }
    }



}
