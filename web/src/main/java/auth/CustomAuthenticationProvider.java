package auth;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//Связь с БД : проверка того, что пользователи есть в БД
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userDao.find(id);
        if (user != null && id.equals(user.getId()) && passwordEncoder.matches(password,user.getPassword()) ){
            return new UsernamePasswordAuthenticationToken(id, password, new ArrayList<>());
        }else {
            throw new BadCredentialsException("Error");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}