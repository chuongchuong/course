package courseonline4399.online.repository;

import courseonline4399.online.model.User;
import courseonline4399.online.model.Voucher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Integer> {
    // Các phương thức cụ thể nếu cần
}
