package dao.impl;

import dao.UserDao;
import model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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
        User u = find(login);
        //если есть тот который хотим удалить, то удаляем, если его нет, создадим и удалим
        entityManager.remove(entityManager.contains(u) ? u : entityManager.merge(u));
    }

    @Override
    public User find(String id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("FROM User s", User.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void mergeUser(User user){
        entityManager.merge(user);
    }

}
