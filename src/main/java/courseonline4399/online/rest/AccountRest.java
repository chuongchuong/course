package courseonline4399.online.rest;

import courseonline4399.online.model.User;
import courseonline4399.online.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRest {
    @Autowired
    AccountService service;

    @GetMapping("rest/user")
    public ResponseEntity<List<User>> getAll(){
        List<User> users= service.findAll();
        return  ResponseEntity.ok(users);

    }
}
