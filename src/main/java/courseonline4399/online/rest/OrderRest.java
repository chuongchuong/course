package courseonline4399.online.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import courseonline4399.online.model.Order;

import courseonline4399.online.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/orders")
public class OrderRest {
    @Autowired
    OrderService orderService;



    @PostMapping("/{tien}/{idVoucher}")
    public Order dathang(@RequestBody JsonNode orderData, @PathVariable("tien") float tien, @PathVariable("idVoucher") Integer idVoucher) {
        System.out.println("chạy post mapping lưu thông tin vào database");
        System.out.println("ttttttttttt" + tien);

        Order order = orderService.create(orderData,tien,idVoucher);
        return order;
    }


}
