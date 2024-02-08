package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CountryDAO extends SuperDAO<Country> {
    public CountryDAO(Session session) {
        super(Country.class, session);
    }
}
