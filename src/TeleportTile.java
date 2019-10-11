import Maze.Tile;

public class TeleportTile extends Tile {
    private int destinationRow;
    private int destinationCol;

    public TeleportTile(int row, int col, String destination) {
        super(row, col, destination);
        String[] destinationInfo = destination.split(">");
        this.destinationRow = Integer.valueOf(destinationInfo[0]);
        this.destinationCol = Integer.valueOf(destinationInfo[1]);
    }

    public boolean isWalkable() {
        return true;
    }

    public void interact() {
        this.main.getPlayer().move(this.main.getLevelBoard().getBoard()[this.destinationRow][this.destinationCol]);
    }

    public String getDestDescription() {
        return this.destinationRow + "," + this.destinationCol;
    }
}