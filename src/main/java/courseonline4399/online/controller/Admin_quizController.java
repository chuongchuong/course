package courseonline4399.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Admin_quizController {
    @RequestMapping(value = "/admin/quiz/{id}", method = RequestMethod.GET)
    public String logoutPage(Model model, @PathVariable("id") Integer id) {
            model.addAttribute("coursedetail_id",id);
        return "admin/form/quiz-form.html";
    }
}
