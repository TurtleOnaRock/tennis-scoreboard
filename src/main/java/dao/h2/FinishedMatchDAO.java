package dao.h2;

import entities.FinishedMatch;

import java.util.List;

public interface FinishedMatchDAO {

    void save(FinishedMatch finishedMatch);

    List<FinishedMatch> getAll(int start, int maxResults);

    List<FinishedMatch> getByName(String name, int start, int maxResults);

    long amountOfRecords();

    long amountOfRecords(String filterName);
}
