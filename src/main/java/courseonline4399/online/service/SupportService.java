package courseonline4399.online.service;

import courseonline4399.online.model.Support;
import courseonline4399.online.model.Userole;
import courseonline4399.online.repository.SupportRepository;
import courseonline4399.online.repository.UseroleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportService implements CRUD<Support,Integer>{

    @Autowired
    SupportRepository supportRepository;
    @Autowired
    AccountService accountService;


    @Override
    public Support create(Support support) {
        return supportRepository.save(support);
    }

    @Override
    public Support update(Support support) {
        return supportRepository.save(support);
    }

    @Override
    public void delete(Integer integer) {
        supportRepository.deleteById(integer);
    }

    @Override
    public List<Support> findAll() {
        return supportRepository.findAll();
    }

    public List<Support> findByEmail(String email) {
        return supportRepository.findByEmail(email);
    }

    @Override
    public Support findById(Integer integer) {
        return supportRepository.findById(integer).get();
    }

}
