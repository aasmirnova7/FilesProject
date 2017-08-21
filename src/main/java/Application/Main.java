package Application;

import dao.daointerfaces.FilesStoreDao;
import dao.daointerfaces.SpecialAccessFilesStoreDao;
import dao.daointerfaces.UserDao;
import model.FilesStore;
import model.SpecialAccessFilesStore;
import model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication(scanBasePackages = {"config", "spring"})
public class Main {

    public static void main(String[] args) throws Throwable{
        SpringApplication.run(Main.class,args);

//        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("config", "spring");
//        UserDao userDAO = context.getBean(UserDao.class);
//        User user1 = new User("1","vasya", "sidorov", "789");
//        User user2 = new User("2","vasya", "sidorov", "789");
//        userDAO.save(user1);
//        userDAO.save(user2);
////        System.out.println("User::" + user1);
//
//        FilesStoreDao fsd = context.getBean(FilesStoreDao.class);
//        FilesStore fs1 = new FilesStore("AAA",0,user1);
//        FilesStore fs2 = new FilesStore("AA", 1,user2);
////        //Выдаст 2 строки
//        fsd.save(fs1);
//        fsd.save(fs2);
////        System.out.println("!!!!!!!!!!!!!!!!!"+ fsd.find(fs1).get(0));
//        SpecialAccessFilesStoreDao spd = context.getBean(SpecialAccessFilesStoreDao.class);
//        SpecialAccessFilesStore safs = new SpecialAccessFilesStore((long) 1,fs1);
//        spd.save(safs);
//        System.out.println("!!!!!!!!!!! "+ spd.find(safs));
//        //User user = fsd.find(fs1).get(0).getUser();
//        //System.out.println("User::" + user);
//        //System.out.println(userDAO.getAll());
//
//        /*FilesStoreDao fsd = context.getBean(FilesStoreDao.class);
//        FilesStore fs1 = new FilesStore("A", (long)1, 0);
//        fsd.delete(fsd.find(fs1));
//        System.out.println(fsd.find(fs1));*/
//
//
//        //System.out.println("Result: "+fsd.find(fs1));
//        //User user = userDAO.find((long) 2);
//        //System.out.println(user);
//        context.close();
    }
}