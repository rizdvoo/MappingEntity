package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AddressDAO extends SuperDAO<Address> {

    public AddressDAO(Session session) {
        super(Address.class, session);
    }
}
