package dao;

import model.FilesStore;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class FilesStoreDaoImpl implements FilesStoreDao {
    @PersistenceContext //injection of entityManager
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(FilesStore filesStore) { //OK!!!
        List<FilesStore> list = find(filesStore);
        if(list.size() == 0)
            entityManager.persist(filesStore);
        else {
            list.get(0).setPrivacy(filesStore.getPrivacy());
        }
    }
    @Override
    @Transactional
    public void delete(FilesStore filesStore) {
        entityManager.remove(filesStore);
    }

    @Override//Просто находим файл, без проверки доступа
    public List<FilesStore> find(FilesStore filesStore) {
        TypedQuery<FilesStore> query = entityManager.createQuery("FROM FilesStore s where s.fileName = :name AND s.idOwner = :owner ", FilesStore.class);
        query.setParameter("name", filesStore.getFileName());
        query.setParameter("owner", filesStore.getIdOwner());
        return query.getResultList();
    }

    @Override //находим файл, c проверкой доступа
    public FilesStore findWithPrivacy(FilesStore filesStore) {
        List<FilesStore> list = find(filesStore);
        //Тут нужно проверить Id пользователя, который запрашивает
        //if(list.isEmpty() == false && list.get(0).getPrivacy() == 1 && list.get(0).getIdOwner() != myId )//Проверка на то, что мы являемся владельцем
          //  return "Пользователь не может посмотреть";
        if(list.get(0).getPrivacy() == 2 ) // Проверка по другой таблице
        ;

        return list.get(0);
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