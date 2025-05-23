package dao.h2;

import entities.FinishedMatch;
import exceptions.DataBaseException;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

public class FinishedMatchDAOImpl implements FinishedMatchDAO {

    @Override
    public void save(FinishedMatch finishedMatch) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(finishedMatch);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DataBaseException("Problem with save in FinishedMatchDAOImpl");
        }
    }

    @Override
    public List<FinishedMatch> getAll(int start, int maxResult) {
        List<FinishedMatch> matches;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM FinishedMatch", FinishedMatch.class);
            query.setFirstResult(start-1);
            query.setMaxResults(maxResult);
            matches = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
        return matches;
    }

    @Override
    public List<FinishedMatch> getByName(String name, int start, int maxResult) {
        String hqlQuery = "FROM FinishedMatch as match " +
                          "WHERE match.player1.name='" + name + "' OR match.player2.name='" + name + "'";
        List<FinishedMatch> matches;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            Query query = session.createQuery(hqlQuery, FinishedMatch.class);
            query.setFirstResult(start -1);
            query.setMaxResults(maxResult);
            matches = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e){
            throw new DataBaseException(e.getMessage());
        }
        return matches;
    }

    @Override
    public long amountOfRecords() {
        String hqlQuery = "SELECT COUNT(matches) FROM FinishedMatch AS matches";
        Long result;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
           session.beginTransaction();
           result = session.createQuery(hqlQuery, Long.class).getSingleResult();
           session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public long amountOfRecords(String filterName) {
        String hqlQuery = "SELECT COUNT(matches) " +
                          "FROM FinishedMatch AS matches " +
                          "WHERE matches.player1.name='" + filterName + "' OR matches.player2.name='" + filterName + "'";
        Long result;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            result = session.createQuery(hqlQuery, Long.class).getSingleResult();
            session.getTransaction().commit();
        }
        return result;
    }
}
