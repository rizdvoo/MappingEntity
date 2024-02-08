package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorDAO extends SuperDAO<Actor> {
    public ActorDAO(Session session) {
        super(Actor.class, session);
    }
}
