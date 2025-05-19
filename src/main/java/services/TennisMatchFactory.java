package services;

import dao.h2.PlayerDao;
import dao.h2.PlayersDAOImpl;
import entities.Player;

import java.util.UUID;

public class TennisMatchFactory {

    public static UUID create(String name1, String name2){
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setName(name1);
        player2.setName(name2);

        PlayerDao dao = new PlayersDAOImpl();
        player1 = dao.save(player1);
        player2 = dao.save(player2);

        TennisMatch match = new TennisMatch(player1, player2);
        UUID uuid = UUID.randomUUID();
        TennisMatchesStorage matches = TennisMatchesStorage.getInstance();
        matches.addMatch(uuid, match);
        return uuid;
    }
}
