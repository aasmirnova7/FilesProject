package testdao;

import dao.daointerfaces.FilesStoreDao;
import model.FilesStore;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;

public class FilesStoreDaoTest extends JPAHibernateTest {

    @Test
    public void testFind(){
        Assert.assertNotNull(fsd.find( new FilesStore("TTT",0, userDao.find("1"))));
    }
    @Test
    public void testFindWithPrivacy(){ //Дописать
        //Assert.assertNotNull(fsd.find( new FilesStore("TTT",0, userDao.find("1"))));
    }
    @Test
    public void save(){
        FilesStore fs = new FilesStore("TTT",0, userDao.find("1"));
        fsd.save(fs);
        Assert.assertNotNull(fsd.find(fs));
    }
    @Ignore
    @Test
    public void delete(){ //Wrong
        FilesStore fs = fsd.find( new FilesStore("TTT",0, userDao.find("1"))).get(0);
        fsd.delete(fs);
        Assert.assertNull(fsd.find(fs));
    }

    @Test
    public void testChangeFileName(){
        FilesStore fs = fsd.find( new FilesStore("TTT",0, userDao.find("1"))).get(0);
        FilesStore fsUnexpected = fsd.find( new FilesStore("TTT",0, userDao.find("1"))).get(0);
        fsd.changeFileName(fs,"UUU");
        Assert.assertNotEquals(fs.getFileName(), fsUnexpected.getFileName());
    }
    @Test
    public void testChangeLevel(){
        FilesStore fs = fsd.find( new FilesStore("UUU",0, userDao.find("1"))).get(0);
        FilesStore fsUnexpected = fsd.find( new FilesStore("UUU",0, userDao.find("1"))).get(0);
        fsd.changeLevel(fs,1);
        Assert.assertNotEquals(fs.getPrivacy(), fsUnexpected.getPrivacy());
    }
}
