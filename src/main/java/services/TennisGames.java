package services;

import java.util.Map;
import java.util.UUID;

public class TennisGames {

    private static TennisGames instance;
    private Map<UUID, TennisGame> games;

    private TennisGames(){}

    public static TennisGames getInstance(){
        if(instance == null){
            instance = new TennisGames();
        }
        return instance;
    }

    public void addGame(UUID uuid, TennisGame game){
        this.games.put(uuid, game);
    }

    public TennisGame getGame(UUID uuid){
        return this.games.get(uuid);
    }
}
