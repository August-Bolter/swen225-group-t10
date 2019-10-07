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
    public final static int MAX = 32;
    public final static int MIN = 0;


    public BoardPanel(Tile[][] board, Player player) {
        this.board = board;
        this.player = player;
        boardLabels = new TilePanel[board.length][board[0].length];

        setLayout(new GridLayout(DISPLAY_SIZE, DISPLAY_SIZE));
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                boardLabels[row][col] = new TilePanel(board[row][col]); // Makes the label, gives it the image for the tile
            }

        }
//        updateBoard();
        redraw();
    }



    /**
     * Goes through each tile in the array and gets the correct image for that tile
     */
    public void redraw() {
        invalidate();
        removeAll();
//        if (player == null) System.out.println("THE PLAYER IS NULL !!!!!!!!!!!!!!!!!!\n\n\n\n\n\n\n\n\n\n\n\n");
        int playerRow = player.getCurrentPos().getRow();
        int playerCol = player.getCurrentPos().getCol();

        int minRow = Math.max(playerRow - 4, MIN);
        int minCol = Math.max(playerCol - 4, MIN);

        int maxRow = Math.min(playerRow - 4 + DISPLAY_SIZE, MAX);
        int maxCol = Math.min(playerCol - 4 + DISPLAY_SIZE, MAX);

        for (int row = minRow; row < maxRow; row++) {
            for (int col = minCol; col < maxCol; col++) {
                boardLabels[row][col].redraw();
                add(boardLabels[row][col]);
            }

        }
        revalidate();
    }

    /**
     *
     */
    public void updateBoard(Tile tile) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
//                boardLabels[row][col] = new TilePanel(board[row][col]); // Makes the label, gives it the image for the tile
                if (boardLabels[row][col].getTile() == tile) {
                    boardLabels[row][col] = new TilePanel(board[row][col]); // Makes the label, gives it the image for the tile
                }
            }
        }

        redraw();
    }
}
