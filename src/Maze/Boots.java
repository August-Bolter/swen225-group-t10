package Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Boots class- this is a type of item
 * Boots are needed to be 'picked up' by the player before they can move through
 * CHECKTHIS
 */
public class Boots extends Item {

    /**
     * Constructor for boots
     * @param row of where the boots are located
     * @param col of where the boots are located
     */
    public Boots(int row, int col) {
        super(row, col);
        this.setPriority(4);
    }

    /**
     * Interact method: this method is called when the player moves on a valid tile
     */
    @Override
    public void interact() {
        //remove boots from tile
        getTile().removeItem(this);

        //Add chip to players inventory
        try {
            main.getPlayer().addInventory(this);
        }
        catch(InventoryException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to 'draw' the boots on the screen
     * @return image of the boots
     */
    public Image getImage() {
        if (main != null) {
            BufferedImage img = main.itemImages.get("boots");
            if (img != null) {
                return img;
            }
        }

        String path = PATH + "boots" + ".png";

        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new Error(path+"\nThe image failed to load:" + e);
        }
    }
}
