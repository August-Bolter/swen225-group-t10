package Render;

import Maze.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class BoardPanel extends JPanel {
    private Tile[][] board;
    private MainFrame frame;
    private JPanel[][] boardJPanels;
    private Map<String, Image> imageMap;

    public BoardPanel(MainFrame frame, Tile[][] board) {
        this.frame = frame;
        this.board = board;

        redraw();
    }

    /**
     * Goes through each tile in the array and gets the correct image for that tile
     * Once setup is done // TODO do I want to change it to load the map on setup as a seperate method
     * Each image will be stored in a map with the class name as the key
     */
    public void redraw() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                String name = board[row][col].getClass().toString().toLowerCase();
                System.out.println(name);
                if (imageMap.containsKey(name)) { continue; }
                Image image = frame.getImage(name);
                imageMap.put(name, image);
            }

        }


    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
