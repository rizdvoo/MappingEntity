package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Inventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InventoryDAO extends SuperDAO<Inventory> {
    public InventoryDAO(Session session) {
        super(Inventory.class, session);
    }
}
