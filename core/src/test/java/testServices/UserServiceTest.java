package testServices;

import model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.UserService;
import testDAO.DAOTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DAOTestConfig.class})
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Before
    public void before(){
        User user = new User("1","adam","petrov","1234");
        userService.save(user);
    }
    @After
    public void after(){
        userService.delete("1");
    }

    @Test
    public void saveTest(){
        Assert.assertNotNull(userService.find("1"));
    }
    @Test
    public void deleteTest(){
        User user = new User("2","kate","petrova","333");
        userService.save(user);
        userService.delete("2");
        Assert.assertNull(userService.find("2"));
    }
    @Test
    public void findTest(){
        Assert.assertNotNull(userService.find("1"));
    }
    @Test
    public void changeFirstNameTest(){
        User user = userService.find("1");
        User unexpected = userService.find("1");
        userService.changeFirstName("1","kostuya");
        Assert.assertNotEquals(userService.find("1").getName(),unexpected.getName());
    }
    @Test
    public void changeLastNameTest(){
        User user = userService.find("1");
        User unexpected = userService.find("1");
        userService.changeLastName("1","sidorov");
        Assert.assertNotEquals(userService.find("1").getLastName(),unexpected.getLastName());
    }
    @Test
    public void changePasswordTest(){
        User user = userService.find("1");
        User unexpected = userService.find("1");
        userService.changePassword("1","890");
        Assert.assertNotEquals(userService.find("1").getPassword(),unexpected.getPassword());
    }
}
