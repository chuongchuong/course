package courseonline4399.online.service;

import courseonline4399.online.model.Voucher;
import courseonline4399.online.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherService {

    private final VoucherRepository voucherRepository;

    @Autowired
    public VoucherService(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }
    public Voucher getVoucherById(Integer id) {
        return voucherRepository.findById(id).get();
    }

    public void createVoucher(Voucher voucher) {
        voucherRepository.save(voucher);
    }

    public void updateVoucher(Voucher voucher) {
        voucherRepository.save(voucher);
    }

    public void deleteVoucherById(Integer id) {
        voucherRepository.deleteById(id);
    }
}