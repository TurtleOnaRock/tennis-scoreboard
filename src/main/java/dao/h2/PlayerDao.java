package dao.h2;

import entities.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDao {

    Player save (Player player);

    Optional<Player> findPlayer(String name);

    List<Player> getPlayers();

}
