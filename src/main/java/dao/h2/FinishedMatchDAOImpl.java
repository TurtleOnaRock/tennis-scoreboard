package dao.h2;

import entities.FinishedMatch;
import exceptions.DataBaseException;
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
    public List<FinishedMatch> getAll() {
        List<FinishedMatch> matches;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            matches = session.createQuery("FROM FinishedMatch", FinishedMatch.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DataBaseException("Problem with getAll in FinishedMatchDAOImpl");
        }
        return matches;
    }

    @Override
    public List<FinishedMatch> getByName(String name) {
        String hqlQuery = "FROM FinishedMatch as match WHERE match.player1.name='" + name + "'"
                                                            + " OR "
                                                            + "match.player2.name='" + name + "'";
        List<FinishedMatch> finishedMatches;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            finishedMatches = session.createQuery(hqlQuery, FinishedMatch.class).list();
            session.getTransaction().commit();
        }
        return finishedMatches;
    }
}
