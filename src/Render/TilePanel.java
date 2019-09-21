package Render;

import Maze.Tile;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private Tile tile;
    private static final int SIZE = 32;

    public TilePanel(Tile tile) {
        this.tile = tile;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(tile.getImage(), SIZE, SIZE, null);
    }
}
