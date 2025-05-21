package dao.h2;

import entities.FinishedMatch;

import java.util.List;

public interface FinishedMatchDAO {

    void save(FinishedMatch finishedMatch);

    List<FinishedMatch> getAll();

    List<FinishedMatch> getByName(String name);
}
