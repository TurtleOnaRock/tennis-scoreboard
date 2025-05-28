package services;

import dto.OngoingMatchDTO;
import dto.OngoingMatchMapper;
import exceptions.WrongPlayerException;

import java.util.UUID;

public class MatchScoreService {

    public OngoingMatchDTO addPoint(UUID uuid, long pointWinnerId) throws WrongPlayerException {

        OngoingMatch match = OngoingMatchesStorage.getInstance().getMatch(uuid);
        checkPlayerInGame(match, pointWinnerId);
        renewConditionMessage(match);
        if (match.isTieBreak()) {
            MatchScoreCalculatorService.addTieBreakPointTo(match, pointWinnerId);
        } else {
            MatchScoreCalculatorService.addPointTo(match, pointWinnerId);
        }
        if (match.isCompleted()) {
            new FinishedMatchesService().save(match);
        }
        return OngoingMatchMapper.toMatchDTO(match, uuid);
    }

    private void checkPlayerInGame(OngoingMatch match, long playerId) throws WrongPlayerException {
        long firstPlayerId = match.getPlayer1().getPlayer().getId();
        long secondPlayerId = match.getPlayer2().getPlayer().getId();
        if (firstPlayerId != playerId && secondPlayerId != playerId) {
            throw new WrongPlayerException("Player with id=" + playerId + " doesn't a part of the match");
        }
    }

    private void renewConditionMessage(OngoingMatch match) {
        match.setConditionMessage("");
    }
}