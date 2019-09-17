package Maze;


import Persistence.LoadJSON;

import java.awt.*;

public class LevelBoard {
    private static final int SIZE = 32;

    public final String title;
    public final int totalChips, timeLimit;

    private Tile[][] board;

    public LevelBoard(String title, int totalChips, int timeLimit, Tile[][] board) {
        this.title = title;
        this.totalChips = totalChips;
        this.timeLimit = timeLimit;
        this.board = board;

        // TESTING
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
                System.out.println("[" + i + "," + j + "], " + board[i][j].getClass().toString());
        }
    }


    public Tile[][] getBoard() {
        return board;
    }

    public Tile getTileAtPosition(Tile currentPos, String direction) {
        return null; //Replace this with actual code
    }
}
