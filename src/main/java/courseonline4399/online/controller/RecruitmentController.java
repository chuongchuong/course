package courseonline4399.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecruitmentController {
    @GetMapping("/tuyen-dung")
    public String RecruitmentController(){
        return "user/Recruitment.html";

    }
}
