package courseonline4399.online.controller;

import courseonline4399.online.model.UsedVoucher;
import courseonline4399.online.model.User;
import courseonline4399.online.model.Voucher;
import courseonline4399.online.service.AccountService;
import courseonline4399.online.service.UsedVoucherService;
import courseonline4399.online.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class VoucherController {
    @Autowired
    VoucherService voucherService;
    @Autowired
    AccountService accountService;

    @Autowired
    UsedVoucherService usedVoucherService;
    @GetMapping("/admin/voucher")
    public String getvoucher(Model model){
        List<Voucher> vouchers = voucherService.getAllVouchers();
        model.addAttribute("vouchers", vouchers);
        List<UsedVoucher> usedVouchers = usedVoucherService.getAllUsedVouchers();
        model.addAttribute("usedVouchers", usedVouchers);
        model.addAttribute("voucher", new Voucher());
        return "/admin/form/voucher-form";
    }
    @PostMapping("/admin/voucher/addVoucher")
    public String addVoucher(@ModelAttribute("voucher") Voucher voucher, BindingResult result) {
        User user = accountService.getCurrentUserInfo();
        voucher.setUser(user);
        voucher.setCreatedDate(new Date());
        if(voucher.getPercent() == null){
            voucher.setPercent(Float.valueOf(0));
        }else if(voucher.getPrice() == null){
            voucher.setPrice(Float.valueOf(0));
        }
        voucher.setVoucherCode(generateRandomString());
        voucherService.createVoucher(voucher);

        return "redirect:/admin/voucher"; // Chuyển hướng về trang danh sách voucher
    }

    @PostMapping("/admin/voucher/updateVoucher")
    public String updateVoucher(@ModelAttribute("voucher") Voucher voucher) {
        User user = accountService.getCurrentUserInfo();
        voucher.setCreatedDate(voucherService.getVoucherById(voucher.getId()).getCreatedDate());
        if(voucher.getEndDate() == null){
            voucher.setEndDate(voucherService.getVoucherById(voucher.getId()).getEndDate());
        }
        if(voucher.getPrice() != null){
            if(voucherService.getVoucherById(voucher.getId()).getPercent() != 0){
                voucher.setPercent(Float.valueOf(0));
            }
        }
        if(voucher.getPercent() != null){
            if(voucherService.getVoucherById(voucher.getId()).getPrice() != 0){
                voucher.setPrice(Float.valueOf(0));
            }
        }
        voucher.setUser(user);
        voucher.setVoucherCode(voucherService.getVoucherById(voucher.getId()).getVoucherCode());
        voucherService.updateVoucher(voucher);


        return "redirect:/admin/voucher"; // Chuyển hướng về trang danh sách voucher
    }

    @PostMapping("/admin/voucher/deleteVoucher")
    public String deleteVoucher(@ModelAttribute("voucher") Voucher voucher) {
        voucherService.deleteVoucherById(voucher.getId());
        return "redirect:/admin/voucher"; // Chuyển hướng về trang danh sách voucher
    }
//    @GetMapping("/admin/voucher/editvoucher/{id}")
//    public String editVoucher(@ModelAttribute("id") Long id, Model model) {
//        Voucher voucher = voucherService.getVoucherById(id);
//        model.addAttribute("voucher", voucher);
//        System.out.println(".................................................lllllllllllllllllllllllllllll"+id);
//        return "/admin/form/voucher-form";
//    }

    public static String generateRandomString() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
