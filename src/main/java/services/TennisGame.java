package services;

import entities.Player;
import exceptions.WrongPlayerException;

/**
 * Tennis game point's score can be mapped into
 *           0 - 0
 *          15 - 1
 *          30 - 2
 *          40 - 3
 *    Advanced - 4
 */

public class TennisGame {

    private static final int POINT_40 = 3;
    private static final int POINT_ADVANCED = 4;
    private static final int AMOUNT_SETS_TO_WIN = 2;

    private final PlayerScore player1;
    private final PlayerScore player2;
    private Player winner;
    private boolean tieBreak;


    public TennisGame(Player player1, Player player2){
        this.player1 = new PlayerScore(player1);
        this.player2 = new PlayerScore(player2);
        this.winner = null;
        this.tieBreak = false;
    }

    public void addPointTo(int playerId) throws WrongPlayerException{
        PlayerScore pointWinner;
        PlayerScore secondPlayer;

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
            addTieBreakPoint(pointWinner, secondPlayer);
        } else {
            addPoint(pointWinner, secondPlayer);
        }
    }


    private void addPoint(PlayerScore pointWinner, PlayerScore secondPlayer ){
        int pointWinnerScore = pointWinner.getPoint();
        int secondPlayerScore = secondPlayer.getPoint();

        if (pointWinnerScore < POINT_40 || (pointWinnerScore == 40 && secondPlayerScore == POINT_40)) {
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
        pointWinner.upPoint();
        int pointWinnerScore = pointWinner.getPoint();
        int secondPlayerScore = secondPlayer.getPoint();

        if(pointWinnerScore >=7) {
            if( (pointWinnerScore - secondPlayerScore) >=2 ){
                pointWinner.upSet();
                checkSetScore(pointWinner);
                this.tieBreak = false;
                nextGame();
            }
        }
    }

    private void checkGameScore(PlayerScore gameWinner, PlayerScore secondPlayer){
        int gameWinnerScore = gameWinner.getGame();
        int secondPlayerScore = secondPlayer.getGame();

        if( (gameWinnerScore == 6 && secondPlayerScore <=4) || (gameWinnerScore == 7 && secondPlayerScore == 5) ){
            gameWinner.upSet();
            checkSetScore(gameWinner);
        } else if(gameWinnerScore ==6 && secondPlayerScore == 6){
            this.tieBreak = true;
            nextGame();
        }
    }

    private void checkSetScore(PlayerScore setWinner){
        if( setWinner.getSet() == AMOUNT_SETS_TO_WIN) {
            this.winner = setWinner.getPlayer();
        }
    }

    public boolean DoesGameEnd(){
        return this.winner != null;
    }

    public Player getWinner(){
        return this.winner;
    }

    private void nextGame(){
        this.player1.setPoint(0);
        this.player2.setPoint(0);
    }

}
