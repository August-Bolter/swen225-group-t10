package Maze;


import Persistence.LoadJSON;

import java.awt.*;

public class LevelBoard {
    private static final int SIZE = 32;

    //these used to public and I changed them to private and used getter and setter methods  - is this okay?
    private final String title;

    public enum Direction {LEFT, RIGHT, UP, DOWN}
    private final int totalChips, timeLimit;

    private Tile[][] board;

    public LevelBoard(String title, int totalChips, int timeLimit, Tile[][] board) {
        this.title = title;
        this.totalChips = totalChips;
        this.timeLimit = timeLimit;
        this.board = board;

        // TESTING
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++)
                System.out.println("[" + i + "," + j + "], " + board[i][j].getClass().toString() + " with " + board[i][j].getItems().size() + " items");
        }

        System.out.println(board[3][6].getItems().get(0).getClass() + ", " + board[3][6].getItems().get(0).getRow());
    }


    public Tile[][] getBoard() {
        return board;
    }

    /**
     * Gets the tile at a given direction from a given position.
     * @param currentPos the given position
     * @param direction the direction from the given position
     * @return the tile at the direction
     */
    public Tile getTileAtPosition(Tile currentPos, Direction direction) {
        switch (direction) {
            case LEFT:
                if (currentPos.getCol() > 0)
                    return board[currentPos.getRow()][currentPos.getCol()-1];
                else
                    return null;
            case RIGHT:
                if (currentPos.getCol() < board.length)
                    return board[currentPos.getRow()][currentPos.getCol()+1];
                else
                    return null;
            case UP:
                if (currentPos.getRow() > 0)
                    return board[currentPos.getRow()-1][currentPos.getCol()];
                else
                    return null;
            case DOWN:
                if (currentPos.getCol() < board[0].length)
                    return board[currentPos.getRow()+1][currentPos.getCol()];
                else
                    return null;
            default:
                return null;
        }
    }


    //getter and setter methods

    /**
     * Method that gets the total number of chips
     * @return number of chips
     */
    public int getTotalChips() {
        return totalChips;
    }

    /**
     * Method that gets the timelimit
     * @return the timeLimit
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Method that gets the title
     * @return title
     */
    public String getTitle() {
        return title;
    }

}
