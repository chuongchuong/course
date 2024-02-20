package courseonline4399.online.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import courseonline4399.online.model.Order;
import courseonline4399.online.model.OrderDetail;
import courseonline4399.online.model.User;
import courseonline4399.online.model.Userole;
import courseonline4399.online.repository.OrderDetailRepository;
import courseonline4399.online.repository.OrderRepository;
import courseonline4399.online.repository.UseroleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleService implements CRUD<Userole,Integer>{

    @Autowired
    UseroleRepository useroleRepository;

    @Override
    public Userole create(Userole userole) {
        return useroleRepository.save(userole);
    }

    @Override
    public Userole update(Userole userole) {
        return null;
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

    public Userole findByUserId(Integer integer) {
        return useroleRepository.findByIdUser(integer);
    }
}
