package courseonline4399.online.controller;


import com.itextpdf.text.DocumentException;
import courseonline4399.online.Util.ExportToPDF;
import courseonline4399.online.model.*;
import courseonline4399.online.service.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class PaymentController {
	@Autowired
	AccountService accountService;

	@Autowired
	OrderService orderService;

	@Autowired
	UserCourseService userCourseService;

	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	private MailerService mailer;

	@Autowired
	UsedVoucherService usedVoucherService;

	@Autowired
	ExportToPDF exportToPDF;
	@GetMapping("/payment")
	public String payment(
			Model model,
			@RequestParam(value = "vnp_ResponseCode") String responseCode,
			@RequestParam(value = "vnp_Amount") Float amout,
			@RequestParam(value = "vnp_BankCode") String bankcode,
			@RequestParam(value = "vnp_CardType") String cardtype,
			@RequestParam(value = "vnp_OrderInfo") String orderinfo,
			@RequestParam(value = "vnp_PayDate") String paydate

	) throws MessagingException, DocumentException, IOException {
		User user = accountService.getCurrentUserInfo();
		// thông tin order của user
		List<Order> orders = orderService.findByUser(user.getId());
		Order order = orderService.getOrderNew(orders);
		System.out.println("Don hang đuọc lay ra là " + order.getId());
		// thông tin thanh toán
//		String strTien = Float.toString(amout);
//		if (strTien.endsWith(".00")) {
//			strTien = strTien.substring(0, strTien.length() - 3);
//		}
		String strTien = String.format("%,.0f", amout);
		model.addAttribute("tien",strTien);
		model.addAttribute("bankcode",bankcode);
		model.addAttribute("cardtype",cardtype);
		model.addAttribute("orderinfo",orderinfo);
		model.addAttribute("paydate",paydate);
		if(responseCode.equals("00")){
			order.setStatusorder(1);
			orderService.update(order);
			System.out.println("thanh toán thành công");
			model.addAttribute("messege" , "Thanh toán thành công");

			// Xoá giỏ hàng từ local storage
			String javascriptCode = "localStorage.removeItem('cart');";
			model.addAttribute("javascriptCode", javascriptCode);
			// them khoa hoc cho ngươi dùng
			List<Course> list = orderDetailService.findByOrderId(order.getId());
			for(Course c : list){
				UserCourse u = new UserCourse();
				u.setOwner(user);
				u.setCourse(c);
				userCourseService.create(u);
			}// gui email
			mailer.send(new MailInfo(user.getEmail(), "Thanh toán thành công",
					"Cảm ơn bạn đã tin tưởng mua khoá học của chúng tôi"));

			// xuất file pdf
			//exportToPDF.exportTableToPDF(list,bankcode,orderinfo,paydate,user.getFullname(),Double.valueOf(amout));
		}else{
			order.setStatusorder(2);
			orderService.update(order);
			model.addAttribute("messege" , "Thanh toán không thành công");
			System.out.println("thanh toán đã bị huỷ");
		}
//		model.addAttribute("order", order);

		model.addAttribute("orderdetail", orderDetailService.findByOrderId(order.getId()));
		model.addAttribute("user", user);
		return "/user/ResultPaym.html";
	}


}
