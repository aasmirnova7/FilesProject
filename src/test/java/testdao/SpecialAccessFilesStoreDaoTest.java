package testdao;

import dao.daointerfaces.FilesStoreDao;
import dao.daointerfaces.SpecialAccessFilesStoreDao;
import dao.daointerfaces.UserDao;
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
}
