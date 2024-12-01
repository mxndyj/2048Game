import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import leaderboard.Leaderboard;
import leaderboard.Player;

class LeaderboardTest {

	@BeforeEach
	void setUp() {
		Leaderboard leaderboard = new Leaderboard();
	}
	
	@Test
	void emptyLeaderboard() {
		assertEquals(leaderboard.getLeaderboard().size(), 0);
	}
	
	@Test
	void addNullToLeaderboard() {
		leaderboard.addPlayerToLeaderboard(null);
		assertEquals(leaderboard.getLeaderboard().size(), 0);
	}

}
