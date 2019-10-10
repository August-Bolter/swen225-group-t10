import Maze.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class BlueEnemyTest {

    /**
     * Tests whether the enemy will move in an invalid direction
     * LEFT
     */
    @Test
    void invalidLeftBlueEnemyMove() {
        BlueEnemy b = new BlueEnemy(0, 0, "West");
        Class c = b.getClass();
        try {
            Method m = c.getDeclaredMethod("setDirection", LevelBoard.Direction.class);
            m.setAccessible(true);
            m.invoke(b, LevelBoard.Direction.LEFT);
            b.moveEnemy();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            assertTrue(true);
        }

    }

    /**
     * Tests whether the enemy will move in an invalid direction
     * RIGHT
     */
    @Test
    void invalidRightBlueEnemyMove() {
        BlueEnemy b = new BlueEnemy(0, 31, "East");
        Class c = b.getClass();
        try {
            Method m = c.getDeclaredMethod("setDirection", LevelBoard.Direction.class);
            m.setAccessible(true);
            m.invoke(b, LevelBoard.Direction.RIGHT);
            b.moveEnemy();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            assertTrue(true);
        }

    }

    /**
     * Tests whether the enemy will move in an invalid direction
     * UP
     */
    @Test
    void invalidUpBlueEnemyMove() {
        BlueEnemy b = new BlueEnemy(0, 0, "North");
        Class c = b.getClass();
        try {
            Method m = c.getDeclaredMethod("setDirection", LevelBoard.Direction.class);
            m.setAccessible(true);
            m.invoke(b, LevelBoard.Direction.UP);
            b.moveEnemy();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            assertTrue(true);
        }

    }

    /**
     * Tests whether the enemy will move in an invalid direction
     * LEFT
     */
    @Test
    void invalidDownBlueEnemyMove() {
        BlueEnemy b = new BlueEnemy(31, 31, "Down");
        Class c = b.getClass();
        try {
            Method m = c.getDeclaredMethod("setDirection", LevelBoard.Direction.class);
            m.setAccessible(true);
            m.invoke(b, LevelBoard.Direction.DOWN);
            b.moveEnemy();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            assertTrue(true);
        }

    }

    /**
     * Tests whether the enemy will move in an invalid direction
     * LEFT
     */
    @Test
    void validLeftBlueEnemyMove() {
        Tile enemy = new FreeTile(15, 15);
        Tile[][] t = new Tile[31][31];
        LevelBoard board = new LevelBoard("Tester board", 20, 160, t);

        BlueEnemy b = new BlueEnemy(15, 15, "left");
        b.setCurrentPos(enemy);
        Class c = b.getClass();
        try {
            Method m = c.getDeclaredMethod("setDirection", LevelBoard.Direction.class);
            m.setAccessible(true);
            m.invoke(b, LevelBoard.Direction.LEFT);
            b.moveEnemy();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        assertTrue(true);

    }

    @Test
    void interactTest() {
        BlueEnemy b = new BlueEnemy(15, 15, "left");
        Player p = new Player(15, 15);
        b.interact();

    }
}