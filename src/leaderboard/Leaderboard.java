package leaderboard;

import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;

public class Leaderboard {
	private TreeSet<Player> leaderboard;
	
	public Leaderboard() {
		leaderboard = new TreeSet<Player>;
	}
	
	public void insertPlayerInLeaderboard(Player newPlayer) {
		if (leaderboard.isEmpty() || leaderboard.size() < 10) {
			leaderboard.add(new Player(newPlayer));
		} else {
			if (newPlayer.compareTo(leaderboard.first()) > 0) {
				leaderboard.pollFirst();
				leaderboard.add(new Player(newPlayer));
			}
		}
	}
	
	public List<String> getLeaderboard() {
		ArrayList<String> playerScores = new ArrayList<>();
		
		for (Player p: leaderboard) {
			playerScores.add(p.toString());
		}
		
		return playerScores;
	}
}
