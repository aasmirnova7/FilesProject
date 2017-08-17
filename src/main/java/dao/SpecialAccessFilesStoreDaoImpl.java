package dao;

import model.FilesStore;
import model.SpecialAccessFilesStore;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

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

    @Override
    public List<SpecialAccessFilesStore> find(SpecialAccessFilesStore filesStore) {
        TypedQuery<SpecialAccessFilesStore> query = entityManager.createQuery("from SpecialAccessFilesStore s where s.filesStore = :store", SpecialAccessFilesStore.class);
        query.setParameter("store", filesStore.getFilesStore());
        return query.getResultList();
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
