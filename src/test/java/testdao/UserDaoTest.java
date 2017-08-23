package testdao;

import dao.daointerfaces.UserDao;
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
    PasswordEncoder passwordEncoder;
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
        User user1 = new User("7","karl", "sidorov", "789");
        userDao.save(user1);
        Assert.assertNotNull(userDao.find(user1.getId()));
    }
    @Test
    public void testUserDelete(){
        userDao.delete("7");
        Assert.assertNull(userDao.find("7"));
    }
    @Test
    public void testUserChangeFirstName(){
        User userUnexpected = userDao.find("1");
        userDao.changeFirstName("1","adam","1");
        Assert.assertNotEquals(userDao.find("1").getName(), userUnexpected.getName());
    }
    @Test
    public void testUserChangeLastName(){
        User userUnexpected = userDao.find("1");
        userDao.changeLastName("1","adova","1");
        Assert.assertNotEquals(userDao.find("1").getLastName(), userUnexpected.getLastName());
    }
    @Test
    public void testUserChangePassword(){
        User userUnexpected = userDao.find("1");
        userDao.changePassword("1","13","1");
        Assert.assertNotEquals(userDao.find("1").getPassword(), userUnexpected.getPassword());
    }
}
