package testServices;

import dao.FilesStoreDao;
import model.FilesStore;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.FilesStoreService;
import services.UserService;
import testDAO.DAOTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DAOTestConfig.class})
public class FilesStoreServiceTest {
    @Autowired
    private FilesStoreService filesStoreService;
    @Autowired
    private UserService userService;
    @Autowired
    private FilesStoreDao filesStoreDao;
//    @Test
//    public void saveTest(){
//        User user = new User("2","petya","ivanov","4321");
//        userService.save(user);
//        FilesStore filesStore = new FilesStore("AAA",2,user);
//        filesStoreService.save(filesStore,"1");
//        filesStore = new FilesStore("PPP",2,user);
//        filesStoreService.save(filesStore);
//        filesStore = new FilesStore("WWW",2,user);
//        filesStoreService.save(filesStore,"1","3");
//        Assert.assertNotNull(filesStoreService.find("AAA","2").get(0));
//    }
//    @Test
//    public void deleteTest(){
//        filesStoreService.delete("QQQ","2");
//        Assert.assertTrue(filesStoreService.find("QQQ","2").isEmpty());
//    }
//    @Test
//    public void findTest(){
//        Assert.assertNotNull(filesStoreService.find("AAA","2").get(0));
//    }
//    @Test
//    public void changeFileNameTest(){
//        FilesStore filesStore = filesStoreService.find("AAA","2").get(0);
//        FilesStore unexpected = filesStoreService.find("AAA","2").get(0);
//        filesStoreService.changeFileName(filesStore,"QQQ","2");
//        Assert.assertNotEquals(filesStoreService.find("QQQ","2").get(0),unexpected);
//    }
//    @Test
//    public void changeLevelTest(){
//        FilesStore filesStore = filesStoreService.find("PPP","2").get(0);
//        FilesStore unexpected = filesStoreService.find("PPP","2").get(0);
//        filesStoreService.changeLevel(filesStore,1,"2");
//        Assert.assertNotEquals(filesStoreService.find("PPP","2").get(0),unexpected);
//    }
//    @Test
//    public void deleteIdAccessedTest(){
//        filesStoreService.deleteIdAccessed(filesStoreService.find("WWW","2").get(0),"2","1");
//        Assert.assertEquals(filesStoreDao.findSpecialFiles(filesStoreService.find("WWW","2").get(0)).size(),2);
//    }
//    @Test
//    public void addIdAccessedTest(){
//        filesStoreService.addIdAccessed(filesStoreService.find("WWW","2").get(0),"2","3");
//        Assert.assertEquals(filesStoreDao.findSpecialFiles(filesStoreService.find("WWW","2").get(0)).size(),3);
//    }
}
