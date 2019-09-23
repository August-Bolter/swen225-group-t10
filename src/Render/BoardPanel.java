package Render;

import Maze.Player;
import Maze.Tile;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    public Tile[][] board;
    public TilePanel[][] boardLabels;
    public static final int DISPLAY_SIZE = 9;
    public Player player;


    public BoardPanel(Tile[][] board, Player player) {
        setLayout(new GridLayout(DISPLAY_SIZE, DISPLAY_SIZE));
        this.board = board;
        boardLabels = new TilePanel[board.length][board[0].length];
        int playerRow = player.getCurrentPos().getRow();
        int playerCol = player.getCurrentPos().getCol();

//        setup();
        // FIXME need to add minimums and maximums!!
        for (int row = playerRow - 4; row < playerRow - 4 + DISPLAY_SIZE; row++) {
            for (int col = playerCol - 4; col < playerCol - 4 + DISPLAY_SIZE; col++) {
                boardLabels[row][col] = new TilePanel(board[row][col]); // Makes the label, gives it the image for the tile
                // TODO tell the items when they're visible and when they're not
//                boardLabels[row][col].setBackground(Color.BLUE);
//                Graphics g = boardLabels[row][col].getGraphics();
//                if (g == null) { throw new RuntimeException("you know why..."); }
//                boardLabels[row][col].paint(g);
                add(boardLabels[row][col]);
            }

        }

        repaint();

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

    @Override
    public void paint(Graphics g) {
        for (int row = 0; row < 32; row++)
            for (int col = 0; col < 32; col++)
                boardLabels[row][col].paint(g);
    }
}
