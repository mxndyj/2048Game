package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeSet;

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
        JFrame mainFrame = createMainFrame();
        
        JScrollPane scrollPane = new JScrollPane(this.bookDisplayPanel);
        scrollPane.setBounds(0, 50, 1000, 375);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Game"));
        mainFrame.add(scrollPane);
        
        mainFrame.setVisible(true);
    }

    private JFrame createMainFrame() {
        JFrame mainFrame = new JFrame("2048");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 500);
        mainFrame.setLayout(null);
        mainFrame.addKeyListener(new KeyListener() {
        	
        	//store the pressed character's in a data structure so that pressing + holding
        	//will only register 1 press
        	private TreeSet<Character> pressed = new TreeSet<Character>();

        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_W && !this.pressed.contains('w')) {
        			System.out.println("w was pressed");
        			this.pressed.add('w');
        		}
        		
        		if (e.getKeyCode() == KeyEvent.VK_A && !this.pressed.contains('a')) {
        			System.out.println("a was pressed");
        			this.pressed.add('a');
        		}
        		
        		if (e.getKeyCode() == KeyEvent.VK_S && !this.pressed.contains('s')) {
        			System.out.println("s was pressed");
        			this.pressed.add('s');
        		}
        		
        		if (e.getKeyCode() == KeyEvent.VK_D && !this.pressed.contains('d')) {
        			System.out.println("d was pressed");
        			this.pressed.add('d');
        		}
        	}
        	
        	public void keyReleased(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_W) {
        			System.out.println("w was released");
        		}
        		
        		if (e.getKeyCode() == KeyEvent.VK_A) {
        			System.out.println("a was released");
        		}
        		
        		if (e.getKeyCode() == KeyEvent.VK_S) {
        			System.out.println("s was released");
        		}
        		
        		if (e.getKeyCode() == KeyEvent.VK_D) {
        			System.out.println("d was released");
        		}
        		this.pressed = new TreeSet<Character>();
        	}
        	
        	public void keyTyped(KeyEvent e) {}
        });

        return mainFrame;
    }
}
