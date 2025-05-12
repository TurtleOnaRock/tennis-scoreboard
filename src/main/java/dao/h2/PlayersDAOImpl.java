package dao.h2;

import exceptions.DataBaseException;
import entities.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class PlayersDAOImpl implements PlayerDao {

    @Override
    public void save(String name) {
        Player player = new Player();
        player.setName(name);
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(player);
            session.getTransaction().commit();
        } catch (Exception e){
            throw new DataBaseException("Problem in save");
        }
    }

    @Override
    public Optional<Player> findPlayer(String name) {
        Player player;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            player = session.get(Player.class, name);
            session.getTransaction().commit();
        }catch (HibernateException e){
            throw new DataBaseException("");
        }
        if(player == null){
            return Optional.empty();
        }
        return Optional.of(player);
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> players;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            players = session.createQuery("from Player", Player.class).list();
            session.getTransaction().commit();
        } catch (HibernateException e){
            throw new DataBaseException("Problem in a getPlayers");

        }
        return players;
    }
}
