import Application.Main;
import Maze.InventoryException;
import Maze.Key;
import Maze.LevelBoard;
import Maze.Player;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void addInventory() {
        Main game = new Main();
        try {
            game.getPlayer().addInventory(new Key(1,1,"blue"));
        } catch (InventoryException e) {
            e.printStackTrace();
        }

        assertTrue(game.getPlayer().getInventory()[0].equals(new Key(1,1,"blue")));
    }

    @Test
    void removeItemFromInventory() {
        Main game = new Main();
        try {
            game.getPlayer().addInventory(new Key(1,1,"blue"));
            game.getPlayer().removeItemFromInventory(new Key(1,1,"blue"));
        } catch (InventoryException e) {
            e.printStackTrace();
        }

        assertTrue(game.getPlayer().getInventory()[0] == null);
    }
}