package dto;

import entities.FinishedMatch;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FinishedMatchMapper {

    public FinishedMatchDTO toFinishedMatchDTO(FinishedMatch finishedMatch) {
        if (finishedMatch == null) {
            throw new NullPointerException("Problem in MatchResultDtoMapper");
        }

        long id = finishedMatch.getId();
        String nameOfPlayer1 = finishedMatch.getPlayer1().getName();
        String nameOfPlayer2 = finishedMatch.getPlayer2().getName();
        String nameOfWinner = finishedMatch.getWinner().getName();
        return new FinishedMatchDTO(id, nameOfPlayer1, nameOfPlayer2, nameOfWinner);
    }
}
