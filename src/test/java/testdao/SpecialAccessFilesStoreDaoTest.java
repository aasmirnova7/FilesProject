package testdao;


import dao.daointerfaces.SpecialAccessFilesStoreDao;
import model.FilesStore;
import model.SpecialAccessFilesStore;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;

public class SpecialAccessFilesStoreDaoTest extends JPAHibernateTest{

    @Resource
    SpecialAccessFilesStoreDao sfsd = context.getBean(SpecialAccessFilesStoreDao.class);

    @Test
    public void testFind(){
        Assert.assertNotNull(sfsd.find(
                new SpecialAccessFilesStore((long)2,fsd.find(new FilesStore("UUU",0, userDao.find("1"))).get(0))));
    }
    @Test
    public void save(){
        SpecialAccessFilesStore fs = new SpecialAccessFilesStore((long)2,fsd.find(new FilesStore("UUU",0, userDao.find("1"))).get(0));
        sfsd.save(fs);
        Assert.assertNotNull(sfsd.find(fs));
    }
    @Ignore
    @Test
    public void delete(){ //Wrong
        SpecialAccessFilesStore fs = sfsd.find(new SpecialAccessFilesStore((long)3,fsd.find(new FilesStore("UUU",0, userDao.find("1"))).get(0))).get(0);
        sfsd.delete(fs);
        Assert.assertNull(sfsd.find(fs));
    }
    @Test
    public void testChangeIdAccessed(){
        SpecialAccessFilesStore fs = sfsd.find(new SpecialAccessFilesStore((long)2,fsd.find(new FilesStore("UUU",0, userDao.find("1"))).get(0))).get(0);
        SpecialAccessFilesStore fsUnexpected = sfsd.find(new SpecialAccessFilesStore((long)2,fsd.find(new FilesStore("UUU",0, userDao.find("1"))).get(0))).get(0);
        sfsd.changeIdAccessed(fs,(long) 4);
        Assert.assertNotEquals(fs.getIdAccessed(), fsUnexpected.getIdAccessed());
    }
}
