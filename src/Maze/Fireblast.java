package Maze;

import Application.Main;

public class Fireblast extends RedEnemy {
    public Fireblast(int row, int col, String direction, Main main) {
        super(row, col, direction);
        this.main = main;
    }

    public void moveBlast(){
        Tile newTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);
        if (newTile.isWalkable()) {
            doMove(newTile);
            if (currentPos.getItems().contains(main.getPlayer())) {
                interact();
            }
        } else {
            currentPos.removeItem(this);
        }
    }

    @Override
    public void onTick() {
        moveBlast();
    }

    public void interact() {
        main.getFrame().displayInfo("Watch out for Fire Blasts!");
        main.restartLevel();
    }
}
