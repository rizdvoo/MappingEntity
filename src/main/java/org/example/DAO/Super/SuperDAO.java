package org.example.DAO.Super;

import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Data
public abstract class SuperDAO<T> {

    private final Class<T> clazz;

    private Session mySession;

    public SuperDAO(Class<T> clazz, Session session) {
        this.clazz = clazz;
        this.mySession = session;
    }

    public <Id> Optional<T> getById(Class<Id> idType, Id id) {
        Transaction transaction = mySession.beginTransaction();
        try {
            T result = mySession.find(clazz, idType.cast(id));
            transaction.commit();
            return Optional.of(result);
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List<T>> getItems(int limit, int offset) {
        Transaction transaction = mySession.beginTransaction();
        Query<T> query = null;
        try {
            query = mySession.createQuery("from " + clazz.getName(), clazz);
            query.setMaxResults(limit);
            query.setFirstResult(offset);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return Optional.of(query.list());
    }

    public Optional<List<T>> findAll() {
        Transaction transaction = mySession.beginTransaction();
        Query<T> query = null;
        try {
            query = mySession.createQuery("from" + clazz.getName(), clazz);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return Optional.of(query.list());
    }

    public T save(T entity) {
        Transaction transaction = mySession.beginTransaction();
        try {
            mySession.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return entity;
    }

    public T update(T entity) {
        Transaction transaction = mySession.beginTransaction();
        try {
            mySession.update(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return entity;
    }

    public void delete(T entity) {
        Transaction transaction = mySession.beginTransaction();
        try {
            mySession.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public <Id> void  deleteById(Class<Id> idType, Id id) {
        Optional<T> entity = getById(idType, id);
        entity.ifPresent(this::delete);
    }
}
