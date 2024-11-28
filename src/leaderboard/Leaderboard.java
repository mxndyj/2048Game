package leaderboard;

import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;

public class Leaderboard {
	//TreeSet keeps its elements in sorted order 
	private TreeSet<Player> leaderboard;
	
	public Leaderboard() {
		leaderboard = new TreeSet<Player>();
	}
	
	public void insertPlayerInLeaderboard(Player newPlayer) {
		//Automatically inserts into TreeSet if it is empty or contains less than 10 elements
		if (leaderboard.isEmpty() || leaderboard.size() < 10) {
			leaderboard.add(new Player(newPlayer));
		} else {
			//If newPlayer has a greater score than the 10th place player, then switch them out
			if (newPlayer.compareTo(leaderboard.first()) > 0) {
				leaderboard.pollFirst();
				leaderboard.add(new Player(newPlayer));
			}
		}
	}
	
	//Returns the leaderboard as a sorted ArrayList of their string representations
	public List<String> getLeaderboard() {
		ArrayList<String> playerScores = new ArrayList<>();
		
		for (Player p: leaderboard) {
			playerScores.add(p.toString());
		}
		
		return playerScores;
	}
}
