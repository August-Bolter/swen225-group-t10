package Render;

import Maze.Tile;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    public Tile[][] board;
    public TilePanel[][] boardLabels;

    public BoardPanel(Tile[][] board) {
        setLayout(new GridLayout(board.length, board[0].length));
        this.board = board;
        boardLabels = new TilePanel[board.length][board[0].length];

//        setup();

        redraw();
    }

//    /**
//     * Creates the map of images corresponding to image names
//     * Currently uses the class name for the key and image name
//     * TODO names are bad because they're currently maze.freetile, I want to remove the first bit
//     */
//    public void setup() {
//        imageMap = new HashMap<>();
//
//        for (int row = 0; row < board.length; row++) {
//            for (int col = 0; col < board[0].length; col++) {
//                String name = board[row][col].getClass().toString().toLowerCase();
//                System.out.println(name);
//                Image image = frame.getImage(name);
//                imageMap.put(name, image);
//            }
//
//        }
//    }

    /**
     * FIXME
     * Goes through each tile in the array and gets the correct image for that tile
     * Once setup is done
     * Each image will be stored in a map with the class name as the key
     */
    public void redraw() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                boardLabels[row][col] = new TilePanel(board[row][col]); // Makes the label, gives it the image for the tile
//                boardLabels[row][col].setBackground(Color.BLUE);
//                Graphics g = boardLabels[row][col].getGraphics();
//                if (g == null) { throw new RuntimeException("you know why..."); }
//                boardLabels[row][col].paint(g);
                add(boardLabels[row][col]);
            }

        }

        repaint();
    }
}
