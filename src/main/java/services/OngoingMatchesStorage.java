package services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesStorage {

    private static OngoingMatchesStorage instance;
    private Map<UUID, OngoingMatch> matches;

    private OngoingMatchesStorage() {
        matches = new HashMap<>();
    }

    public static OngoingMatchesStorage getInstance() {
        if (instance == null) {
            instance = new OngoingMatchesStorage();
        }
        return instance;
    }

    public void addMatch(UUID uuid, OngoingMatch match) {
        this.matches.put(uuid, match);
    }

    public OngoingMatch getMatch(UUID uuid) {
        return this.matches.get(uuid);
    }
}
