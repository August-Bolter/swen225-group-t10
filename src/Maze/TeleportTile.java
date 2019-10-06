package Maze;

public class TeleportTile extends Tile {
    private int destinationRow, destinationCol;

    /**
     * Constructor
     * @param row the row
     * @param col the col
     * @param destination a String describing the destination, in the form row,col
     */
    public TeleportTile(int row, int col, String destination) {
        super(row, col, destination);
        String[] destinationInfo = destination.split(",");
        this.destinationRow = Integer.valueOf(destinationInfo[0]);
        this.destinationCol = Integer.valueOf(destinationInfo[1]);
    }

    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public void interact() {
        // Move the player to the destination
        main.getPlayer().move(main.getLevelBoard().getBoard()[destinationRow][destinationCol]);
    }

    /**
     * Return destination as a string
     */
    public String getDestDescription() {
        return destinationRow + "," + destinationCol;
    }
}
