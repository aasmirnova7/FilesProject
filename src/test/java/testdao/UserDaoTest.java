package testdao;

import model.User;
import org.junit.*;

public class UserDaoTest extends JPAHibernateTest{

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

    @Ignore
    @Test
    public void testUserDelete(){ //Wrong
        //User user = userDao.find("1");
        userDao.delete(userDao.find("1"));
        Assert.assertNull(userDao.find("1"));
    }

    @Test
    public void testUserChangeFirstName(){
        User user = userDao.find("1");
        User userUnexpected = userDao.find("1");
        userDao.changeFirstName(user,"adam");
        Assert.assertNotEquals(user.getName(), userUnexpected.getName());
    }
    @Test
    public void testUserChangeLastName(){
        User user = userDao.find("1");
        User userUnexpected = userDao.find("1");
        userDao.changeLastName(user,"adov");
        Assert.assertNotEquals(user.getLastName(), userUnexpected.getLastName());
    }
    @Test
    public void testUserChangePassword(){
        User user = userDao.find("1");
        User userUnexpected = userDao.find("1");
        userDao.changePassword(user,"123");
        Assert.assertNotEquals(user.getPassword(), userUnexpected.getPassword());
    }
}
