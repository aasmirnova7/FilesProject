package testServices;

import dao.FilesStoreDao;
import model.FilesStore;
import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

    @Before
    public void before(){
        User user = new User("1","petya","ivanov","4321");
        userService.save(user);
        User user2 = new User("2","kate","ivanova","4777");
        userService.save(user2);
        FilesStore filesStore = new FilesStore("AAA",2,user,"QQQ".getBytes());
        filesStoreService.save(filesStore,"1","2");
    }
    @After
    public void after(){
        userService.delete("1");
        userService.delete("2");
    }

    @Test
    public void saveTest(){
        Assert.assertNotNull(filesStoreService.find("AAA","1").get(0));
    }
    @Test
    public void deleteTest(){
        filesStoreService.delete("AAA","1");
        Assert.assertTrue(filesStoreService.find("AAA","1").isEmpty());
    }
    @Test
    public void findTest(){
        Assert.assertNotNull(filesStoreService.find("AAA","1").get(0));
    }
    @Test
    public void findAllTest(){
        Assert.assertFalse(filesStoreService.findAll("1").isEmpty());
    }
    @Test
    public void changeFileNameTest(){
        FilesStore filesStore = filesStoreService.find("AAA","1").get(0);
        FilesStore unexpected = filesStoreService.find("AAA","1").get(0);
        filesStoreService.changeFileName(filesStore,"RRR","1");
        Assert.assertNotEquals(filesStoreService.find("RRR","1").get(0),unexpected);
    }
    @Test
    public void changeLevelTest(){
        FilesStore filesStore = filesStoreService.find("AAA","1").get(0);
        FilesStore unexpected = filesStoreService.find("AAA","1").get(0);
        filesStoreService.changeLevel(filesStore,1,"1");
        Assert.assertNotEquals(filesStoreService.find("AAA","1").get(0),unexpected);
    }
    @Test
    public void deleteIdAccessedTest(){
        filesStoreService.deleteIdAccessed(filesStoreService.find("AAA","1").get(0),"2");
        Assert.assertEquals(filesStoreDao.findSpecialFiles(filesStoreService.find("AAA","1").get(0)).size(),1);
    }
    @Test
    public void addIdAccessedTest(){
        filesStoreService.addIdAccessed(filesStoreService.find("AAA","1").get(0),"3");
        Assert.assertEquals(filesStoreDao.findSpecialFiles(filesStoreService.find("AAA","1").get(0)).size(),3);
    }
    @Test
    public void findAllInSpecialFilesTest(){
        Assert.assertFalse(filesStoreService.findAllInSpecialFiles("2").isEmpty());
    }
    @Test
    public void findAllInSpecialFilesWhereIIsOwnerTest(){
        Assert.assertFalse(filesStoreService.findAllInSpecialFilesWhereIIsOwner("1","AAA").isEmpty());
    }
    @Test
    public void findWithFileNameAndUserTest(){
        Assert.assertNotNull(filesStoreService.findWithFileNameAndUser("AAA","1"));
    }
    @Test
    public void findWithDataAndUserTest(){
        Assert.assertNotNull(filesStoreService.findWithDataAndUser("QQQ".getBytes(),"1"));
    }
}
