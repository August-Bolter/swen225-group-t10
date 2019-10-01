
import Maze.DoorTile;
import Maze.Item;
import Maze.Key;
import Maze.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Series of tests for the Key class
 */
public class KeyTest{

    /**
     * Tests when the door and key are matching
     */
    @Test
    public void isMatchingColour() {
        Key k = new Key(0, 0, "Blue");
        DoorTile d = new DoorTile(2, 2, "Blue");
        assertTrue(k.isMatchingColour(d));
    }

    /**
     * Tests when the door and key are not matching
     */

    @Test
    public void isNotMatchingColour() {
        Key k = new Key(0, 0, "Blue");
        DoorTile d = new DoorTile(2, 2, "Green");
        assertFalse(k.isMatchingColour(d));
    }

    /**
     * Checks the when interacting with the key, it is removed from the tile and then added to the player's inventory
     */
    @Test
    public void interact() {
        Key k = new Key(2, 2, "Red");
        Player p = new Player(0, 0);
   //     k.interact();
//        for(Item i: p.getInventory()) {
//            System.out.println(i);
//        }
    }

    /**
     * Tests whether the correct colour returns
     */
    @Test
    public void getColor() {
        Key k = new Key(0, 0, "Blue");
        k.getColor();
        assertTrue(k.getColor().equals("Blue"));
    }
}



