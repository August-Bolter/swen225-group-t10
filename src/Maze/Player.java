package Maze;

import java.util.List;

/**
 * Player class: This is the main player which the user moves to play the game.
 */
public class Player {
    private Tile currentPos;
    private List<Item> inventory;

    public Player(Tile startingPos){
        this.currentPos = startingPos;
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
    public void addInventory(Item newItem){
        inventory.add(newItem);
    }

}
