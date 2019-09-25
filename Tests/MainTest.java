import Application.Main;
import Maze.LevelBoard;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    /**
     * Tests a valid move
     * @return
     */

    @Test
    public void doValidMove() {
        //get class get methods
        Main main = new Main();
        int playerRow = main.getPlayer().getRow();
        int playerCol = main.getPlayer().getCol();
        main.doMove(LevelBoard.Direction.LEFT);
        assertTrue(main.getPlayer().getRow() == playerRow && main.getPlayer().getCol() == playerCol);
    }
}
