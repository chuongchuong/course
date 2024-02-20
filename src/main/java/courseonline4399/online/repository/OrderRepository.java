package courseonline4399.online.repository;

import courseonline4399.online.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.user.id =?1 and o.statusorder=0")
    List<Order> findByIdUser(Integer userid);
    //
    @Query("SELECT o FROM Order o WHERE o.statusorder=1")
    List<Order> findByStatus1();
    @Query("SELECT o FROM Order o WHERE o.user.id =?1 ")
    List<Order> findAllIdUser(Integer userid);

    Order  findByUserIdAndVoucher(Integer userid,Integer voucherid);
 }
