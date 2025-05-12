package dao.h2;

import exceptions.DataBaseException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex){
            throw new DataBaseException("Session was not set up");
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
