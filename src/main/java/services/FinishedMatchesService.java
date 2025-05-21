package services;

import dao.h2.FinishedMatchDAO;
import dao.h2.FinishedMatchDAOImpl;
import dto.FinishedMatchDTO;
import dto.FinishedMatchMapper;
import entities.FinishedMatch;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class FinishedMatchesService {

    public void save(TennisMatch match) {
        FinishedMatch finishedMatch = toFinishedMatch(match);
        FinishedMatchDAO dao = new FinishedMatchDAOImpl();
        dao.save(finishedMatch);
    }

    public List<FinishedMatchDTO> getAll() {
        FinishedMatchDAO dao = new FinishedMatchDAOImpl();
        List<FinishedMatch> matches = dao.getAll();

        List<FinishedMatchDTO> finishedMatchesDTO = new ArrayList<>();
        FinishedMatchMapper mapper = new FinishedMatchMapper();

        for (FinishedMatch match : matches) {
            finishedMatchesDTO.add(mapper.toFinishedMatchDTO(match));
        }
        return finishedMatchesDTO;
    }

    public List<FinishedMatchDTO> getByName(String playerName){
        FinishedMatchDAO dao = new FinishedMatchDAOImpl();
        List<FinishedMatch> matches = dao.getByName(playerName);

        List<FinishedMatchDTO> finishedMatchesDTO = new ArrayList<>();
        FinishedMatchMapper mapper = new FinishedMatchMapper();
        for (FinishedMatch match : matches) {
            finishedMatchesDTO.add(mapper.toFinishedMatchDTO(match));
        }
        return finishedMatchesDTO;
    }

    private FinishedMatch toFinishedMatch(TennisMatch match) {
        FinishedMatch finishedMatch = new FinishedMatch();
        finishedMatch.setPlayer1(match.getPlayer1().getPlayer());
        finishedMatch.setPlayer2(match.getPlayer2().getPlayer());
        finishedMatch.setWinner(match.getWinner());
        return finishedMatch;
    }

}
