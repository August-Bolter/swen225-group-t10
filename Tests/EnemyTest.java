import Application.Main;
import Maze.Enemy;
import Maze.FreeTile;
import Maze.Tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the enemy
 */
class EnemyTest {

    @Test
    void interact() {
        Main game = new Main("Tester",1);
    }

    @Test
    void getRow() {
        Main game = new Main("Tester",2);
        int expected = 0;
        int actual = 100;
        //looping through to get the one enemy
        for(Enemy e: game.getLevelBoard().getEnemies()) {
           actual = e.getRow();
        }
        assertEquals(expected, actual);
    }

    @Test
    void getCol() {
        Main game = new Main("Tester",2);
        int expected = 0;
        int actual = 100;
        //looping through to get the one enemy
        for(Enemy e: game.getLevelBoard().getEnemies()) {
            actual = e.getCol();
        }
        assertEquals(expected, actual);
    }

    @Test
    void setCurrentPos() {
        Main game = new Main("Tester",2);
        Tile expected = new FreeTile(0, 0);
        Tile actual = new FreeTile(10, 10);
        //looping through to get the one enemy
        for(Enemy e: game.getLevelBoard().getEnemies()) {
            actual = e.getTile();
        }
        assertEquals(expected, actual);
    }

}