import dao.UserDao;
import model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserDao userDAO = context.getBean(UserDao.class);
        User user = new User("alexey", "alekseev", 123);
        System.out.println("User::" + user);
        userDAO.save(user);
        System.out.println("User::" + user);
        //close resources
        context.close();
    }
}