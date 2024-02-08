package org.example.DAO;


import org.example.DAO.Super.SuperDAO;
import org.example.Entity.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StaffDAO extends SuperDAO<Staff> {
    public StaffDAO(Session session) {
        super(Staff.class, session);
    }
}
