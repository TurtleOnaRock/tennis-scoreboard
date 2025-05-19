package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PlayerScoreDTO {
    private final int id;
    private final String name;
    private final String point;
    private final int game;
    private final int set;
}
