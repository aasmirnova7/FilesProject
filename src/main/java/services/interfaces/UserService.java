package services.interfaces;

import model.User;

public interface UserService {
    void save(User u);
    void delete(String login);

    User find(String id);
    void changeFirstName(String id, String name,String login);
    void changeLastName(String id, String lastName, String login);
    void changePassword(String id, String password,String login);
}
