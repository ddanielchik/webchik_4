package org.example.lab_4_jakartaee.managerDB;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import org.example.lab_4_jakartaee.entity.User;


@Stateless
public class UserDAO extends AbstractEntityDAO<User> {
    // методы сохранения пользователей, нахождения по  имени, проверка совпадения паролей
    // тут можнот наверное реализовать "ваш парол слишком леккий, в пень идите))"
    // метод сохр пользователей
    public UserDAO() {
    }

    public void save(User user) {
        var em = getEm();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public User findByUsername(String username) {
        var em = getEm();
        em.getTransaction().begin();
        User user = em.find(User.class, username);
        em.getTransaction().commit();
        return user;
    }

    public boolean existsByUsername(String username) {
        var em = getEm();
        em.getTransaction().begin();
        User user = em.find(User.class, username);
        em.getTransaction().commit();
        return user != null;
    }

    public boolean authenticate(String username, String password) {
        var em = getEm();
        em.getTransaction().begin();
        User user = em.find(User.class, username);
        em.getTransaction().commit();
        return user.chekPassword(password);
    }

}
