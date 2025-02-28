package org.example.lab_4_jakartaee.managerDB;

import org.example.lab_4_jakartaee.entity.Point;

public class PointDAO extends AbstractEntityDAO<Point> {
    @Override
    public void save(Point point) {
        var em = getEm();

        em.getTransaction().begin();
        em.persist(point);
        em.getTransaction().commit();
    }
}
