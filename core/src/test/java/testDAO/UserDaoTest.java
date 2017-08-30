package testDAO;

import dao.UserDao;
import model.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DAOTestConfig.class})
public class UserDaoTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @PersistenceContext
    private EntityManager em;

    @Before
    public void before(){
        User user = new User("1","vasya", "vasichkin", "123");
        userDao.save(user);
    }
    @After
    public void after(){
        userDao.delete("1");
    }

    @Test
    public void testFindById(){
        Assert.assertNotNull(userDao.find("1"));
    }
    @Test
    public void testSaveUser(){
        User user1 = new User("2","karl", "sidorov", "789");
        userDao.save(user1);
        Assert.assertNotNull(userDao.find(user1.getId()));
    }
    @Test
    public void testUserDelete(){
        User user1 = new User("3","vaniya", "ivanov", "456");
        userDao.save(user1);
        userDao.delete("3");
        Assert.assertNull(userDao.find("3"));
    }
    @Test
    public void change() {
        User u = userDao.find("1");
        User unexpected = userDao.find("1");
        u.setLastName("qqqqq");
        userDao.mergeUser(u);
        Assert.assertNotEquals(unexpected,userDao.find("1"));
    }
}
