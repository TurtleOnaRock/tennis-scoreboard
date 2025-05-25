package services;

import dao.h2.FinishedMatchDAO;
import dao.h2.FinishedMatchDAOImpl;
import dto.FinishedMatchDTO;
import dto.FinishedMatchMapper;
import dto.FinishedMatchesDTOWrapper;
import entities.FinishedMatch;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class FinishedMatchesService {

    public static final int MIN_PAGE_NUMBER = 1;
    public static final int PAGE_SIZE = 5;

    public void save(TennisMatch match) {
        FinishedMatch finishedMatch = toFinishedMatch(match);
        FinishedMatchDAO dao = new FinishedMatchDAOImpl();
        dao.save(finishedMatch);
    }

    public FinishedMatchesDTOWrapper getAll(int pageNumber) {
        if (pageNumber < MIN_PAGE_NUMBER) {
            pageNumber = MIN_PAGE_NUMBER;
        }

        int totalPages = getTotalPages();
        if(pageNumber > totalPages){
            pageNumber = totalPages;
        }

        int start = ((pageNumber - 1) * PAGE_SIZE) + 1;
        FinishedMatchDAO dao = new FinishedMatchDAOImpl();
        List<FinishedMatch> matches = dao.getAll(start, PAGE_SIZE);

        List<FinishedMatchDTO> finishedMatchesDTO = new ArrayList<>();
        FinishedMatchMapper mapper = new FinishedMatchMapper();
        for (FinishedMatch match : matches) {
            finishedMatchesDTO.add(mapper.toFinishedMatchDTO(match));
        }
        return new FinishedMatchesDTOWrapper(finishedMatchesDTO, pageNumber, totalPages);
    }

    public FinishedMatchesDTOWrapper getByName(String playerName, int pageNumber) {
        if (pageNumber < MIN_PAGE_NUMBER) {
            pageNumber = MIN_PAGE_NUMBER;
        }

        int totalPages = getTotalPages(playerName);
        if(pageNumber > totalPages){
            pageNumber = totalPages;
        }

        int start = ((pageNumber -1) * PAGE_SIZE) +1;
        FinishedMatchDAO dao = new FinishedMatchDAOImpl();
        List<FinishedMatch> matches = dao.getByName(playerName, start, PAGE_SIZE);

        List<FinishedMatchDTO> finishedMatchesDTO = new ArrayList<>();
        FinishedMatchMapper mapper = new FinishedMatchMapper();
        for (FinishedMatch match : matches) {
            finishedMatchesDTO.add(mapper.toFinishedMatchDTO(match));
        }
        return new FinishedMatchesDTOWrapper(finishedMatchesDTO, pageNumber, totalPages);
    }

    private FinishedMatch toFinishedMatch(TennisMatch match) {
        FinishedMatch finishedMatch = new FinishedMatch();
        finishedMatch.setPlayer1(match.getPlayer1().getPlayer());
        finishedMatch.setPlayer2(match.getPlayer2().getPlayer());
        finishedMatch.setWinner(match.getWinner());
        return finishedMatch;
    }

    private int getTotalPages() {
        FinishedMatchDAO dao = new FinishedMatchDAOImpl();
        long amountOfRecords = dao.amountOfRecords();
        int totalPages = (int) amountOfRecords / PAGE_SIZE;
        return (amountOfRecords % PAGE_SIZE == 0) ? totalPages : totalPages + 1;
    }

    private int getTotalPages(String name) {
        FinishedMatchDAO dao = new FinishedMatchDAOImpl();
        long amountOfRecords = dao.amountOfRecords(name);
        int totalPages = (int) amountOfRecords / PAGE_SIZE;
        return (amountOfRecords % PAGE_SIZE == 0) ? totalPages : totalPages + 1;
    }
}
