package Application;

import dao.daointerfaces.FilesStoreDao;
import dao.daointerfaces.UserDao;
import model.FilesStore;
import model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(scanBasePackages = {"config", "spring","controllers"})
public class Main {

    public static void main(String[] args) throws Throwable{
        SpringApplication.run(Main.class,args);
    }
}