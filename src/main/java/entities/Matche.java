package entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Matches")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Matche {

    @Id
    @Column(name="id")
    private int id;

    @ManyToOne @JoinColumn(name="Player_1", nullable = false)
    private Player Player1;

    @ManyToOne @JoinColumn(name="Player_2", nullable = false)
    private Player Player2;

    @ManyToOne @JoinColumn(name="Winner", nullable = false)
    private Player Winner;
}
