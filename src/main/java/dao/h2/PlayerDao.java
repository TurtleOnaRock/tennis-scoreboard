package dao.h2;

import entities.Player;

import java.util.Optional;

public interface PlayerDao {

    void save(Player player);

    Optional<Player> find(String name);
}
