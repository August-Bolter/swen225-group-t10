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
    private LevelBoard.Direction direction;
    private int row, col;

    public Player(int row, int col) {
        super(row, col);
        this.row = row;
        this.col = col;
        this.inventory = new Item[8];
        direction = LevelBoard.Direction.DOWN;
    }

    public void setDirection(LevelBoard.Direction d) {
        if (d == LevelBoard.Direction.LEFT) {
            direction = LevelBoard.Direction.LEFT;
        }
        else if (d == LevelBoard.Direction.RIGHT) {
            direction = LevelBoard.Direction.RIGHT;
        }
        else if (d == LevelBoard.Direction.UP) {
            direction = LevelBoard.Direction.UP;
        }
        else if (d == LevelBoard.Direction.DOWN) {
            direction = LevelBoard.Direction.DOWN;
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
     * Sets the current position of the player by getting a tile from the board
     */
    public void setCurrentPos() {
        this.currentPos = main.getLevelBoard().getBoard()[row][col];
    }

    /**
     * Adds a new item into the inventory (list)
     * @param newItem
     */
    public void addInventory(Item newItem) throws InventoryException {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = newItem;
                return;
            }
        }
        throw new InventoryException("The player's inventory is full");
    }

    // TODO remove testing method
    public void printInventory() {
        System.out.print("[");
        for (int i = 0; i < inventory.length; i++) {
            System.out.print(inventory[i] + ", ");
        }
        System.out.print("]\n");
    }

    /**
     * Removes an item from the player's inventory
     * @param item the item to remove
     */
    public void removeItemFromInventory(Item item){
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].equals(item)) {
                inventory[i] = null;
                return;
            }
        }
    }

    /**
     * Check's if the player has a particular item in their inventory
     * @param item the item to check
     * @return true if the item is in the inventory, else if not
     */
    public boolean isInInventory(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getClass().getName().equals(item.getClass().getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return the player's inventory
     */
    public Item[] getInventory() {
        return inventory;
    }

    /**
     * Player can't interact with himself
     */
    @Override
    public void interact() {
        // Intentionally unimplemented
    }

    /**
     * Moves the player by updating their current tile
     * @param tileToMoveTo the tile to move to
     */
    public void move(Tile tileToMoveTo) {
        currentPos.removeItem(this);
        tileToMoveTo.addItem(this);
        setCurrentPos(tileToMoveTo);
    }

    /**
     * Gets the image representing the player
     * Takes the player's current direction into account
     * @return the image
     */
    public Image getImage() {
        String path = "Resources/player/"+direction.toString().toLowerCase()+".png";

        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new Error(path+"\nThe file failed to load: " + e);
        }
    }
}
