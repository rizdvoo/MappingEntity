package org.example.DAO;

import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CategoryDAO extends SuperDAO<Category> {

    public CategoryDAO(Session session) {
        super(Category.class, session);
    }
}
