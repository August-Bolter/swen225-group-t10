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
        this.board = board;
        this.player = player;
        boardLabels = new TilePanel[board.length][board[0].length];

        setLayout(new GridLayout(DISPLAY_SIZE, DISPLAY_SIZE));

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                boardLabels[row][col] = new TilePanel(board[row][col]); // Makes the label, gives it the image for the tile
            }

        }

        redraw();
    }

    /**
     * Goes through each tile in the array and gets the correct image for that tile
     * Once setup is done
     * Each image will be stored in a map with the class name as the key
     */
    public void redraw() {
        System.out.println("REDRAW IS CALLED");
        removeAll();
        revalidate();
//        if (player == null) System.out.println("THE PLAYER IS NULL !!!!!!!!!!!!!!!!!!\n\n\n\n\n\n\n\n\n\n\n\n");
        int playerRow = player.getCurrentPos().getRow();
        int playerCol = player.getCurrentPos().getCol();

        for (int row = playerRow - 4; row < playerRow - 4 + DISPLAY_SIZE; row++) {
            for (int col = playerCol - 4; col < playerCol - 4 + DISPLAY_SIZE; col++) {
                boardLabels[row][col].redraw();
                add(boardLabels[row][col]);
            }

        }
    }

    /**
     *
     */
    public void updateBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                boardLabels[row][col] = new TilePanel(board[row][col]); // Makes the label, gives it the image for the tile
            }
        }
    }

    public void setPlayer(Player p) {
        player = p;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }
}
