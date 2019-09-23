package Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/** Represents a key in the game. The key has a color. The player can pick the key up and use it to unlock doors with the
 * same color. */
public class Key extends Item {
    private static final String KEY = "Key";
    String color; //The key can be blue, red, green or yellow

    /** Creates a key.
     * @param row The row (in regards to the board) of the key.
     * @param col The column (in regards to the board) of the key.
     * @param color The color of the key. */
    public Key(int row, int col, String color) {
        super(row, col);
        this.color = color;
    }

    /**
     * Checks if the door and key is a macthign colour
     * @return true if matches, false if doesn't match
     */
    public boolean isMatchingColour(DoorTile d){
            if (d.getColor().equals(color)) {
                return true;
            }
        return false;
    }

    //getter and setter methods

    /**
     * Doors can only be opened by a key that is the matching colour. We need to check if it is matching
     * @return the key's color
     */
    public String getColor() {
        return color;
    }



    public void interact() {
        //TODO: need to implement key item being able to interact with Player

        //remove key from tile
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
        String path = PATH+color+ KEY + ".png";

        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new Error(path+"\nThe image failed to load:" + e);
        }
    }
}
