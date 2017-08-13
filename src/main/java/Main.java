import dao.UserDao;
import model.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("config");
        UserDao userDAO = context.getBean(UserDao.class);
        User user = new User("alexey", "alekseev", 123);
        userDAO.save(user);
        System.out.println("User::" + user);
        userDAO.changeFirstName(userDAO.find((long)1),"YYY");
        userDAO.changePassword(userDAO.find((long)2), 456);

        //User user = userDAO.find((long) 2);
        //System.out.println(user);
        context.close();
    }
}