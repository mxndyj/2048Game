package view;

import javax.swing.*;
import java.awt.*;

public class GameBoardGUI extends JFrame {
    /**
	 * 
	 */
	

	private static final int SIZE = 4;

    private TileLabel[][] gridBoard;
    private JLabel scoreLabel, bestScoreLabel;

    public GameBoardGUI() {
        setFrameSettings();
        setTopPortion();
        setGridPortion();
    }

    public void setFrameSettings() {
        setTitle("Tile Game - 2048");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setPreferredSize(new Dimension(500, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setTopPortion() {
        JLabel titleLabel = new JLabel("2048");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setBounds(10, 20, 150, 50);
        add(titleLabel);

        JLabel subTitleLabel = new JLabel("Join the numbers and get to the 2048 tile!");
        subTitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subTitleLabel.setBounds(10, 70, 300, 15);
        add(subTitleLabel);

        addScoreUI();
    }

    public void addScoreUI() {
        JPanel scorePanel = new JPanel();
        scorePanel.setBounds(250, 10, 110, 40);
        scorePanel.setLayout(new GridLayout(2, 1));
        scorePanel.setBackground(Color.GRAY);

        JLabel scoreTextLabel = new JLabel("SCORE");
        scoreTextLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreTextLabel.setForeground(Color.WHITE);
        scorePanel.add(scoreTextLabel);

        scoreLabel = new JLabel("0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setForeground(Color.WHITE);
        scorePanel.add(scoreLabel);

        add(scorePanel);

        JPanel bestScorePanel = new JPanel();
        bestScorePanel.setBounds(365, 10, 110, 40);
        bestScorePanel.setLayout(new GridLayout(2, 1));
        bestScorePanel.setBackground(Color.GRAY);

        JLabel bestScoreTextLabel = new JLabel("BEST");
        bestScoreTextLabel.setHorizontalAlignment(JLabel.CENTER);
        bestScoreTextLabel.setForeground(Color.WHITE);
        bestScorePanel.add(bestScoreTextLabel);

        bestScoreLabel = new JLabel("0");
        bestScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bestScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        bestScoreLabel.setForeground(Color.WHITE);
        bestScorePanel.add(bestScoreLabel);

        add(bestScorePanel);
    }

    public void setGridPortion() {
        gridBoard = new TileLabel[SIZE][SIZE];

        GridLayout layout = new GridLayout(SIZE, SIZE);
        layout.setHgap(5);
        layout.setVgap(5);

        JPanel gridPanel = new JPanel();
        gridPanel.setBounds(10, 100, 465, 450);
        gridPanel.setLayout(layout);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                TileLabel tile = new TileLabel();
                gridPanel.add(tile);
                gridBoard[i][j] = tile;
            }
        }
        add(gridPanel);
    }

    public TileLabel[][] getViewGridBoard() {
        return gridBoard;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public JLabel getBestScoreLabel() {
        return bestScoreLabel;
    }
}
