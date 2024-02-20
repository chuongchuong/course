package courseonline4399.online.service;

import courseonline4399.online.model.UsedVoucher;
import courseonline4399.online.repository.UsedVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsedVoucherService {

    private final UsedVoucherRepository usedVoucherRepository;

    @Autowired
    public UsedVoucherService(UsedVoucherRepository usedVoucherRepository) {
        this.usedVoucherRepository = usedVoucherRepository;
    }

    public List<UsedVoucher> getAllUsedVouchers() {
        return usedVoucherRepository.findAll();
    }


    public UsedVoucher update(UsedVoucher usedVoucher) {
        return usedVoucherRepository.save(usedVoucher);
    }
    public UsedVoucher create(UsedVoucher usedVoucher) {
        return usedVoucherRepository.save(usedVoucher);
    }
    public UsedVoucher findByUserIdAndVoucherId(Integer userId, Integer voucherId) {
        return usedVoucherRepository.findByUserIdAndVoucherId(userId, voucherId);
    }

}
