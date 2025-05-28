package services;

import dao.h2.PlayerDao;
import dao.h2.PlayersDAOImpl;
import entities.Player;

import java.util.Optional;
import java.util.UUID;

public class OngoingMatchFactory {

    public static UUID create(String name1, String name2) {
        Player player1 = getOrSave(name1);
        Player player2 = getOrSave(name2);

        OngoingMatch match = new OngoingMatch(player1, player2);
        UUID uuid = UUID.randomUUID();
        OngoingMatchesStorage matches = OngoingMatchesStorage.getInstance();
        matches.addMatch(uuid, match);
        return uuid;
    }

    private static Player getOrSave(String name) {
        PlayerDao dao = new PlayersDAOImpl();
        Optional<Player> playerOpt = dao.find(name);
        if (playerOpt.isPresent()) {
            return playerOpt.get();
        }
        Player player = new Player();
        player.setName(name);
        dao.save(player);
        return player;
    }

}
