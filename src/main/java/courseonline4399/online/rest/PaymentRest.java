package courseonline4399.online.rest;


import courseonline4399.online.config.Config;
import courseonline4399.online.model.Course;
import courseonline4399.online.model.Order;
import courseonline4399.online.model.User;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.OrderDetailService;
import courseonline4399.online.service.OrderService;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api_payment")
public class PaymentRest {

    @Autowired
    AccountService accountService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;



    @GetMapping("create_payment")
    public ModelAndView  pay() throws UnsupportedEncodingException,Exception {
        // thông tin người mua và đơn hàg
        // thong tin user
        User user = accountService.getCurrentUserInfo();
        // thông tin order của user
        System.out.println("---------fdghrjgfbiujklvbjfdghfj");
        List<Order> orders = orderService.findByUser(user.getId());
        System.out.println(orders.get(0).getPhonenumber());
        //  System.out.println("so luong don hang get ra duoc "+ orders.size());
        // lay order moi nhat
        Order order = new Order();

        if(orders.size()>1){
            order = orderService.getOrderNew(orders);
        }else{
            order = orders.get(0);
        }

        // tính tổng tiền
        long tongTien = order.getPrice().longValue();

        //String tien = "100000";
        String orderType = "other";

        long amount = (long) Math.ceil(tongTien)*100;
        String bankCode = "";
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";


        // long amount = 1000000;
        String vnp_TxnRef = Config.getRandomNumber(8);



        //  String vnp_IpAddr = "192.168.1.34";
        String vnp_IpAddr = Config.getIpAddr();

        //String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode",bankCode);

//        if (bankCode != null && !bankCode.isEmpty()) {
//            vnp_Params.put("vnp_BankCode", bankCode);
//        }
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang " + vnp_TxnRef+" cho " +user.getEmail());

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_OrderType", orderType);

//        String locate = req.getParameter("language");
//        if (locate != null && !locate.isEmpty()) {
//            vnp_Params.put("vnp_Locale", locate);
//        } else {
//            vnp_Params.put("vnp_Locale", "vn");
//        }
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

//        Payment payment = new Payment();
//        payment.setStatus("OK");
//        payment.setMessage("Successfully");
//        payment.setURL(paymentUrl);
//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
//        Gson gson = new Gson();
//        resp.getWriter().write(gson.toJson(job));
        // return ResponseEntity.status(HttpStatus.OK).body(payment);

        RedirectView redirectView = new RedirectView(paymentUrl);
        return new ModelAndView(redirectView);
    }
}
