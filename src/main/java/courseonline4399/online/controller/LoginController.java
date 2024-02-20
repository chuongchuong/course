package courseonline4399.online.controller;

import courseonline4399.online.model.User;
import courseonline4399.online.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
@Autowired
	AccountService accountService;
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(
			@RequestParam(required = false , value = "error") String error,
			@RequestParam(required = false , value="logout") String logout , Model model){

//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
//
//			{ return "login";
//
//			}
//		return "redirect:/";
		String errorMessage = null;
		if(error != null){
			errorMessage = "Vui lòng kiểm tra tên đăng nhập hoặc mật khẩu";
		}
		if(logout != null){
			errorMessage = "Đăng xuất thành công ";
		}
		model.addAttribute("msg" , errorMessage);
		return "user/login.html";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}
	@GetMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2){
//		OAuth2User oAuth2User = oauth2.getPrincipal();
//		String email = (String) oAuth2User.getAttributes().get("email");\
//		System.out.println(email);


		accountService.loginFromOAuth2(oauth2);
		return "redirect:/home";
	}
}
