package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StoreDAO extends SuperDAO<Store> {
    public StoreDAO(Session session) {
        super(Store.class, session);
    }
}
