package courseonline4399.online.controller;

import courseonline4399.online.model.MailInfo;
import courseonline4399.online.model.User;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.JWT;
import courseonline4399.online.service.MailerService;
import jakarta.mail.MessagingException;

import jakarta.validation.Valid;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegistrationController {

	@Autowired
	private MailerService mailer;

	@Autowired
	AccountService accountService;
	@Autowired
	private JWT jwt;
	@Autowired
	PasswordEncoder passwordEncoder;
	@GetMapping(value = {"/registration"})
	public String registration(Model model){

		model.addAttribute("user", new User());
		return "user/registration.html";
	}
	//	@RequestParam String email, @RequestParam String fullname,
//	@RequestParam String phone, @RequestParam String username,
//	@RequestParam String password, @RequestParam String confirm
	@PostMapping(value = {"/register"})
	public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,  @RequestParam String confirm,Model model) throws MessagingException {

		if (bindingResult.hasErrors()) {
			return "user/registration.html";
		}
		else{
			User user_email  = accountService.findByEmail(user.getEmail());
			if (user_email != null && user.getEmail() != null && user.getEmail().equals(user_email.getEmail())) {

				model.addAttribute("msg" , "Email đã tồn tại");
				return "user/registration.html";
			}if (confirm.isEmpty()) {
				model.addAttribute("msg" , "Xác nhận mật khẩu thất bại");
				return "user/registration.html";
			}
			if(user.getPassword().equals(confirm)){

				Map<String,Object> data = new HashMap<>();
				data.put("email" , user.getEmail());
				data.put("fullname" , user.getFullname());
				data.put("phone" , user.getPhonenumber());
				data.put("username" , user.getUsername());
				data.put("password" , passwordEncoder.encode(user.getPassword()));

				mailer.send(new MailInfo(user.getEmail(), "Xac nhan mk",
						"Click here: http://localhost:8080/registerStudentAccount?token=" + jwt.createJWT(data)));
				model.addAttribute("msg" , "Vui lòng kiểm tra email của bạn !");
			}else{
				model.addAttribute("msg" , "Xác nhận mật khẩu thất bại");
			}
		}


		return "user/registration.html";
	}

	@GetMapping(value = {"/registerStudentAccount"})
	public String register(@RequestParam String token , Model model){
		if(token!=null){
			if(jwt.checkSign(token)){
				accountService.create(token);
				model.addAttribute("fullname" , getValue(token,"fullname"));
				model.addAttribute("name" , getValue(token,"username"));

			}else{
				model.addAttribute("msg" , "Quá hạn đăng kí , vui lòng đăng kí lại.");
			}
		}else{
			model.addAttribute("msg" , "Lỗi , vui lòng đăng kí lại");
		}
		return "user/checkRegister.html";
	}
	//Dky giảng viên( Người đăng khóa học)
	@PostMapping(value = {"/registerTeacher"})
	public String registerTeacher(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,  @RequestParam String confirm,Model model) throws MessagingException {

		if (bindingResult.hasErrors()) {
			return "user/registration.html";
		}
		else{
			User user_email  = accountService.findByEmail(user.getEmail());
			if (user_email != null && user.getEmail() != null && user.getEmail().equals(user_email.getEmail())) {
				model.addAttribute("msg" , "Email đã tồn tại");
				return "user/registration.html";
			}if (confirm.isEmpty()) {
				model.addAttribute("msg" , "Xác nhận mật khẩu thất bại");
				return "user/registration.html";
			}
			if(user.getPassword().equals(confirm)){

				Map<String,Object> data = new HashMap<>();
				data.put("email" , user.getEmail());
				data.put("fullname" , user.getFullname());
				data.put("phone" , user.getPhonenumber());
				data.put("username" , user.getUsername());
				data.put("password" , passwordEncoder.encode(user.getPassword()));
				data.put("status",true);
				mailer.send(new MailInfo(user.getEmail(), "Xac nhan mk",
						"Click here: http://localhost:8080/registerTeacherAccount?token=" + jwt.createJWT(data)));
				model.addAttribute("msg" , "Vui lòng kiểm tra email của bạn !");
			}else{
				model.addAttribute("msg" , "Xác nhận mật khẩu thất bại");
			}
		}


		return "user/registration.html";
	}

	@GetMapping(value = {"/registerTeacherAccount"})
	public String registerTeacher(@RequestParam String token , Model model){
		if(token!=null){
			if(jwt.checkSign(token)){
				accountService.createTeacher(token);
				model.addAttribute("fullname" , getValue(token,"fullname"));
				model.addAttribute("name" , getValue(token,"username"));

			}else{
				model.addAttribute("msg" , "Quá hạn đăng kí , vui lòng đăng kí lại.");
			}
		}else{
			model.addAttribute("msg" , "Lỗi , vui lòng đăng kí lại");
		}
		return "user/checkRegisterTeacher.html";
	}
	private String getValue(String token, String name) {
		Object obj = JSONValue.parse(jwt.decodess(token));
		JSONObject jsonObject = (JSONObject) obj;
		return jsonObject.get(name).toString();
	}
}
