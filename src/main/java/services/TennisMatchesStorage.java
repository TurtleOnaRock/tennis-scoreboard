package services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TennisMatchesStorage {

    private static TennisMatchesStorage instance;
    private Map<UUID, TennisMatch> matches;

    private TennisMatchesStorage(){
        matches = new HashMap<>();
    }

    public static TennisMatchesStorage getInstance(){
        if(instance == null){
            instance = new TennisMatchesStorage();
        }
        return instance;
    }

    public void addMatch(UUID uuid, TennisMatch match){
        this.matches.put(uuid, match);
    }

    public TennisMatch getMatch(UUID uuid){
        return this.matches.get(uuid);
    }
}
