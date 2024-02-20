package courseonline4399.online.service;

import courseonline4399.online.model.Role;
import courseonline4399.online.model.User;
import courseonline4399.online.model.Userole;
import courseonline4399.online.repository.UserRepository;
import courseonline4399.online.repository.UseroleRepository;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class AccountService implements CRUD<User, Integer> {
    @Autowired
    UserRepository repository;

    @Autowired
    AccountRoleService accountRoleService;
    @Autowired
    private JWT jwt;
    @Autowired
    PasswordEncoder pe;

    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UploadService uploadService;
    @Autowired
    FileManagerService fileManagerService;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    public User create(String token) {
        String email = getValue(token, "email");
        String fullname = getValue(token, "fullname");
        String phone = getValue(token, "phone");
        String username = getValue(token, "username");
        String password = getValue(token, "password");
        //save to database

        User user = new User();
        user.setEmail(email);
        user.setFullname(fullname);
        user.setPassword(password);
        user.setPhonenumber(phone);
        user.setUsername(username);
        user.setStatus(Boolean.FALSE);
        create(user);

        Role role = new Role();
        role.setId("USER");
        role.setName("USER");

        Userole userole = new Userole();
        userole.setRole(role);
        userole.setUser(user);

        accountRoleService.create(userole);
        return user;
    }

    //Dky giảng viên
    public User createTeacher(String token) {
        String email = getValue(token, "email");
        String fullname = getValue(token, "fullname");
        String phone = getValue(token, "phone");
        String username = getValue(token, "username");
        String password = getValue(token, "password");
        //save to database

        User user = new User();
        user.setEmail(email);
        user.setFullname(fullname);
        user.setPassword(password);
        user.setPhonenumber(phone);
        user.setUsername(username);
        user.setStatus(Boolean.TRUE);
        create(user);

        Role role = new Role();
        role.setId("TEACHER");
        role.setName("TEACHER");

        Userole userole = new Userole();
        userole.setRole(role);
        userole.setUser(user);

        accountRoleService.create(userole);
        return user;
    }

    private String getValue(String token, String name) {
        Object obj = JSONValue.parse(jwt.decodess(token));
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject.get(name).toString();
    }

    @Override
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    public List<User> findTeachers() {
        return repository.findTeachers();
    }

    @Override
    public User findById(Integer integer) {
        return repository.findById(integer).get();
    }

    public User findByUsername(String username) {
        return repository.findByusername(username);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Boolean isLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null;
    }

    public Authentication isLoginInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    public User getCurrentUserInfo() {
        Authentication currentUser = isLoginInfo();
        if (currentUser != null) {
            User user = repository.findByusername(currentUser.getName());
            return user;
        }
        return null;
    }

    public void loginFromOAuth2(OAuth2AuthenticationToken oauth2) {
        OAuth2AuthenticationToken oauth2c = oauth2;
        // String fullname = oauth2.getPrincipal().getAttribute("name");
        String email = oauth2.getPrincipal().getAttribute("email");
        String password = Long.toHexString(System.currentTimeMillis());
        assert email != null;
        User account = repository.findByEmail(email.trim());
        if (account == null) {
            account = new User();
            account.setFullname(oauth2.getPrincipal().getAttribute("name"));
            account.setEmail(email);
            account.setUsername(email);
            account.setPhonenumber("0328383680");
            account.setPassword(password);
            account.setStatus(Boolean.FALSE);

            repository.save(account);

            Role role = new Role();
            role.setId("USER");
            role.setName("USER");

            Userole userole = new Userole();
            userole.setRole(role);
            userole.setUser(account);
            accountRoleService.create(userole);
        } else {


            if(!account.getStatus()){
                account.setFullname(oauth2.getPrincipal().getAttribute("name"));
                repository.save(account);
            }else{
                return;
            }

        }
        //  tim trong data base load role
        Userole userole = userRoleService.findByUserId(account.getId());
        UserDetails user = org.springframework.security.core.userdetails.User.withUsername(email)
                .password(pe.encode(password)).roles(String.valueOf(userole.getRole().getName())).build();
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public void updateTokenByEmail(String email, String token) {
        repository.updateTokenByEmail(email, token);
    }

    public User finBytoken(String token) {
        return repository.findByToken(token);
    }


}
