package Render;

import Maze.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardPanel extends JPanel {
    private Tile[][] board;
    private MainFrame frame;
    private JLabel[][] boardLabels;
    private Map<String, Image> imageMap;

    public BoardPanel(MainFrame frame, Tile[][] board) {
        this.frame = frame;
        this.board = board;

        boardLabels = new JLabel[board.length][board[0].length];

        setup();

        redraw();
    }

    /**
     * Creates the map of images corresponding to image names
     * Currently uses the class name for the key and image name
     * TODO names are bad because they're currently maze.freetile, I want to remove the first bit
     */
    public void setup() {
        imageMap = new HashMap<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                String name = board[row][col].getClass().toString().toLowerCase();
                System.out.println(name);
                Image image = frame.getImage(name);
                imageMap.put(name, image);
            }

        }
    }

    /**
     * Goes through each tile in the array and gets the correct image for that tile
     * Once setup is done
     * Each image will be stored in a map with the class name as the key
     */
    public void redraw() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                String name = board[row][col].getClass().toString().toLowerCase();
                boardLabels[row][col] = new JLabel(new ImageIcon()); // Makes the label, gives it the image for the tile
//                System.out.println(name);
                if (imageMap.containsKey(name)) {
                    Image tileImage = imageMap.get(name);
//                    System.out.println(row + " " + col);
                    if (tileImage != null) { // TODO this can be removed once image names are correct and return images
                        boardLabels[row][col].setIcon(new ImageIcon(tileImage));
                    }
                } else {
                    Image image = frame.getImage(name);
                    imageMap.put(name, image);
                }
            }

        }


    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
