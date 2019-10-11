import Application.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testcases for Board
 * @author Justina
 */

public class BoardTest {

    /**
     * Test that launches the start screen for 1 second
     */
    @Test
    public void testBoardSetUp() throws InterruptedException{
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    Main m = new Main(1);
                    m.setup(1);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(1000);

    }

    /**
     * Test that launches the start screen for 1 second
     */
    @Test
    public void testStartScreen() throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Main m = new Main("Test", 1);
                    m.setup(1);
                    // m.startScreen();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(1000);

    }
}
