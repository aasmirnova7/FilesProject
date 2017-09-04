package dao;
import model.User;

import java.util.List;

public interface UserDao {
    void save(User u);
    void delete(String login);
    void mergeUser(User u);
    User find(String id);
    List<User> findAll();

}
