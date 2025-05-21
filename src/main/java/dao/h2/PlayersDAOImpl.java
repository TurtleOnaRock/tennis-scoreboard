package dao.h2;

import exceptions.DataBaseException;
import entities.Player;
import org.hibernate.Session;

import java.util.Optional;

public class PlayersDAOImpl implements PlayerDao {

    @Override
    public void save(Player player) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DataBaseException("Problem to save " + player.getName());
        }
    }

    @Override
    public Optional<Player> find(String name) {
        Player player;
        String query = "FROM Player as player WHERE player.name ='" + name + "'";
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            player = session.createQuery(query, Player.class).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
        return Optional.ofNullable(player);
    }
}
