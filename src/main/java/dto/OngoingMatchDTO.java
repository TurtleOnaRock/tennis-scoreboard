package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class OngoingMatchDTO {
    private final PlayerScoreDTO player1;
    private final PlayerScoreDTO player2;
    private final UUID uuid;
    private final String conditionMessage;
    private final boolean isCompleted;
}
