package Maze;

import java.util.List;

/**
 * Player class: This is the main player which the user moves to play the game.
 */
public class Player {
    private Tile currentPos;
    private Item[] inventory;

    public Player(Tile startingPos){
        this.currentPos = startingPos;
        this.inventory = new Item[8];
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
        for (int i = 0; i < inventory.length; i++)
            if (inventory[i] == null)
                inventory[i] = newItem;


        throw new InventoryException("The player's inventory is full");
    }

}
