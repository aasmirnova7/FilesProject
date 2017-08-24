package dao.daoimpl;

import dao.daointerfaces.FilesStoreDao;
import model.FilesStore;
import model.SpecialAccessFilesStore;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FilesStoreDaoImpl implements FilesStoreDao {
    @PersistenceContext //injection of entityManager
    private EntityManager entityManager;

    @Override
    public List<FilesStore> findWithFileNameAndUser(FilesStore filesStore){
        TypedQuery<FilesStore> query = entityManager.createQuery("FROM FilesStore s WHERE s.fileName = :name AND s.user = :myuser", FilesStore.class);
        query.setParameter("name", filesStore.getFileName());
        query.setParameter("myuser", filesStore.getUser());
        return query.getResultList();
    }
    @Override
    public  List<FilesStore> findWithFileName(String fileName){
        TypedQuery<FilesStore> query = entityManager.createQuery("FROM FilesStore s WHERE s.fileName = :name", FilesStore.class);
        query.setParameter("name", fileName);
        return query.getResultList();
    }
    @Override
    @Transactional
    public void save(FilesStore filesStore){
        entityManager.persist(filesStore);
    }

    @Override
    @Transactional
    public void delete(FilesStore file) {
        entityManager.remove(entityManager.find(file.getClass(),file.getNumber()));
    }

    @Override
    public List<SpecialAccessFilesStore> findSpecialFiles(FilesStore filesStore) {
        TypedQuery<SpecialAccessFilesStore> query = entityManager.createQuery("from SpecialAccessFilesStore s where s.filesStore = :store", SpecialAccessFilesStore.class);
        query.setParameter("store", filesStore);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void mergeFileStore(FilesStore filesStore){
        entityManager.merge(filesStore);
    }

}