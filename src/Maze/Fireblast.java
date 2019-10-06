package Maze;

public class Fireblast extends RedEnemy {
    public Fireblast(int row, int col) {
        super(row, col);
    }

    public void moveBlast(){
        Tile newTile = main.getLevelBoard().getTileAtPosition(currentPos, direction);
        doMove(newTile);
    }
}
