package springsequrity;

import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//Связь с БД : проверка того, что пользователи есть в БД
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDao userDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userDao.find(id);

        if (id.equals(user.getId()) && user.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(id, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}