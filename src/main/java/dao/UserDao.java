package dao;
import model.User;

import java.util.List;

public interface UserDao {
    void save(User u);
    void delete(User u);

    User find(String id);
    void changeFirstName(User u, String name);
    void changeLastName(User u, String lastName);
    void changePassword(User u, String password);
    //List<User> getAll();
}
