package controller;

import model.*;
import view.*;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Scanner;


/**
 * The GameBoardController manages the flow of the 2048 game, including the interaction between
 * the model (game logic) and the view (GUI or text-based interface).
 */

public class GameBoardController {
	private static final int SIZE = 4;

    private GameBoard model;
    private GameBoardGUI view;
    private WelcomeScreen welcomeScreen;
    private ScoreManager scoreManager;
    private String mode; // "GUI" or "TEXT"

    public GameBoardController(GameBoard model, GameBoardGUI view, WelcomeScreen welcomeScreen, String mode) {
        this.model = model;
        this.view = view;
        this.welcomeScreen = welcomeScreen;
        this.scoreManager = ScoreManager.getScoreManager();
        this.mode = mode;

        if (mode.equals("GUI")) {
            welcomeScreen.getLeaderboardButton().addActionListener(e -> displayLeaderboardGUI());
        }
    }

    public void loadGame() {
        if (mode.equals("GUI")) {
            welcomeScreen.setVisible(true);
            SoundEffect.loopMusic("src/sounds/2048 - Twilight.wav");
            view.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent e) {
                    handleKeyPress(e);
                }

                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {}
            });
            welcomeScreen.getStartButton().addActionListener(e -> {
                welcomeScreen.dispose();
                view.setVisible(true);
            });
            updateGameUI();
        } else if (mode.equals("TEXT")) {
            startTextBasedGame();
        }
    }

    
    /**
     * Starts the game in text-based mode, allowing user input via the console.
     */
    private void startTextBasedGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to 2048!");
        SoundEffect.loopMusic("src/sounds/2048 - Twilight.wav");
        displayGameBoard();

        while (!isGameOver()) {
            System.out.println("Enter a move (w=UP, s=DOWN, a=LEFT, d=RIGHT) or type 'leaderboard' to view scores, or 'end' to quit:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("end")) {
                displayFinalScore();
                return;
            } else if (input.equalsIgnoreCase("leaderboard")) {
                displayLeaderboard();
            } else if (handleInput(input)) {
                spawnRandomTile();
                displayGameBoard();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
        displayFinalScore();
        scanner.close();
    }

    public void updateGameUI() {
        if (mode.equals("GUI")) {
            Tile[][] modelGridBoard = model.getModelGridBoard();
            TileLabel[][] viewGridBoard = view.getViewGridBoard();

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    Tile tile = modelGridBoard[i][j];
                    viewGridBoard[i][j].setTile(tile);
                }
            }

            // Update score labels
            view.getScoreLabel().setText(String.valueOf(scoreManager.getPlayerScore()));
            view.getBestScoreLabel().setText(String.valueOf(scoreManager.getBestScore()));
        }
    }

    public void displayGameBoard() {
        Tile[][] modelGridBoard = model.getModelGridBoard();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print((modelGridBoard[i][j].getTileValue() == 0 ? "." : modelGridBoard[i][j].getTileValue()) + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Handles user input in text based mode.
     *
     * @param input the user input (e.g., "w", "s", "a", "d").
     * @return true if the move was successful, false else.
     */
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
                scoreManager.addScore(response[1]);
                SoundEffect.playSound("src/sounds/move.wav");
                System.out.println("Current Score: " + scoreManager.getPlayerScore());
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * Handles key press events in GUI mode.
     *
     * @param e the KeyEvent triggered by a key press.
     */

    private void handleKeyPress(KeyEvent e) {
        MoveTileStrategy strategy = null;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> strategy = new MoveTileUp();
            case KeyEvent.VK_DOWN -> strategy = new MoveTileDown();
            case KeyEvent.VK_LEFT -> strategy = new MoveTileLeft();
            case KeyEvent.VK_RIGHT -> strategy = new MoveTileRight();
        }

        if (strategy != null) {
            int[] response = model.move(strategy);
            if (response[0] == 1) {
                if (response[1] == 2048) {
                	SoundEffect.playSound("src/sounds/win.wav");
                    endGame(scoreManager.getPlayerScore(), scoreManager.getBestScore(), "Congratulations!!! You Won!!!");
                }
                SoundEffect.playSound("src/sounds/move.wav");
                scoreManager.addScore(response[1]);
                model.placeRandomTile();
                model.showTiles();
                updateGameUI();

                if (model.isGameOver()) {
                    JOptionPane.showMessageDialog(null, "No Available Moves!");
                    endGame(scoreManager.getPlayerScore(), scoreManager.getBestScore(), "Game Over ! You Lost !");
                }
            }
        }
    }

    public boolean isGameOver() {
        return model.isGameOver();
    }

    public void spawnRandomTile() {
        model.placeRandomTile();
    }

    public void displayLeaderboard() {
        List<Integer> leaderboard = scoreManager.getLeaderboard();
        System.out.println("Top 10 Scores:");
        for (int i = 0; i < leaderboard.size(); i++) {
            System.out.println((i + 1) + ". " + leaderboard.get(i));
        }
    }

    private void displayLeaderboardGUI() {
        List<Integer> leaderboard = scoreManager.getLeaderboard();
        StringBuilder message = new StringBuilder("Top 10 Scores:\n");
        for (int i = 0; i < leaderboard.size(); i++) {
            message.append(i + 1).append(". ").append(leaderboard.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayFinalScore() {
        System.out.println("Game Over!");
        System.out.println("Your Score: " + scoreManager.getPlayerScore());
        System.out.println("Best Score: " + scoreManager.getBestScore());
        scoreManager.finalizeScore();
    }

    
    /**
     * Ends the game and displays an ending screen in GUI mode.
     *
     * @param playerScore - the player's final score.
     * @param bestScore  -  the best score achieved.
     * @param message   -   the message to display on the ending screen.
     */
    public void endGame(int playerScore, int bestScore, String message) {
        if (mode.equals("GUI")) {
            scoreManager.finalizeScore();
            EndingScreen screen = new EndingScreen(playerScore, bestScore, message);
            screen.getPlayAgainButton().addActionListener(e -> {
                screen.dispose();
                model = new GameBoard();
                scoreManager.clear();
                updateGameUI();
                view.setVisible(true);
            });
            view.dispose();
            screen.setVisible(true);
        }
    }
	
}
