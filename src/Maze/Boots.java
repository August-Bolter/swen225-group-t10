package Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Boots extends Item {

    public Boots(int row, int col) {
        super(row, col);
    }

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

    public Image getImage() {
        String path = PATH + "boots" + ".png";

        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new Error(path+"\nThe image failed to load:" + e);
        }
    }
}
