package Render;

import Maze.Tile;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private Tile tile;
    private JLabel image;
//    private static final int SIZE = 32;

    public TilePanel(Tile tile) {
//        setPreferredSize(new Dimension());
        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.tile = tile;
    }


    public void paint(Graphics g) {
        super.paint(g);
        removeAll();

        image = new JLabel(new ImageIcon(tile.getImage()));
        add(image);
        setVisible(true);
//        g.drawImage(tile.getImage(), SIZE, SIZE, this);

    }
}
