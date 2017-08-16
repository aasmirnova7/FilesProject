package Application;

import dao.FilesStoreDao;
import dao.UserDao;
import model.FilesStore;
import model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication(scanBasePackages = {"config","springsequrity"})
public class Main {

    public static void main(String[] args) throws Throwable{
        SpringApplication.run(Main.class,args);
        /*ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("config","springsequrity");
        UserDao userDAO = context.getBean(UserDao.class);
        User user = new User("3","vasya", "sidorov", "789");
        userDAO.save(user);
        System.out.println("User::" + user);
        //System.out.println(userDAO.getAll());

        /*FilesStoreDao fsd = context.getBean(FilesStoreDao.class);
        FilesStore fs1 = new FilesStore("A", (long)1, 0);
        fsd.delete(fsd.find(fs1));
        System.out.println(fsd.find(fs1));*/

     /*   FilesStoreDao fsd = context.getBean(FilesStoreDao.class);
        FilesStore fs1 = new FilesStore("CCC", (long)1, 1);
        FilesStore fs2 = new FilesStore("CCC", (long)1, 0);
        //Выдаст 2 строки
        fsd.save(fs1);
        fsd.save(fs2);
        //System.out.println("Result: "+fsd.find(fs1));
        //User user = userDAO.find((long) 2);
        //System.out.println(user);
        context.close();*/
    }
}