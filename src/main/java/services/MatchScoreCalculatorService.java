package services;

import dto.TennisMatchDTO;
import dto.TennisMatchMapper;
import exceptions.WrongPlayerException;

import java.util.UUID;

public class MatchScoreCalculatorService {

    public TennisMatchDTO addPoint(UUID uuid, long pointWinnerId) throws WrongPlayerException {
        TennisMatch match = TennisMatchesStorage.getInstance().getMatch(uuid);
        match.addPointTo(pointWinnerId);
        if (match.isCompleted()) {
            new FinishedMatchesService().save(match);
        }
        return TennisMatchMapper.toMatchDTO(match, uuid);
    }

}
