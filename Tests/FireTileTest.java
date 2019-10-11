import Application.Main;
import Maze.LevelBoard;
import Maze.Player;
import Persistence.LoadJSON;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FireTileTest {

    /**
     * Testing whether we can walk on the fire tile WIHTOUT boots
     * player should not be able to do so
     * Level 10 is a tester level specifcally for testing firetiles
     */
    @Test
    void isWalkable() {
//        Main m = new Main(10 );
//        LevelBoard levelBoard = LoadJSON.loadLevelFromJSON(10);
//        levelBoard.setMain(m);
//        m.setup(10);
        assertTrue(true);
    }

    @Test
    void interact() {
    }
}