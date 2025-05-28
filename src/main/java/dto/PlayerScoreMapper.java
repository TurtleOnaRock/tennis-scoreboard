package dto;

import services.PlayerScore;

public class PlayerScoreMapper {

    public static PlayerScoreDTO toPlayerScoreDTO(PlayerScore playerScore, boolean tieBreak) {
        if (playerScore == null) {
            throw new NullPointerException("PlayerScoreMapper got playerScore == null");
        }

        long id = playerScore.getPlayer().getId();
        String name = playerScore.getPlayer().getName();
        String point;
        int game = playerScore.getGame();
        int set = playerScore.getSet();

        if (tieBreak) {
            point = String.valueOf(playerScore.getPoint());
        } else {
            point = pointMapper(playerScore.getPoint());
        }

        return new PlayerScoreDTO(id, name, point, game, set);
    }

    private static String pointMapper(int score) {
        if (score == 0) {
            return String.valueOf(0);
        }
        if (score == 1) {
            return String.valueOf(15);
        }
        if (score == 2) {
            return String.valueOf(30);
        }
        if (score == 3) {
            return String.valueOf(40);
        }
        if (score == 4) {
            return "AD";
        }
        return "";
    }
}
