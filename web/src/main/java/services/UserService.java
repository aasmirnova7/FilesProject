package services;

import model.User;
import java.util.List;

public interface UserService {
    void save(User u);
    void delete(String login);
    User find(String id);
    void changeFirstName(String id, String name);
    void changeLastName(String id, String lastName);
    void changePassword(String id, String password);
    List<String> findAll();
}
