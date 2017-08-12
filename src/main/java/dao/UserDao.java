package dao;
import model.User;

public interface UserDao {
    public void save(User u);

    public User find(Long id);
}
