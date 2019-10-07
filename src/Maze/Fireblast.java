package Maze;

import Application.Main;

public class Fireblast extends RedEnemy {
    public Fireblast(int row, int col, Main main) {
        super(row, col);
        this.main = main;
    }

    public boolean moveBlast(){
        Tile newTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);
        if (newTile.isWalkable()) {
            doMove(newTile);
            return true;
        } else {
            currentPos.removeItem(this);
            return false;
        }
    }
}
