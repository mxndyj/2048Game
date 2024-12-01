package view;

import javax.swing.*;
import java.awt.*;

public class HelperScreen extends JFrame {
	
    public HelperScreen() {
        setTitle("Tile Game - 2048");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setPreferredSize(new Dimension(400, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
