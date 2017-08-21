package testdao;


import dao.daointerfaces.FilesStoreDao;
import dao.daointerfaces.UserDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class JPAHibernateTest {
    ConfigurableApplicationContext context = new AnnotationConfigApplicationContext("config", "spring");
    @Resource
    UserDao userDao = context.getBean(UserDao.class);
    @Resource
    FilesStoreDao fsd = context.getBean(FilesStoreDao.class);

    @After
    public void end(){
        context.close();
    }
//    protected static LocalContainerEntityManagerFactoryBean emf;
//    protected static EntityManager em;
//
//    @BeforeClass
//    public static void init() throws FileNotFoundException, SQLException {
//        emf = Persistence.createEntityManagerFactory("mnf-pu-test");
//        em = emf.createEntityManager();
//    }
//
//    @Before
//    public void initializeDatabase(){
//        Session session = em.unwrap(Session.class);
//        session.doWork(new Work() {
//            @Override
//            public void execute(Connection connection) throws SQLException {
//                try {
//                    File script = new File(getClass().getResource("/data.sql").getFile());
//                    RunScript.execute(connection, new FileReader(script));
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException("could not initialize with script");
//                }
//            }
//        });
//    }
//
//    @AfterClass
//    public static void tearDown(){
//        em.clear();
//        em.close();
//        emf.close();
//    }
}
