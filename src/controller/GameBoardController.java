package controller;

import model.*;
import view.TextBasedUI;

import java.util.List;

public class GameBoardController {
    private final GameBoard model;
    private final ScoreManager scoreManager;

    public GameBoardController(GameBoard model) {
        this.model = model;
        this.scoreManager = ScoreManager.getScoreManager();
    }

    public void loadGame(String mode) {
        if (mode.equalsIgnoreCase("TEXT")) {
            // text-based UI
            TextBasedUI textUI = new TextBasedUI(this);
            textUI.start();
        } else if (mode.equalsIgnoreCase("GUI")) {
            System.out.println("GUI mode is not done yet.");
        } else {
            System.out.println("Invalid mode selected. Exiting.");
        }
    }

    public void displayGameBoard() {
        System.out.println("Current Score: " + getPlayerScore());
        model.showTiles(); 
    }

    public boolean handleInput(String input) {
        MoveTileStrategy strategy = null;
        switch (input.toLowerCase()) {
            case "w" -> strategy = new MoveTileUp();
            case "s" -> strategy = new MoveTileDown();
            case "a" -> strategy = new MoveTileLeft();
            case "d" -> strategy = new MoveTileRight();
        }

        if (strategy != null) {
            int[] response = model.move(strategy);
            if (response[0] == 1) {
                addScore(response[1]);
                return true;
            }
        }
        return false;
    }

    public void spawnRandomTile() {
        model.placeRandomTile();
    }

    public boolean isGameOver() {
        return model.isGameOver();
    }

    public void displayLeaderboard() {
        System.out.println(getFormattedLeaderboard());
    }

    public void displayFinalScore() {
        System.out.println("Game Over!");
        System.out.println("Your Score: " + getPlayerScore());
        System.out.println("Best Score: " + getBestScore());
        finalizeScore();
    }

    //  helper methods for encapsulation
    private int getPlayerScore() {
        return scoreManager.getPlayerScore();
    }

    private int getBestScore() {
        return scoreManager.getBestScore();
    }

    private void addScore(int points) {
        scoreManager.addScore(points);
    }

    private void finalizeScore() {
        scoreManager.finalizeScore();
    }

    private String getFormattedLeaderboard() {
        List<Integer> leaderboard = scoreManager.getLeaderboard();
        StringBuilder builder = new StringBuilder("Top 10 Scores:\n");
        for (int i = 0; i < leaderboard.size(); i++) {
            builder.append((i + 1)).append(". ").append(leaderboard.get(i)).append("\n");
        }
        return builder.toString();
    }
}

