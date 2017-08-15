package dao;

import model.SpecialAccessFilesStore;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class SpecialAccessFilesStoreDaoImpl implements SpecialAccessFilesStoreDao {

    @PersistenceContext //injection of entityManager
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(SpecialAccessFilesStore filesStore) {
        //Если таблица не пустая, то проверить, нет ли повторов
        entityManager.persist(filesStore);
    }
    @Override
    @Transactional
    public void delete(SpecialAccessFilesStore filesStore) {
        entityManager.remove(filesStore);
    }

    //Переписать поиск сновым primary key
    @Override
    public SpecialAccessFilesStore find(SpecialAccessFilesStore filesStore) {
        return entityManager.find(SpecialAccessFilesStore.class, "fileName");
    }
    @Override
    @Transactional
    public void changeFileName(SpecialAccessFilesStore fs, String name) {
        //if(fs.getIdOwner() == )
        //Можно менять только владельцу
        fs.setFileName(name);
        entityManager.merge(fs);
    }
    @Override
    @Transactional
    public void changeIdAccessed(SpecialAccessFilesStore fs, Long idAccessed) {
        //if(fs.getIdOwner() == )
        //Можно менять только владельцу
        fs.setIdAccessed(idAccessed);
        entityManager.merge(fs);
    }
}
