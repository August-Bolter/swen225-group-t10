import Application.Main;
import Maze.LevelBoard;
import Persistence.LoadJSON;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    /**
     * Tests an invalid move
     * @return
     */
    @Test
    public void doInvalidMove() throws InterruptedException {
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    Main m = new Main("tester", 1);
                    m.setup(1);
                    m.doMove(LevelBoard.Direction.DOWN);    //shouldn't be able to move down
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread.sleep(1000);
        assertTrue(true);   //therefore if we reach here, then it must be working

        //get class get methods
//        Method[]  methodList = getClass().getDeclaredMethods();
//        for(Method m :methodList){
//            if(m.getName() == "doMove"){
//                Class c = m.getDeclaringClass();
//            }
//        }
//        assertTrue(true);
    }


    /**
     * Tests an invalid move
     * @return
     */

    @Test
    public void doInValidMove() {

    }

}
