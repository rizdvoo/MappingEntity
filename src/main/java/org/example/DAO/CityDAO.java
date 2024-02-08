package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CityDAO extends SuperDAO<City> {
    public CityDAO(Session session) {
        super(City.class, session);
    }
}
