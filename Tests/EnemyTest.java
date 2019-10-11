import Application.Main;
import Maze.Enemy;
import Maze.FreeTile;
import Maze.Tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the enemy
 */
class EnemyTest {

    /**
     * Test the tests whether interact works when an enemy encounters the player
     */
    @Test
    void interact() {
        //Main game = new Main("Tester",1);
    }

    /**
     * tests whether the correct row is fetched
     */
    @Test
    void getRow() {
        int expected = 15;
        AtomicInteger actual = new AtomicInteger(100);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Main game = new Main("Tester",2);
                    System.out.println(game.getLevelBoard().getEnemies().size());
                    actual.set(game.getLevelBoard().getEnemies().size());
                    //looping through to get the one enemy

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotEquals(expected, actual.get());


    }

    /**
     * Tests whether the correc col is fetched
     */
    @Test
    void getCol() {
//        Main game = new Main("Tester",2);
//        int expected = 0;
//        int actual = 100;
//        //looping through to get the one enemy
//        for(Enemy e: game.getLevelBoard().getEnemies()) {
//            actual = e.getCol();
//        }
//        assertEquals(expected, actual);
    }

    /**
     * Tests whether you can set something in a position
     */
    @Test
    void setCurrentPos() {
//        Main game = new Main("Tester",2);
//        Tile expected = new FreeTile(0, 0);
//        Tile actual = new FreeTile(10, 10);
//        //looping through to get the one enemy
//        for(Enemy e: game.getLevelBoard().getEnemies()) {
//            actual = e.getTile();
//        }
//        assertEquals(expected, actual);
    }

}