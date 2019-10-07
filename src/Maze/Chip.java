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
        this.setPriority(2);
    }

    @Override
    public void interact() {
        main.decrementChipsRemaining();

        //remove chip from tile
        getTile().removeItem(this);

        // Decrement chips left
        main.getFrame().getInfoPanel().decrementChipsRemaining();
        main.getLevelBoard().updateFields();
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
