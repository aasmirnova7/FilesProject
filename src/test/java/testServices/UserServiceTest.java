package testServices;

import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.interfaces.UserService;
import testDAO.DAOTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DAOTestConfig.class})
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void saveTest(){
        User user = new User("1","adam","petrov","1234");
        userService.save(user);
        System.out.println(userService.find("1"));
        Assert.assertNotNull(userService.find("1"));
    }
    @Test
    public void deleteTest(){
        userService.delete("1");
        Assert.assertNull(userService.find("1"));
    }
    @Test
    public void findTest(){
        User user = new User("2","petya","ivanov","4321");
        userService.save(user);
        Assert.assertNotNull(userService.find("2"));
    }
    @Test
    public void changeFirstNameTest(){
        User user = userService.find("1");
        User unexpected = userService.find("1");
        userService.changeFirstName("1","kostuya","1");
        Assert.assertNotEquals(userService.find("1").getName(),unexpected.getName());
    }
    @Test
    public void changeLastNameTest(){
        User user = userService.find("1");
        User unexpected = userService.find("1");
        userService.changeLastName("1","sidorov","1");
        Assert.assertNotEquals(userService.find("1").getLastName(),unexpected.getLastName());
    }
    @Test
    public void changePasswordTest(){
        User user = userService.find("1");
        User unexpected = userService.find("1");
        userService.changePassword("1","890","1");
        Assert.assertNotEquals(userService.find("1").getPassword(),unexpected.getPassword());
    }
}
