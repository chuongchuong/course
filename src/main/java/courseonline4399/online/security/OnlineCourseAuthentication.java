package courseonline4399.online.security;


import courseonline4399.online.model.User;
import courseonline4399.online.model.Userole;
import courseonline4399.online.repository.UserRepository;
import courseonline4399.online.repository.UseroleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class OnlineCourseAuthentication implements AuthenticationProvider {
    @Autowired
    UserRepository userDAO;
    @Autowired
    UseroleRepository authorityService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User account = userDAO.findByusername(username);
        //String daa = passwordEncoder.encode(password);

        if(account != null && account.getId() > 0 && passwordEncoder.matches(password, account.getPassword())){
            if(!account.getStatus() || account.getStatus() == null){
                return new UsernamePasswordAuthenticationToken(account.getUsername() , password , getGrantedAuthorities(account));
            }else{
                throw new BadCredentialsException("Tai khoan bi tam khoa");
            }

        }else{
            throw new BadCredentialsException("invalid Credential");
        }
    }

    private List< GrantedAuthority> getGrantedAuthorities(User account) {
        List< GrantedAuthority> grantedAuthorities = new ArrayList<>();

        List<Userole> accountroles = authorityService.findAllByUser(account);

        if(accountroles != null && accountroles.size() > 0){
            accountroles.forEach(item ->{
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+item.getRole().getName()));
            });
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
