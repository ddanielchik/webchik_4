package org.example.lab_4_jakartaee.managerDB;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.example.lab_4_jakartaee.entity.User;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLOutput;

public class UserDAO extends AbstractEntityDAO{
    // методы сохранения пользователей, нахождения по  имени, проверка совпадения паролей
    // тут можнот наверное реализовать "ваш парол слишком леккий, в пень идите))"
    // метод сохр пользователей
     public UserDAO() {}
    @Override
    public void save(Object entity) {
        super.getEm().getTransaction().begin();
       // em.persist() — сохраняет сущность в базе данных. кароч добавляем данные в табличку))
        super.getEm().persist(entity);
        super.getEm().getTransaction().commit();



    }

    @Override
    public void find(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }
}
