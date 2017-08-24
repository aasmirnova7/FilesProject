package testDAO;

import dao.daointerfaces.FilesStoreDao;
import dao.daointerfaces.SpecialAccessFilesStoreDao;
import dao.daointerfaces.UserDao;
import model.FilesStore;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
        userDao.save(user);
        FilesStore fs = new FilesStore("TTT",2,user);
        fsd.save(fs);
        safsd.save(fs,"1");
        Assert.assertNotNull(fsd.findSpecialFiles(fs).get(0));
    }
    @Test
    public void testDeleteSaveSpecialAccessedFile(){
        safsd.delete(fsd.findSpecialFiles(fsd.findWithFileName("TTT").get(0)).get(0));
        Assert.assertTrue(fsd.findSpecialFiles(fsd.findWithFileName("TTT").get(0)).isEmpty());
    }
}
