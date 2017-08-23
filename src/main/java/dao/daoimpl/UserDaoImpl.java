package dao.daoimpl;

import dao.daointerfaces.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext //injection of entityManager
    private EntityManager entityManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional //modify the information in the database, so we need a transaction in order to commit our changes.
    public void save(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
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

    @Override
    @Transactional
    public void changeFirstName(String id, String name, String login) {
        User u = find(id);
        if (login.equals(u.getId())) {
            u.setName(name);
            entityManager.merge(u);
        }
    }
    @Override
    @Transactional
    public void changeLastName(String id, String lastName, String login) {
        User u = find(login);
        if (login.equals(u.getId())) {
            u.setLastName(lastName);
            entityManager.merge(u);
        }
    }
    @Override
    @Transactional
    public void changePassword(String id, String password, String login) {
        User u = find(login);
        if (login.equals(u.getId())) {
            u.setPassword(password);
            entityManager.merge(u);
        }
    }
}
