package services.impl;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import services.UserService;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User u) {
        String password = passwordEncoder.encode(u.getPassword());
        u.setPassword(password);
        userDao.save(u);
    }

    @Override
    public void delete(String login) {
        userDao.delete(login);
    }

    @Override
    public User find(String id) {
        return userDao.find(id);
    }

    @Override
    public void changeFirstName(String id, String name, String login) {
        User u = find(id);
        if (login.equals(u.getId())) {
            u.setName(name);
            userDao.mergeUser(u);
        }
    }

    @Override
    public void changeLastName(String id, String lastName, String login) {
        User u = find(login);
        if (login.equals(u.getId())) {
            u.setLastName(lastName);
            userDao.mergeUser(u);
        }
    }

    @Override
    public void changePassword(String id, String password, String login) {
        User u = find(login);
        if (login.equals(u.getId())) {
            u.setPassword(passwordEncoder.encode(password));
            userDao.mergeUser(u);
        }
    }

}
