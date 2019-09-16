package Maze;

import java.util.List;

/**
 * Player class: This is the main player which the user moves to play the game
 *
 */
public class Player {
    Tile currentPos;
    List<Item> inventory;

    Player(Tile startingPos){
        this.currentPos = startingPos;
    }


}
