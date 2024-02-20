package courseonline4399.online.service;

import courseonline4399.online.model.Course;
import courseonline4399.online.model.OrderDetail;
import courseonline4399.online.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService implements CRUD<OrderDetail,Integer>{

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    CourseService courseService;

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<OrderDetail> findAll() {
        return null;
    }

    @Override
    public OrderDetail findById(Integer integer) {
        return null;
    }

    public List<Course> findByOrderId(Integer id){
        List<OrderDetail> list = orderDetailRepository.findByOrderId(id);
        List<Course> listCourse = new ArrayList<>();
        for(OrderDetail i : list){
            Course course = courseService.findById(i.getCourse().getId());
            course.setPrice(i.getPrice());
            listCourse.add(course);
        }
        return  listCourse;
    }

}
