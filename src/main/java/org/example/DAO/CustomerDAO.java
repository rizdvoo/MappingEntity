package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class CustomerDAO extends SuperDAO<Customer> {
    public CustomerDAO(Session session) {
        super(Customer.class, session);
    }
}
