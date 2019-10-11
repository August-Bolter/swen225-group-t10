import Maze.InfoTile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for testing the infotile
 */

class InfoTileTest {

    @Test
    public void getInfoTest(){
        InfoTile i = new InfoTile(0, 0, "This is an info tile");
        String actual = i.getInfo();
        String expected = "This is an info tile";
        assertEquals(expected, actual);
    }

}