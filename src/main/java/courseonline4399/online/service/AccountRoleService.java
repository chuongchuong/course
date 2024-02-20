package courseonline4399.online.service;

import courseonline4399.online.model.Userole;
import courseonline4399.online.repository.UseroleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRoleService implements CRUD<Userole , Integer>{
    @Autowired
    UseroleRepository useroleRepository;
    @Override
    public Userole create(Userole userole) {
        return useroleRepository.save(userole);
    }

    @Override
    public Userole update(Userole userole) {
        return useroleRepository.save(userole);
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<Userole> findAll() {
        return useroleRepository.findAll();
    }

    @Override
    public Userole findById(Integer integer) {
        return useroleRepository.findById(integer).get();
    }
}
