package services;

import entities.Player;
import exceptions.WrongPlayerException;
import lombok.Getter;

/**
 * Tennis game point's score can be mapped into
 *           0 - 0
 *          15 - 1
 *          30 - 2
 *          40 - 3
 *    Advanced - 4
 */

@Getter
public class TennisMatch {

    private static final int POINT_40 = 3;
    private static final int POINT_ADVANCED = 4;
    private static final int AMOUNT_SETS_TO_WIN = 2;

    private final PlayerScore player1;
    private final PlayerScore player2;
    private Player winner;
    private boolean tieBreak;
    private String conditionMessage;


    public TennisMatch(Player player1, Player player2){
        this.player1 = new PlayerScore(player1);
        this.player2 = new PlayerScore(player2);
        this.winner = null;
        this.tieBreak = false;
        this.conditionMessage = "";
    }

    public boolean isCompleted(){
        return this.winner != null;
    }

    public void addPointTo(long playerId) throws WrongPlayerException{
        PlayerScore pointWinner;
        PlayerScore secondPlayer;

        this.conditionMessage = "";

        if (this.player1.getPlayer().getId() == playerId){
            pointWinner = this.player1;
            secondPlayer = this.player2;
        } else if (this.player2.getPlayer().getId() == playerId){
            pointWinner = this.player2;
            secondPlayer = this.player1;
        } else {
            throw new WrongPlayerException("Player with ID=" + playerId + " isn't a part of the game");
        }

        if(tieBreak){
            this.conditionMessage = this.conditionMessage + "TIE-BREAK\n";
            addTieBreakPoint(pointWinner, secondPlayer);
        } else {
            addPoint(pointWinner, secondPlayer);
        }
    }

    private void addPoint(PlayerScore pointWinner, PlayerScore secondPlayer ){
        this.conditionMessage = this.conditionMessage + pointWinner.getPlayer().getName() + " takes the point!\n";

        int pointWinnerScore = pointWinner.getPoint();
        int secondPlayerScore = secondPlayer.getPoint();

        if (pointWinnerScore < POINT_40 || (pointWinnerScore == POINT_40 && secondPlayerScore == POINT_40)) {
            pointWinner.upPoint();
        } else if (pointWinnerScore == POINT_ADVANCED || (pointWinnerScore == POINT_40 && secondPlayerScore < POINT_40)) {
            pointWinner.upGame();
            checkGameScore(pointWinner, secondPlayer);
            nextGame();
        } else if (pointWinnerScore == POINT_40 && secondPlayerScore == POINT_ADVANCED) {
            secondPlayer.downPoint();
        }
    }

    private void addTieBreakPoint(PlayerScore pointWinner, PlayerScore secondPlayer){
        this.conditionMessage = this.conditionMessage + pointWinner.getPlayer().getName() + " takes the tie-break point!\n";

        pointWinner.upPoint();
        int pointWinnerScore = pointWinner.getPoint();
        int secondPlayerScore = secondPlayer.getPoint();

        if(pointWinnerScore >=7) {
            if( (pointWinnerScore - secondPlayerScore) >=2 ){
                pointWinner.upSet();
                this.conditionMessage = this.conditionMessage + pointWinner.getPlayer().getName() + " win tie-break!\n";
                checkSetScore(pointWinner);
                this.tieBreak = false;
                nextGame();
                nextSet();
            }
        }
    }

    private void checkGameScore(PlayerScore gameWinner, PlayerScore secondPlayer){
        this.conditionMessage = this.conditionMessage + gameWinner.getPlayer().getName() + " takes the Game!\n";

        int gameWinnerScore = gameWinner.getGame();
        int secondPlayerScore = secondPlayer.getGame();

        if( (gameWinnerScore == 6 && secondPlayerScore <=4) || (gameWinnerScore == 7 && secondPlayerScore == 5) ){
            gameWinner.upSet();
            nextSet();
            checkSetScore(gameWinner);
        } else if(gameWinnerScore ==6 && secondPlayerScore == 6){
            this.tieBreak = true;
            nextGame();
        }
    }

    private void checkSetScore(PlayerScore setWinner){
        this.conditionMessage = this.conditionMessage + setWinner.getPlayer().getName() + " takes the Set!\n";

        if (setWinner.getSet() == AMOUNT_SETS_TO_WIN) {
            this.winner = setWinner.getPlayer();
            this.conditionMessage = this.conditionMessage + "Congratulations to " + setWinner.getPlayer().getName() + " on the victory!";
        }
    }

    private void nextGame(){
        this.player1.setPoint(0);
        this.player2.setPoint(0);
    }

    private void nextSet(){
        this.player1.setGame(0);
        this.player2.setGame(0);
    }
}
