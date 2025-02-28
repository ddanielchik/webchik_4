package org.example.lab_4_jakartaee.managerDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.example.lab_4_jakartaee.entity.User;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLOutput;

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
}
