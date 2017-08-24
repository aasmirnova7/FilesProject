package dao.daoimpl;

import dao.daointerfaces.UserDao;
import model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext //injection of entityManager
    private EntityManager entityManager;

    @Override
    @Transactional //modify the information in the database, so we need a transaction in order to commit our changes.
    public void save(User user) {
        if(!entityManager.contains(user)) {
            entityManager.persist(user);
        }
    }
    @Override
    @Transactional
    public void delete(String login) {
        //Если удаляем строку из табл., нужно удалить все его файлы
        User u = find(login);
        //если есть тот который хотим удалить, то удаляем, если его нет, создадим и удалим
        entityManager.remove(entityManager.contains(u) ? u : entityManager.merge(u));
    }

    @Override
    public User find(String id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void mergeUser(User user){
        entityManager.merge(user);
    }

}
