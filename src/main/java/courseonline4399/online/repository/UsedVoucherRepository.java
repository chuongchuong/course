package courseonline4399.online.repository;

import courseonline4399.online.model.UsedVoucher;
import courseonline4399.online.model.User;
import courseonline4399.online.model.Voucher;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsedVoucherRepository extends JpaRepository<UsedVoucher, Long> {

   UsedVoucher findByUserIdAndVoucherId(Integer userId, Integer voucherId);

}
