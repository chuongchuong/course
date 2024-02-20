package courseonline4399.online.controller;

import courseonline4399.online.model.User;
import courseonline4399.online.repository.UserRepository;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.FileManagerService;
import courseonline4399.online.service.UploadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class ProfileController {
    @Autowired
    AccountService accountService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UploadService uploadService;
    @Autowired
    FileManagerService fileManagerService;
    @GetMapping(value = {"/profile"})
    public String profileDisplay(Model model){
        User user = accountService.getCurrentUserInfo();
        model.addAttribute("user" , user);
        String errorMessage = null;
        model.addAttribute("mess" , errorMessage);
        return "user/user-profile.html";
    }

    @PostMapping(value = {"/profile/edit"})
    public String editprofile(Model model,@Valid @ModelAttribute("user") User user,
                              BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            String errorMessage = null;
            model.addAttribute("mess" , errorMessage);
            return "user/user-profile.html";
        }else{

            user.setStatus(Boolean.FALSE);
            accountService.update(user);
            return "redirect:/profile";
        }

    }

    @PostMapping(value = {"/profile/changepass"})
    public String changepass(RedirectAttributes redirectAttributes,
                             @RequestParam("passnew") String passnew,
                             @RequestParam("confirmpass") String passconfim) {
        User userdefault = accountService.getCurrentUserInfo();
        User account = accountService.findByUsername(userdefault.getUsername());

        if (account != null) {
            if (passnew.equals(passconfim)) {
                account.setPassword(passwordEncoder.encode(passnew));
                accountService.update(account);
                redirectAttributes.addFlashAttribute("successMessage", "Mật khẩu đã được thay đổi thành công.");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: Mật khẩu xác nhận không khớp.");
            }
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: Người dùng không tồn tại.");
        }

        return "redirect:/profile";
    }

}