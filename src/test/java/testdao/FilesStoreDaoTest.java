package testdao;

import dao.daointerfaces.FilesStoreDao;
import dao.daointerfaces.UserDao;
import model.FilesStore;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//Доделать
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DAOTestConfig.class})
public class FilesStoreDaoTest{
    @Autowired
    private UserDao userDao;
    @Autowired
    private FilesStoreDao fsd;

    //private Integer fsId;

    @PersistenceContext
    private EntityManager em;

    @Before
    public void before(){
        User user = new User("1","vasya", "vasichkin", "123");
        userDao.save(user);
        FilesStore fs = new FilesStore("TTT",0, userDao.find("1"));
        fsd.save(fs);
        //fsId = fs.getNumber();
    }
    @Test
    public void testFind(){
        Assert.assertNotNull(fsd.find( "TTT","2"));
    }
    @Test
    public void testFindWithPrivacy(){ //Дописать
        //Assert.assertNotNull(fsd.find( new FilesStore("TTT",0, userDao.find("1"))));
    }
//    @Test
//    public void save(){
//        FilesStore fs = new FilesStore("AAA",0, userDao.find("1"));
//        fsd.save(fs);
//        Assert.assertNotNull(fsd.find(fs));
//    }
    @Ignore
    @Test
    public void delete(){ //Wrong
//        User user = new User("2","vasya", "vasichkin", "13");
//        userDao.save(user);
//        FilesStore fs1 = new FilesStore("AAA",0, userDao.find("2"));
//        fsd.save(fs1);
//        FilesStore fs = fsd.find("AAA").get(0);
//        fsd.delete(fs);
//        Assert.assertNull(fsd.find("AAA").get(0));
    }

//    @Test
//    public void testChangeFileName(){
//        FilesStore fs = fsd.find( new FilesStore("TTT",0, userDao.find("1"))).get(0);
//        FilesStore fsUnexpected = fsd.find( new FilesStore("TTT",0, userDao.find("1"))).get(0);
//        fsd.changeFileName(fs,"UUU");
//        Assert.assertNotEquals(fs.getFileName(), fsUnexpected.getFileName());
//    }
//    @Test
//    public void testChangeLevel(){
//        FilesStore fs = fsd.find( new FilesStore("UUU",0, userDao.find("1"))).get(0);
//        FilesStore fsUnexpected = fsd.find( new FilesStore("UUU",0, userDao.find("1"))).get(0);
//        fsd.changeLevel(fs,1);
//        Assert.assertNotEquals(fs.getPrivacy(), fsUnexpected.getPrivacy());
//    }
}
