import entities.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.MatchScoreCalculatorService;
import services.OngoingMatch;

public class MatchScoreCalculatorServiceTest {

    private OngoingMatch match;

    @BeforeEach
    void prepare() {
        Player firstPlayer = new Player();
        firstPlayer.setName("first");
        firstPlayer.setId(1);
        Player secondPlayer = new Player();
        secondPlayer.setName("second");
        secondPlayer.setId(2);
        this.match = new OngoingMatch(firstPlayer, secondPlayer);
    }

    @Test
    void addPointToFirstPlayer() {
        MatchScoreCalculatorService.addPointTo(match, 1);
        Assertions.assertEquals(1, match.getPlayer1().getPoint());
        Assertions.assertEquals(0, match.getPlayer2().getPoint());
    }

    @Test
    void addPointToSecondPlayer() {
        MatchScoreCalculatorService.addPointTo(match, 2);
        Assertions.assertEquals(0, match.getPlayer1().getPoint());
        Assertions.assertEquals(1, match.getPlayer2().getPoint());
    }

    @Test
    void player1ShouldTakeGamePointScore40x30() {
        setMatchScore(3, 0, 0, 2, 0, 0);
        MatchScoreCalculatorService.addPointTo(match, 1);

        Assertions.assertEquals(1, match.getPlayer1().getGame());
        Assertions.assertEquals(0, match.getPlayer1().getPoint());
        Assertions.assertEquals(0, match.getPlayer2().getPoint());
    }

    @Test
    void player1ShouldTakePointPointScore40x40() {
        setMatchScore(3, 0, 0, 3, 0, 0);
        MatchScoreCalculatorService.addPointTo(match, 1);

        Assertions.assertEquals(4, match.getPlayer1().getPoint());
        Assertions.assertEquals(0, match.getPlayer1().getGame());
    }

    @Test
    void player1ShouldLosePointPointScoreADx40() {
        setMatchScore(4, 0, 0, 3, 0, 0);
        Assertions.assertEquals(4, match.getPlayer1().getPoint());
        MatchScoreCalculatorService.addPointTo(match, 2);

        Assertions.assertEquals(3, match.getPlayer1().getPoint());
    }

    @Test
    void player1ShouldTakeSetGameScore6x5() {
        setMatchScore(3, 6, 0, 1, 5, 0);
        MatchScoreCalculatorService.addPointTo(match, 1);

        Assertions.assertEquals(1, match.getPlayer1().getSet());
        Assertions.assertEquals(0, match.getPlayer2().getSet());
    }

    @Test
    void shouldStartTieBreakGameScore6x5() {
        setMatchScore(0, 6, 0, 3, 5, 0);
        Assertions.assertFalse(match.isTieBreak());
        MatchScoreCalculatorService.addPointTo(match, 2);

        Assertions.assertTrue(match.isTieBreak());
    }

    @Test
    void Player2ShouldWinMatchSetScore1x1() {
        setMatchScore(0, 0, 1, 3, 5, 1);
        MatchScoreCalculatorService.addPointTo(match, 2);

        Assertions.assertEquals(2, match.getPlayer2().getSet());
        Assertions.assertSame(match.getPlayer2().getPlayer(), match.getWinner());
    }

    @Test
    void ShouldStartTieBreakGameScore5x6(){
        setMatchScore(3,5,0,0,6,0);
        Assertions.assertFalse(match.isTieBreak());
        MatchScoreCalculatorService.addPointTo(match, 1);

        Assertions.assertTrue(match.isTieBreak());
    }

    private void setMatchScore(int point1, int game1, int set1, int point2, int game2, int set2) {
        this.match.getPlayer1().setPoint(point1);
        this.match.getPlayer1().setGame(game1);
        this.match.getPlayer1().setSet(set1);

        this.match.getPlayer2().setPoint(point2); //15
        this.match.getPlayer2().setGame(game2);
        this.match.getPlayer2().setSet(set2);
    }
}
