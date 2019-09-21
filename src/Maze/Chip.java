package Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/** Represents a treasure (chip) in the game. This item can be picked up by the player. Once they have all the chips in the
 * level they can pass through the GateTile and finish the level by reaching the ExitTile. */

public class Chip extends Item {
    private static final String POKEBALL = "pokeball";

    /** Creates a chip
     * @param row The row (in regards to the board) of the chip
     * @param col The column (in regards to the board) of the chip
     * */
    public Chip(int row, int col) {
        super(row, col);
    }

    @Override
    public void interact() {
        //TODO: need to implement chip tile being able to interact with Player
        main.decrementChipsRemaining();

        //remove chip from tile
        Tile tile = getTile();
        for (Item i : tile.getItems()){
            if (i.equals(this)){
                tile.removeItem(i);
            }
        }

        //Add chip to players inventory
        try {
            main.getPlayer().addInventory(this);
        }
        catch(InventoryException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        String path = PATH + POKEBALL + ".png";

        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new Error(path+"\nThe image failed to load:" + e);
        }
    }

}
