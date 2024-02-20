package courseonline4399.online.repository;

import courseonline4399.online.model.Cartsession;
import courseonline4399.online.model.Order;
import courseonline4399.online.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT p FROM OrderDetail p WHERE p.order.id =?1")
    List<OrderDetail> findByOrderId(Integer id);

    @Query("SELECT p FROM OrderDetail p WHERE p.order.id =?1")
    List<OrderDetail> findByOrder(Integer id);

}
