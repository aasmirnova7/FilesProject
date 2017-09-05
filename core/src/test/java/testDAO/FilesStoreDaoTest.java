package testDAO;

import dao.FilesStoreDao;
import dao.SpecialAccessFilesStoreDao;
import dao.UserDao;
import model.FilesStore;
import model.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.FilesStoreService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DAOTestConfig.class})
public class FilesStoreDaoTest{
    @Autowired
    private UserDao userDao;
    @Autowired
    private FilesStoreDao fsd;
    @Autowired
    private SpecialAccessFilesStoreDao specialAccessFilesStoreDao;
    @Autowired
    private FilesStoreService storeService;

    private Integer fsId;

    @PersistenceContext
    private EntityManager em;

    @Before
    public void before(){
        User user = new User("1","vasya", "vasichkin", "123");
        userDao.save(user);
        FilesStore fs = new FilesStore("TTT",0, user, "QQQ".getBytes());
        fsd.save(fs);
        fsId = fs.getNumber();
    }
    @After
    public void after(){
        userDao.delete("1");
    }
    @Test
    public void findWithFileNameAndUserTest(){
        FilesStore fs=em.find(FilesStore.class,fsId);
        Assert.assertNotNull(fsd.findWithFileNameAndUser(fs.getFileName(),fs.getUser()));
    }
    @Test
    public void findWithFileNameTest(){
        Assert.assertNotNull(fsd.findWithFileName(em.find(FilesStore.class,fsId).getFileName()));
    }
    @Test
    public void findAllTest(){
        User user = new User("1","vasya", "vasichkin", "123");
        Assert.assertFalse(fsd.findWithUser(user).isEmpty());
    }
    @Test
    public void saveTest(){
        FilesStore filesStore = new FilesStore("AAA",0,em.find(User.class,"1"),"AAAAA".getBytes());
        fsd.save(filesStore);
        Assert.assertNotNull(fsd.findWithFileName("AAA").get(0));
    }
    @Test
    public void deleteTest(){
        fsd.delete(fsd.findWithFileName("TTT").get(0));
        Assert.assertTrue(fsd.findWithFileName("TTT").isEmpty());
    }
    @Test
    public void mergeFileStoreTest(){
        FilesStore fs = fsd.findWithFileName("TTT").get(0);
        FilesStore unexpected = fsd.findWithFileName("TTT").get(0);
        fs.setPrivacy(1);
        fsd.mergeFileStore(fs);
        Assert.assertNotEquals(fsd.findWithFileName("TTT").get(0),unexpected);
    }

    @Test
    public void findSpecialFilesTest(){
        User user = new User("1","vasya", "vasichkin", "123");
        FilesStore fs = new FilesStore("AAA",2, user, "IIII".getBytes());
        fsd.save(fs);
        specialAccessFilesStoreDao.save(fs,"1");
        Assert.assertNotNull(fsd.findSpecialFiles(fs).get(0));

    }
    @Test
    public void findAllInSpecialFilesTest(){
        FilesStore fs = fsd.findWithFileName("TTT").get(0);
        storeService.changeLevel(fs,2,"1");
        Assert.assertFalse(fsd.findAllInSpecialFiles("1").isEmpty());
    }
    @Test
    public void findWithFileNameInSAFSTest(){
        FilesStore fs =fsd.findWithFileName("TTT").get(0);
        storeService.changeLevel(fs,2,"1");
        Assert.assertFalse(fsd.findWithFileNameInSAFS(fs).isEmpty());
    }
    @Test
    public void findWithLevel0Test(){
        FilesStore fs =fsd.findWithFileName("TTT").get(0);
        Assert.assertFalse(fsd.findWithLevel0().isEmpty());
    }
    @Test
    public void findWithDataAndUserTest(){
        User user = new User("1","vasya", "vasichkin", "123");
        Assert.assertFalse(fsd.findWithDataAndUser("QQQ".getBytes(),user).isEmpty());
    }
}
