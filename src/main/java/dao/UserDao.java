package dao;
import model.User;

public interface UserDao {
    void save(User u);
    void delete(User u);

    User find(Long id);
    void changeFirstName(User u, String name);
    void changeLastName(User u, String lastName);
    void changePassword(User u, Integer password);
}
