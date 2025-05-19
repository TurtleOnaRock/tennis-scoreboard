package dto;


import services.TennisMatch;

import java.util.UUID;

public class TennisMatchMapper {

    public static TennisMatchDTO toMatchDTO(TennisMatch match, UUID uuid ){
        if(match == null){
            throw new NullPointerException("DtoMapper got match == null");
        }
        if(uuid == null){
            throw new NullPointerException("DtoMapper got uuid == null");
        }

        PlayerScoreDTO player1 = PlayerScoreMapper.toPlayerScoreDTO(match.getPlayer1(), match.isTieBreak());
        PlayerScoreDTO player2 = PlayerScoreMapper.toPlayerScoreDTO(match.getPlayer2(), match.isTieBreak());
        String conditionMessage = match.getConditionMessage();
        boolean isCompleted = match.isCompleted();

        return new TennisMatchDTO(player1, player2, uuid, conditionMessage, isCompleted);
    }
}
