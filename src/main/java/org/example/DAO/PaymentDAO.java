package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PaymentDAO extends SuperDAO<Payment> {
    public PaymentDAO(Session session) {
        super(Payment.class, session);
    }
}
