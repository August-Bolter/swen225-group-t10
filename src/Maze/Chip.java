package Maze;

import Application.Main;

/** Represents a treasure (chip) in the game. This item can be picked up by the player. Once they have all the chips in the
 * level they can pass through the GateTile and finish the level by reaching the ExitTile. */

public class Chip extends Item {


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
        
    }

}
