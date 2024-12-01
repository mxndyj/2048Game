package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndingScreen extends HelperScreen {
    private final JButton playAgainButton;
    public EndingScreen(int playerScore, int bestScore, String message) {
        JLabel titleLabel = new JLabel(message);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(5, 80, 375, 50);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel);

        JLabel playerScoreLabel = new JLabel("Player Score : " + playerScore);
        playerScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        playerScoreLabel.setBounds(5, 120, 375, 50);
        playerScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        add(playerScoreLabel);

        JLabel bestScoreLabel = new JLabel("Best Score : " + bestScore);
        bestScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bestScoreLabel.setBounds(5, 160, 375, 50);
        bestScoreLabel.setHorizontalAlignment(JLabel.CENTER);
        add(bestScoreLabel);

        playAgainButton = new JButton("PLAY AGAIN");
        playAgainButton.setBounds(80, 220, 140, 50);
        playAgainButton.setFocusPainted(false);
        add(playAgainButton);

        JButton exitButton = new JButton("EXIT");
        exitButton.setBounds(230, 220, 80, 50);
        exitButton.setFocusPainted(false);
        add(exitButton);


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JButton getPlayAgainButton() {
        return playAgainButton;
    }
}