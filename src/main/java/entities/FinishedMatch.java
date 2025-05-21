package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FinishedMatches")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FinishedMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "Player_1", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "Player_2", nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "Winner", nullable = false)
    private Player winner;
}
