package model;



import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager {
	
	private int playerScore;
    private int bestScore;
    private static ScoreManager scoreManager;
    private static final String FILE_NAME = "score.txt";
    private static final int MAX_ENTRIES = 10;

    private ScoreManager() {
        playerScore = 0;
        bestScore = getBestScoreFromFile();
    }

    public static ScoreManager getScoreManager() {
        if (scoreManager ==null) {
            scoreManager = new ScoreManager();
        }
        return scoreManager;
    }
    
    
    
    /**
     * 
     * @param score - players score from playing game 
     */
    public void addScore(int score) {
        playerScore +=score;
        if (playerScore  > bestScore) {
            bestScore = playerScore;
        }
    }

    public void finalizeScore() {
        saveScoreToLeaderboard(playerScore);
    }

    public void clear() {
        playerScore = 0;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getBestScore() {
        return bestScore;
    }

    private int getBestScoreFromFile() {
        List<Integer> leaderboard = getLeaderboard();
        return leaderboard.isEmpty() ? 0 : leaderboard.get(0);
    }
    

    public List<Integer> getLeaderboard() {
        return new ArrayList<>(loadLeaderboard()); 
    }

    private List<Integer> loadLeaderboard() {
        List<Integer> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading leaderboard file: " + e.getMessage());
        }
        Collections.sort(scores, Collections.reverseOrder());
        return scores;
    }

    /**
     * 
     * @param score - players score from playing game 
     */
    private void saveScoreToLeaderboard(int score) {
        List<Integer> scores = getLeaderboard();
        scores.add(score);
        Collections.sort(scores, Collections.reverseOrder());
        if (scores.size() > MAX_ENTRIES) {
            scores = scores.subList(0, MAX_ENTRIES);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (int s : scores) {
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving leaderboard to file: " + e.getMessage());
        }
    }
}
