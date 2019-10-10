import Maze.Player;
import org.junit.jupiter.api.Test;

class BootsTest {

    @Test
    void interact() {
        Boots b = new Boots(10, 10);
        Player p = new Player(10, 10);
        b.interact();
    }

    @Test
    void getImage() {
    }
}