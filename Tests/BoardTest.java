import Application.Main;
import Maze.LevelBoard;
import org.junit.jupiter.api.Test;

/**
 * Class for testing whether the board will be set up
 */

public class BoardTest {

    /**
     * Test that launches the start screen for 1 second
     * For Level 1
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
     * For Level 2
     */
    /*@Test
    public void testStartScreen() throws InterruptedException{
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    Main m = new Main(2);
                    m.setup(1);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(1000);

    }*/
}
