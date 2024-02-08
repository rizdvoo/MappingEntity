package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Film;
import org.example.Entity.Rental;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class FilmDAO extends SuperDAO<Film> {
    public FilmDAO(Session session) {
        super(Film.class, session);
    }

    public Optional<Film> getAvailableFilmForRent() {
        Transaction transaction = getMySession().beginTransaction();
        try {
            String hql = "select * from Movies.film where film.film_id not in (select distinct Movies.inventory.film_id from Movies.inventory)";
            Query<Film> query = getMySession().createNativeQuery(hql, Film.class);
            query.setMaxResults(1);
            transaction.commit();
            return Optional.of(query.getSingleResult());
        } catch(Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
