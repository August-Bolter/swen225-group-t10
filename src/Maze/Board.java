package Maze;


import java.awt.*;

public class Board {
    private static final int SIZE = 32;
    private Tile[][] board = new Tile[32][32];

    public void setup(){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Tile(i,j)
                {

                };

            }
        }
    }
    public Tile[][] getBoard() {
        return board;
    }

    public Tile getTileAtPosition(Tile currentPos, String direction) {
        return null; //Replace this with actual code
    }
}
