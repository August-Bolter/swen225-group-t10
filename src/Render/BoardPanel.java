package Render;

import Maze.Tile;

import javax.swing.*;

public class BoardPanel extends JPanel {
    private Tile[][] board;
    private JPanel[][] boardJPanels;

    public BoardPanel(Tile[][] board) {
        this.board = board;
    }

    public void setup() {

    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
