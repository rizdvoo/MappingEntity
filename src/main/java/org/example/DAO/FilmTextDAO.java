package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.FilmText;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends SuperDAO<FilmText> {
    public FilmTextDAO(Session session) {
        super(FilmText.class, session);
    }
}
