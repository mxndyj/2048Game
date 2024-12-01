package view;

import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends HelperScreen {
    private final JButton startButton;
    private final JButton leaderboardButton;

    public WelcomeScreen() {
        JLabel titleLabel = new JLabel("Welcome to 2048 Tile Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(60, 100, 300, 50);
        add(titleLabel);

        startButton = new JButton("START");
        startButton.setBounds(135, 170, 100, 50);
        startButton.setFocusPainted(false);
        add(startButton);

        leaderboardButton = new JButton("LEADERBOARD");
        leaderboardButton.setBounds(90, 240, 200, 50);
        leaderboardButton.setFocusPainted(false);
        add(leaderboardButton);
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getLeaderboardButton() {
        return leaderboardButton;
    }
}
