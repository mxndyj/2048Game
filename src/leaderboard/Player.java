package leaderboard;

public class Player {
	private String name;
	private int score;
	
	public Player() {
		this.score = 0;
	}
	
	public Player(String name) {
		this.name = name;
		this.score = 0;
	}
	
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	//Copy constructor for Player Class
	public Player(Player otherPlayer) {
		this.name = otherPlayer.name;
		this.score = otherPlayer.score;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getScore() {
		return this.score;
	}
	
	//Compare the scores first and then if those are equal, then compare their names
	public int compareTo(Player otherPlayer) {
		return this.score == otherPlayer.score ? this.name.compareTo(otherPlayer.name) : ((Integer) this.score).compareTo(otherPlayer.score);
	}
	
	@Override
	public String toString() {
		return this.getName() + ": " + this.getScore();
	}
}
