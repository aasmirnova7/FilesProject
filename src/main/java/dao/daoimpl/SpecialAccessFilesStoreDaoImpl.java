package dao.daoimpl;

import dao.daointerfaces.SpecialAccessFilesStoreDao;
import model.FilesStore;
import model.SpecialAccessFilesStore;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
public class SpecialAccessFilesStoreDaoImpl implements SpecialAccessFilesStoreDao {

    @PersistenceContext //injection of entityManager
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(FilesStore filesStore, String idAccessed) {
        if(filesStore.getPrivacy() == 2) {
            TypedQuery<SpecialAccessFilesStore> query = entityManager.createQuery("FROM SpecialAccessFilesStore s WHERE s.filesStore= :file AND s.idAccessed = :id", SpecialAccessFilesStore.class);
            query.setParameter("file", filesStore);
            query.setParameter("id", idAccessed);
            if (query.getResultList().isEmpty()) {
                SpecialAccessFilesStore safs = new SpecialAccessFilesStore(idAccessed, filesStore);
                entityManager.persist(safs);
            }
        }
        //Иначе говорим, что нужно менять privacy
        //Если не добавляем, то выдвать сообщение
    }
    @Override
    @Transactional
    public void delete(SpecialAccessFilesStore filesStore) {
        entityManager.remove(entityManager.find(filesStore.getClass(),filesStore.getNumber()));
    }
}
