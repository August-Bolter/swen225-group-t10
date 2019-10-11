import Application.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Thread.sleep(10000);

    }

    /**
     * Test that launches the start screen for 1 second
     */
    /*@Test
    public void testStartScreen() throws InterruptedException{
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    Main m = new Main(1);
                    m.startScreen();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(10000);

    }*/
}
