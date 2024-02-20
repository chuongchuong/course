package courseonline4399.online.controller;

import courseonline4399.online.model.User;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.MailerService;
import courseonline4399.online.service.UploadService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class ForgetpassController {
	@Autowired
	private MailerService mailer;
	@Autowired
	AccountService accountService;

	@GetMapping(value = {"/forgotpass"})
	public String forget(){

		return "user/forgotpass.html";
	}

	@PostMapping(value = {"/forgotpassword"})
	public String snedmailpass(@RequestParam("email") String email, Model model, User user){

		try {
//			String tokenResetpass = UUID.randomUUID().toString();
//			user.setTokenResetpass(tokenResetpass);
//			accountService.create(user);
			User findemail = accountService.findByEmail(email);

			if (findemail == null) {
				model.addAttribute("mess", "Không tìm thấy người dùng với địa chỉ email này.");
				return "user/forgotpass.html";
			}

			else {


				String tokenResetpass = UUID.randomUUID().toString();

				accountService.updateTokenByEmail(email,tokenResetpass);
				mailer.send(email,"Nhập lại mật khẩu","Click the link to reset your password: http://localhost:8080/resetpass?token="+tokenResetpass);
				model.addAttribute("mess","Liên kết đặt lại mật khẩu đã được gửi đến email của bạn.");

				System.out.println("gui okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
				return "user/forgotpass.html";
			}



		} catch (MessagingException e) {
			throw new RuntimeException(e);

		}

	}

}