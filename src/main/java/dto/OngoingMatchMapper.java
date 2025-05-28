package dto;


import services.OngoingMatch;

import java.util.UUID;

public class OngoingMatchMapper {

    public static OngoingMatchDTO toMatchDTO(OngoingMatch match, UUID uuid) {
        if (match == null) {
            throw new NullPointerException("DtoMapper got match == null");
        }
        if (uuid == null) {
            throw new NullPointerException("DtoMapper got uuid == null");
        }

        PlayerScoreDTO player1 = PlayerScoreMapper.toPlayerScoreDTO(match.getPlayer1(), match.isTieBreak());
        PlayerScoreDTO player2 = PlayerScoreMapper.toPlayerScoreDTO(match.getPlayer2(), match.isTieBreak());
        String conditionMessage = match.getConditionMessage();
        boolean isCompleted = match.isCompleted();

        return new OngoingMatchDTO(player1, player2, uuid, conditionMessage, isCompleted);
    }
}
