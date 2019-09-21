package Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Player class: This is the main player which the user moves to play the game.
 */
public class Player extends Item {
    private Tile currentPos;
    private Item[] inventory;
    private enum Direction {UP, DOWN, RIGHT, LEFT};
    private Direction direction;

    public Player(int row, int col) {
        super(row, col);
        //this.currentPos = startingPos;
        this.inventory = new Item[8];
        direction = Direction.DOWN;
    }

    public void setDirection(LevelBoard.Direction d) {
        if (d == LevelBoard.Direction.LEFT) {
            direction = Direction.LEFT;
        }
        else if (d == LevelBoard.Direction.RIGHT) {
            direction = Direction.RIGHT;
        }
        else if (d == LevelBoard.Direction.UP) {
            direction = Direction.UP;
        }
        else if (d == LevelBoard.Direction.DOWN) {
            direction = Direction.DOWN;
        }
    }

    //Start of getter and setter methods
    /**
     * fetches the current position of the Player.
     * @return currentPos
     */
    public Tile getCurrentPos() {
        return currentPos;
    }

    /**
     * Sets the current position of the player.
     * @param currentPos
     */
    public void setCurrentPos(Tile currentPos) {
        this.currentPos = currentPos;
    }

    /**
     * Adds a new item into the inventory (list)
     * @param newItem
     */
    public void addInventory(Item newItem) throws InventoryException {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null)
                inventory[i] = newItem;
        }
        throw new InventoryException("The player's inventory is full");
    }

    public void removeItemFromInventory(Item item){
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].equals(item)) {
                inventory[i] = null;
            }
        }
    }

    public Item[] getInventory() {
        return inventory;
    }

    @Override
    public void interact() {

    }

    public Image getImage() {
        String path = "Resources/player/"+direction.toString().toLowerCase()+".png";

        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new Error(path+"\nThe file failed to load: " + e);
        }
    }
}
