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

    private Integer fsId;

    @PersistenceContext
    private EntityManager em;

//    @Before
//    public void before(){
//        User user = new User("1","vasya", "vasichkin", "123");
//        userDao.save(user);
//        FilesStore fs = new FilesStore("TTT",0, user);
//        fsd.save(fs);
//        fsId = fs.getNumber();
//    }
//    @After
//    public void after(){
//        userDao.delete("1");
//    }
//    @Test
//    public void findWithFileNameAndUserTest(){
//        Assert.assertNotNull(fsd.findWithFileNameAndUser(em.find(FilesStore.class,fsId)));
//    }
//    @Test
//    public void findWithFileNameTest(){
//        Assert.assertNotNull(fsd.findWithFileName(em.find(FilesStore.class,fsId).getFileName()));
//    }
//    @Test
//    public void saveTest(){
//        FilesStore filesStore = new FilesStore("AAA",0,em.find(User.class,"1"));
//        fsd.save(filesStore);
//        Assert.assertNotNull(fsd.findWithFileName("AAA").get(0));
//    }
//    @Test
//    public void deleteTest(){
//        fsd.delete(fsd.findWithFileName("TTT").get(0));
//        Assert.assertTrue(fsd.findWithFileName("TTT").isEmpty());
//    }
//    @Test
//    public void mergeFileStoreTest(){
//        FilesStore fs = fsd.findWithFileName("TTT").get(0);
//        FilesStore unexpected = fsd.findWithFileName("TTT").get(0);
//        fs.setPrivacy(1);
//        fsd.mergeFileStore(fs);
//        Assert.assertNotEquals(fsd.findWithFileName("TTT").get(0),unexpected);
//    }
//
//    @Test
//    public void findSpecialFilesTest(){
//        User user = new User("1","vasya", "vasichkin", "123");
//        FilesStore fs = new FilesStore("AAA",2, user);
//        fsd.save(fs);
//        specialAccessFilesStoreDao.save(fs,"1");
//        Assert.assertNotNull(fsd.findSpecialFiles(fs).get(0));
//
//    }
}
