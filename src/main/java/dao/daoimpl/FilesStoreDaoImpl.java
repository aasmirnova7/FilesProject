package dao.daoimpl;

import dao.daointerfaces.FilesStoreDao;
import dao.daointerfaces.SpecialAccessFilesStoreDao;
import model.FilesStore;
import model.SpecialAccessFilesStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FilesStoreDaoImpl implements FilesStoreDao {
    @PersistenceContext //injection of entityManager
    private EntityManager entityManager;

    @Autowired
    SpecialAccessFilesStoreDao specialAccessFilesStoreDao;

    @Override
    @Transactional
    public void save(FilesStore filesStore,String ... idAccessed){
            TypedQuery<FilesStore> query = entityManager.createQuery("FROM FilesStore s WHERE s.fileName = :name AND s.user = :myuser", FilesStore.class);
            query.setParameter("name", filesStore.getFileName());
            query.setParameter("myuser", filesStore.getUser());
            if (query.getResultList().isEmpty()) {
                entityManager.persist(filesStore);
                if (filesStore.getPrivacy() == 2) {
                    for (String id : idAccessed) {
                        specialAccessFilesStoreDao.save(filesStore, id);
                    }
                }
            } else {
                query.getResultList().get(0).setPrivacy(filesStore.getPrivacy());
            }
    }
    @Override
    @Transactional
    public void delete(String fileName, String myId) {
        TypedQuery<FilesStore> query = entityManager.createQuery("FROM FilesStore s WHERE s.fileName = :name", FilesStore.class);
        query.setParameter("name", fileName);
        List<FilesStore> list = query.getResultList();
        for(FilesStore fs: list){
            if(fs.getUser().getId().equals(myId)){
                entityManager.remove(fs);
                break;
            }
        }
    }

    @Override
    public List<FilesStore> find(String fileName, String login) {
        TypedQuery<FilesStore> query = entityManager.createQuery("FROM FilesStore s where s.fileName = :name", FilesStore.class);
        query.setParameter("name", fileName);

        Integer privacy;
        List<FilesStore> listFilesYouCanSee = new ArrayList<>();
        for (FilesStore fs: query.getResultList()) {
            privacy = fs.getPrivacy();
            switch (privacy){
                case 0:
                    listFilesYouCanSee.add(fs);
                    break;
                case 1:
                    if (login.equals(fs.getUser().getId())){
                        listFilesYouCanSee.add(fs);
                    }
                    break;
                case 2:
                    if (findWithPrivacy(fs,login)){
                        listFilesYouCanSee.add(fs);
                    }
                    break;
            }

        }
        return listFilesYouCanSee;
    }

    @Override //находим файл, c проверкой доступа
    public boolean findWithPrivacy(FilesStore filesStore,String login) {
        List<SpecialAccessFilesStore> accessFilesStoreList = findSpecialFiles(filesStore);
        boolean flag = false;
        for (SpecialAccessFilesStore safs : accessFilesStoreList){
            if (safs.getIdAccessed().equals(login)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public List<SpecialAccessFilesStore> findSpecialFiles(FilesStore filesStore) {
        TypedQuery<SpecialAccessFilesStore> query = entityManager.createQuery("from SpecialAccessFilesStore s where s.filesStore = :store", SpecialAccessFilesStore.class);
        query.setParameter("store", filesStore);
        return query.getResultList();
    }
    @Override
    @Transactional
    public void changeFileName(FilesStore fs, String name,String login) {
        if(fs.getUser().getId().equals(login)){
            fs.setFileName(name);
            entityManager.merge(fs);
        }

    }
    @Override
    @Transactional
    public void changeLevel(FilesStore fs, Integer level, String login) {
        if(fs.getUser().getId().equals(login)&&0<level&&level<3) {
            fs.setPrivacy(level);
            entityManager.merge(fs);
        }
    }

    @Override
    @Transactional
    public void deleteIdAccessed(FilesStore fs, String login, String idAccessed){
        if(fs.getUser().getId().equals(login)){
            for (SpecialAccessFilesStore safs: findSpecialFiles(fs)){
                if (safs.getIdAccessed().equals(idAccessed)){
                    specialAccessFilesStoreDao.delete(safs);
                    break;
                }
            }
        }
    }

    @Override
    @Transactional
    public void addIdAccessed(FilesStore fs, String idAccessed, String login){
       // if(fs.getPrivacy() == 2) {
            if (fs.getUser().getId().equals(login)) {
                specialAccessFilesStoreDao.save(fs, idAccessed);
            }
        //}
        //Если не добавляем, то выдвать сообщение
    }
}