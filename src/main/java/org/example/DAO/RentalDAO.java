package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Rental;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;


public class RentalDAO extends SuperDAO<Rental> {
    public RentalDAO(Session session) {
        super(Rental.class, session);
    }

    public Optional<Rental> getUnreturnedRental() {
        Transaction transaction = getMySession().beginTransaction();
        try {
            String hql = "select r from Rental r where r.returnDate is null";
            Query<Rental> query = getMySession().createQuery(hql, Rental.class);
            query.setMaxResults(1);
            transaction.commit();
            return Optional.ofNullable(query.getSingleResult());
        } catch(Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
