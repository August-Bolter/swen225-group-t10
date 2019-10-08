package Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gloves extends Item {

    public Gloves(int row, int col) {
        super(row, col);
        this.setPriority(5);
    }


    @Override
    public void interact() {
        //remove gloves from tile
        getTile().removeItem(this);

        //Add gloves to players inventory
        try {
            main.getPlayer().addInventory(this);
        }
        catch(InventoryException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {

        if (main != null) {
            BufferedImage img = main.itemImages.get("gloves");
            if (img != null) {
                return img;
            }
        }

        String path = PATH + "gloves" + ".png";

        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new Error(path+"\nThe image failed to load:" + e);
        }
    }
}
