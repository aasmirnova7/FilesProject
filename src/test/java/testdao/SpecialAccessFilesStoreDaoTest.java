package testdao;

import dao.daointerfaces.FilesStoreDao;
import dao.daointerfaces.SpecialAccessFilesStoreDao;
import dao.daointerfaces.UserDao;
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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//Доделать
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DAOTestConfig.class})
public class SpecialAccessFilesStoreDaoTest{
    @Autowired
    private UserDao userDao;
    @Autowired
    private FilesStoreDao fsd;
    @Autowired
    private SpecialAccessFilesStoreDao safsd;
    @PersistenceContext
    private EntityManager em;

    @Test
    public void testSaveSpecialAccessedFile(){
        User user = new User("1","vasya", "vasichkin", "13");
        User user1 = new User("2","kate", "trololo", "235");
        userDao.save(user);
        FilesStore fs = new FilesStore("TTT",2,user);
        fsd.save(fs,"2");
        Assert.assertNotNull(fsd.findSpecialFiles(fs));
    }
    @Test
    public void testDeleteSaveSpecialAccessedFile(){
        User user = new User("3","petya", "vasichkin", "13");
        User user1 = new User("4","olga", "trololo", "235");
        userDao.save(user);
        FilesStore fs = new FilesStore("AAA",2,user);
        fsd.save(fs,"4");
        fsd.deleteIdAccessed(fs,"3","4");
        System.out.println("!!!!!!!!"+fsd.findSpecialFiles(fs));
        Assert.assertEquals(fsd.findSpecialFiles(fs).isEmpty(),true);
    }
}
