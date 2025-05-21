package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FinishedMatchDTO {
    private final long id;
    private final String nameOfPlayer1;
    private final String nameOfPlayer2;
    private final String nameOfWinner;
}
