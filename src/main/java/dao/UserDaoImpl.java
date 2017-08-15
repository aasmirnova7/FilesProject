package dao;

import model.User;
import org.springframework.expression.Operation;
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
        //Если таблица не пустая, то проверить, нет ли повторов
        entityManager.persist(user);
    }
    @Override
    @Transactional
    public void delete(User u) {
        //Если удаляем строку из табл., нужно удалить все его файлы
        entityManager.remove(u);
    }

    @Override
    public User find(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void changeFirstName(User u, String name) {
        //Можно менять только владельцу
        u.setName(name);
        entityManager.merge(u);
    }
    @Override
    @Transactional
    public void changeLastName(User u, String lastName) {
        //Можно менять только владельцу
        u.setLastName(lastName);
        entityManager.merge(u);
    }
    @Override
    @Transactional
    public void changePassword(User u, Integer password) {
        //Можно менять только владельцу
        u.setPassword(password);
        entityManager.merge(u);
    }
}
