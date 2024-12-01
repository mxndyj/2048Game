package view;

import javax.swing.*;
import model.Tile;
import java.awt.*;

public class TileLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    private static final Color DEFAULT_BACKGROUND = Color.GRAY;
    private static final Color DEFAULT_FOREGROUND = Color.DARK_GRAY;

    public TileLabel() {
        setOpaque(true);
        setBackground(DEFAULT_BACKGROUND);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setFont(new Font("Arial", Font.BOLD, 40));
        setForeground(DEFAULT_FOREGROUND);
    }

    public void setTile(Tile tile) {
        if (tile.isEmpty()) {
            clear();
        } else {
            setText(String.valueOf(tile.getTileValue())); 
            setBackground(Color.decode(tile.getColor())); 
        }
    }

    public void clear() {
        setText("");
        setBackground(DEFAULT_BACKGROUND);
    }
}
