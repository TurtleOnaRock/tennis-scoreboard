package dao.h2;

import entities.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDao {

    void save (String name);

    Optional<Player> findPlayer(String name);

    List<Player> getPlayers();

}
