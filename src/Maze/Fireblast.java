package Maze;

import Application.Main;

public class Fireblast extends RedEnemy {
    public Fireblast(int row, int col, String direction, Main main) {
        super(row, col, direction);
        this.main = main;
    }

    public boolean moveBlast(){
        Tile newTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);
        if (newTile.isWalkable()) {
            doMove(newTile);
            if (currentPos.getItems().contains(main.getPlayer())) {
                interact();
            }
            return true;
        } else {
            currentPos.removeItem(this);
            return false;
        }
    }
}
