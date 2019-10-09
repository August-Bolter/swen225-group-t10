package Maze;

/**
 * FireTile this is a type of tile which the players can't interact with (walk on) unless they have obtained the boots
 * If they walk on the firetile without boots the game will restart
 */
public class FireTile extends Tile {

    /**
     * Constructor for the fire tile
     * @param row of where the firetile is located
     * @param col of where the firetile is located
     */
    public FireTile(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isWalkable() {
        return main.getPlayer().isInInventory(new Boots(0,0));
    }

    /**
     * Method that allows the player to interact with the firetile
     * If the player enters/walk ontop of the firetiel without the boots, then the game will instantly restart
     * If the player has the boots, then they can freely interact (walk) on the firetile
     */
    @Override
    public void interact() {

    }
}
