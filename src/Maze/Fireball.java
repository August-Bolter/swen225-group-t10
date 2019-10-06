package Maze;

public class Fireball extends RedEnemy {
    public Fireball(int row, int col) {
        super(row, col);
    }

    public void moveBlast(){
        Tile newTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);
        doMove(newTile);
    }
}
