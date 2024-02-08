package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LanguageDAO extends SuperDAO<Language> {
    public LanguageDAO(Session session) {
        super(Language.class, session);
    }
}
