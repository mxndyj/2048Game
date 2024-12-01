package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.GameBoardController;
import model.GameBoard;

public class GameBoardGUI {
	private GameBoardController controller;
    private JPanel bookDisplayPanel;
    private JTextField bookTitle;
    private JTextField bookAuthor;
    private JTextField bookRating;
    
    public GameBoardGUI(GameBoardController controller) {
    	this.controller = controller;
    	setUp();
    }
    
    private void setUp() {
        JFrame mainFrame = new JFrame("2048");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 500);
        mainFrame.setLayout(null); 
        
        JScrollPane scrollPane = new JScrollPane(this.bookDisplayPanel);
        scrollPane.setBounds(0, 50, 1000, 375);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Game"));
        mainFrame.add(scrollPane);
        
        mainFrame.setVisible(true);
    }
}
