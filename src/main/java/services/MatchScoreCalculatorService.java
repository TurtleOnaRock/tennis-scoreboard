package services;

import config.AppConfig;

/**
 * Tennis game point's score can be mapped into
 * 0 - 0
 * 15 - 1
 * 30 - 2
 * 40 - 3
 * Advanced - 4
 */

public class MatchScoreCalculatorService {

    private static final int POINT_40 = 3;
    private static final int POINT_ADVANCED = 4;

    public static void addPointTo(OngoingMatch match, long pointWinnerId) {
        PlayerScore pointWinner = getPointWinner(match, pointWinnerId);
        PlayerScore secondPlayer = getPointLoser(match, pointWinnerId);

        int pointWinnerScore = pointWinner.getPoint();
        int secondPlayerScore = secondPlayer.getPoint();

        if (pointWinnerScore < POINT_40 || (pointWinnerScore == POINT_40 && secondPlayerScore == POINT_40)) {
            pointWinner.upPoint();
        } else if (pointWinnerScore == POINT_ADVANCED || (pointWinnerScore == POINT_40 && secondPlayerScore < POINT_40)) {
            pointWinner.upGame();
            addMsg(match, pointWinner.getPlayer().getName() + " takes the Game!\n");
            checkGameScore(match, pointWinnerId);
            nextGame(match);
        } else if (pointWinnerScore == POINT_40 && secondPlayerScore == POINT_ADVANCED) {
            secondPlayer.downPoint();
        }
    }

    public static void addTieBreakPointTo(OngoingMatch match, long pointWinnerId) {
        addMsg(match, "--- Tie-Break ---\n");
        PlayerScore pointWinner = getPointWinner(match, pointWinnerId);
        PlayerScore secondPlayer = getPointLoser(match, pointWinnerId);

        pointWinner.upPoint();
        int pointWinnerScore = pointWinner.getPoint();
        int secondPlayerScore = secondPlayer.getPoint();

        if (pointWinnerScore >= 7) {
            if ((pointWinnerScore - secondPlayerScore) >= 2) {
                pointWinner.upSet();
                checkSetScore(match, pointWinnerId);
                nextGame(match);
                nextSet(match);

                addMsg(match, pointWinner.getPlayer().getName() + " take the tie-break!!\n");
                addMsg(match, pointWinner.getPlayer().getName() + " tears the Set!!\n");
            }
        }
    }

    private static void checkGameScore(OngoingMatch match, long pointWinnerId) {
        PlayerScore gameWinner = getPointWinner(match, pointWinnerId);
        PlayerScore secondPlayer = getPointLoser(match, pointWinnerId);

        int gameWinnerScore = gameWinner.getGame();
        int secondPlayerScore = secondPlayer.getGame();

        if ((gameWinnerScore == 6 && secondPlayerScore <= 4) || (gameWinnerScore == 7 && secondPlayerScore == 5)) {
            gameWinner.upSet();
            addMsg(match, gameWinner.getPlayer().getName() + " tears the Set!!\n");
            nextSet(match);
            checkSetScore(match, pointWinnerId);
        } else if (gameWinnerScore == 6 && secondPlayerScore == 6) {
            match.setTieBreak(true);
            nextGame(match);
        }
    }

    private static void checkSetScore(OngoingMatch match, long pointWinnerId) {
        PlayerScore pointWinner = getPointWinner(match, pointWinnerId);
        if (pointWinner.getSet() == AppConfig.getAmountSetsToWin()) {
            match.setWinner(pointWinner.getPlayer());
            addMsg(match, pointWinner.getPlayer().getName() + " WINS!!!\n");
        }
    }

    private static void nextGame(OngoingMatch match) {
        match.getPlayer1().setPoint(0);
        match.getPlayer2().setPoint(0);
    }

    private static void nextSet(OngoingMatch match) {
        nextGame(match);
        match.setTieBreak(false);
        match.getPlayer1().setGame(0);
        match.getPlayer2().setGame(0);
    }

    private static PlayerScore getPointWinner(OngoingMatch match, long pointWinnerId) {
        if (match.getPlayer1().getPlayer().getId() == pointWinnerId) {
            return match.getPlayer1();
        }
        return match.getPlayer2();
    }

    private static PlayerScore getPointLoser(OngoingMatch match, long pointWinnerId) {
        if (match.getPlayer1().getPlayer().getId() == pointWinnerId) {
            return match.getPlayer2();
        }
        return match.getPlayer1();
    }

    private static void addMsg(OngoingMatch match, String message) {
        match.setConditionMessage(match.getConditionMessage() + message);
    }
}
