package services;

import config.AppConfig;
import dao.h2.FinishedMatchDAO;
import dao.h2.FinishedMatchDAOImpl;
import dto.FinishedMatchDTO;
import dto.FinishedMatchMapper;
import dto.FinishedMatchesDTOWrapper;
import entities.FinishedMatch;
import lombok.NoArgsConstructor;
import servlets.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class FinishedMatchesService {

    public static final int MIN_PAGE_NUMBER = 1;
    public static final String EMPTY_STRING = "";

    public void save(OngoingMatch match) {
        FinishedMatch finishedMatch = toFinishedMatch(match);
        FinishedMatchDAO dao = new FinishedMatchDAOImpl();
        dao.save(finishedMatch);
    }

    public FinishedMatchesDTOWrapper getAll(int pageNumber) {
        return get(EMPTY_STRING, pageNumber);
    }

    public FinishedMatchesDTOWrapper getByName(String playerName, int pageNumber) {
        return get(playerName, pageNumber);
    }

    private FinishedMatchesDTOWrapper get(String playerName, int pageNumber) {
        int pageSize = AppConfig.getTableRowSize();
        List<FinishedMatchDTO> finishedMatchesDTO = new ArrayList<>();
        FinishedMatchDAO dao = new FinishedMatchDAOImpl();
        List<FinishedMatch> matches;
        long amountOfRecords;

        if (playerName.isEmpty()) {
            amountOfRecords = dao.amountOfRecords();
        } else {
            amountOfRecords = dao.amountOfRecords(playerName);
        }
        if (amountOfRecords == 0) {
            return new FinishedMatchesDTOWrapper(finishedMatchesDTO, 1, 1);
        }

        int totalPages = calculateTotalPages(amountOfRecords, pageSize);
        pageNumber = ValidationUtils.chooseValueOrMinMax(pageNumber, MIN_PAGE_NUMBER, totalPages);
        int startPosition = ((pageNumber - 1) * pageSize) + 1;

        if (playerName.isEmpty()) {
            matches = dao.getAll(startPosition, pageSize);
        } else {
            matches = dao.getByName(playerName, startPosition, pageSize);
        }

        FinishedMatchMapper mapper = new FinishedMatchMapper();
        for (FinishedMatch match : matches) {
            finishedMatchesDTO.add(mapper.toFinishedMatchDTO(match));
        }
        return new FinishedMatchesDTOWrapper(finishedMatchesDTO, pageNumber, totalPages);
    }

    private FinishedMatch toFinishedMatch(OngoingMatch match) {
        FinishedMatch finishedMatch = new FinishedMatch();
        finishedMatch.setPlayer1(match.getPlayer1().getPlayer());
        finishedMatch.setPlayer2(match.getPlayer2().getPlayer());
        finishedMatch.setWinner(match.getWinner());
        return finishedMatch;
    }

    private int calculateTotalPages(long amountOfRecords, int pageSize) {
        int totalPages = (int) amountOfRecords / pageSize;
        return (amountOfRecords % pageSize == 0) ? totalPages : totalPages + 1;
    }

}
