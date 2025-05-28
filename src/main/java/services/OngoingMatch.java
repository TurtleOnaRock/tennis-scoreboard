package services;

import entities.Player;
import lombok.Getter;
import lombok.Setter;

/**
 * Tennis game point's score can be mapped into
 * 0 - 0
 * 15 - 1
 * 30 - 2
 * 40 - 3
 * Advanced - 4
 */

@Getter
@Setter
public class OngoingMatch {


    private final PlayerScore player1;
    private final PlayerScore player2;
    private Player winner;
    private boolean tieBreak;
    private String conditionMessage;


    public OngoingMatch(Player player1, Player player2) {
        this.player1 = new PlayerScore(player1);
        this.player2 = new PlayerScore(player2);
        this.winner = null;
        this.tieBreak = false;
        this.conditionMessage = "";
    }

    public boolean isCompleted() {
        return this.winner != null;
    }

}
