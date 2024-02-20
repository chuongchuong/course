package courseonline4399.online.service;

import java.util.ArrayList;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import courseonline4399.online.model.Order;

import courseonline4399.online.model.OrderDetail;
import courseonline4399.online.model.User;
import courseonline4399.online.repository.OrderDetailRepository;
import courseonline4399.online.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class OrderService implements CRUD<Order,Integer>{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    AccountService accountService;

    @Override
    public Order create(Order order) {
        return null;
    }

    public Order create(JsonNode orderData,float tien,Integer idVoucher) {
        ObjectMapper mapper = new ObjectMapper();

        Order order = mapper.convertValue(orderData, Order.class);
        User user = accountService.getCurrentUserInfo();
        if(user==null){
            return null;
        }
        order.setUser(user);
        order.setPhonenumber(user.getPhonenumber());
        order.setStatusorder(0);
        order.setVoucher(idVoucher);


        orderRepository.save(order);
        //  int idOrder = order.getId();

        TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
        List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
                .stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
        float tongTien=0;
        for(OrderDetail c: details){
            tongTien+=c.getPrice();
        }
        // so sanh nếu có voucher thì thay đổi giá

        System.out.println("Tien da giam giam "+ tien);
        if(tien == 0){

        }

        order.setPrice(tien);

        orderRepository.save(order);
        orderDetailRepository.saveAll(details);

        return order;
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Integer integer) {

    }

    public Order findByUserIdAndVoucher(Integer userid,Integer voucherid) {
        return orderRepository.findByUserIdAndVoucher(userid,voucherid);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }


    @Override
    public Order findById(Integer integer) {
        return orderRepository.findById(integer).get();
    }

    public List<Order> findByUser(Integer integer) {
        List<Order> list = orderRepository.findByIdUser(integer);
        return list;
    }

    public Order getOrderNew(List<Order> list) {
        int i = 0;
        for(Order o : list){
            if(i<o.getId()){
                i = o.getId();
            }
        }
        // xoá đơn hàng
        // xoá orderdetail
//        for(Order o : list){
//            if(i!=o.getId()){
//                List<OrderDetail> l =  orderDetailRepository.findByOrder(o.getId());
//                orderDetailRepository.deleteAll(l);
//                System.out.println("xoá orderdetail thành công");
//                // xoá order
//                orderRepository.deleteById(o.getId());
//                System.out.println("xoá order thành công");
//            }
//        }

        return findById(i);
    }

    public List<Order> findByStatus1() {
        List<Order> list = orderRepository.findByStatus1();
        return list;
    }

    public List<Order> findAllIdUser(Integer integer) {
        List<Order> list = orderRepository.findAllIdUser(integer);
        return list;
    }
}
