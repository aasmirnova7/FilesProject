package services.impl;


import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

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
    public void changeFirstName(String id, String name) {
        User u = find(id);
        u.setName(name);
        userDao.mergeUser(u);
    }

    @Override
    public void changeLastName(String id, String lastName) {
        User u = find(id);
        u.setLastName(lastName);
        userDao.mergeUser(u);
    }

    @Override
    public void changePassword(String id, String password) {
        User u = find(id);
        u.setPassword(passwordEncoder.encode(password));
        userDao.mergeUser(u);
    }

    @Override
    public List<String> findAll() {
        List<String> list = new ArrayList<>();
        List<User> userList = userDao.findAll();
        for(User user: userList){
            list.add(user.getId());
        }
        return list;
    }

}
