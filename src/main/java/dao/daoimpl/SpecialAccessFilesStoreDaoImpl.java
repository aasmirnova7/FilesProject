package dao.daoimpl;

import dao.daointerfaces.SpecialAccessFilesStoreDao;
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
        if(!entityManager.contains(filesStore)) {
            entityManager.persist(filesStore);
        }
    }
    @Override
    @Transactional
    public void delete(SpecialAccessFilesStore filesStore) {
        entityManager.remove(filesStore);
    }


}
