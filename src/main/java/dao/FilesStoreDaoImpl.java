package dao;


import model.FilesStore;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class FilesStoreDaoImpl implements FilesStoreDao {
    @PersistenceContext //injection of entityManager
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(FilesStore filesStore) {
        entityManager.persist(filesStore);

    }
    @Override
    @Transactional
    public void delete(FilesStore filesStore) {
        entityManager.remove(filesStore);
    }
    @Override
    public FilesStore find(FilesStore filesStore) {
        return entityManager.find(FilesStore.class, "fileName");
        //Нужно будет проверить уровень доступа к файлу
    }

    @Override
    @Transactional
    public void changeFileName(FilesStore fs, String name) {
        //if(fs.getIdOwner() == )
        //Можно менять только владельцу
        fs.setFileName(name);
        entityManager.merge(fs);
    }
    @Override
    @Transactional
    public void changeLevel(FilesStore fs, Integer level) {
        //Можно менять только владельцу
        //если level не 0,1,2 то вернуть error
        fs.setPrivacy(level);
        entityManager.merge(fs);
    }
}