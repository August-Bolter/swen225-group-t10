import Application.Main;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;


public class MainTest {

    /**
     * Tests whether a board is made
     */
    @Test
    public void createBoardTest(){
   //     Main game = new Main(1);
//        game.setup(2);

    }

    /**
     * Tests a valid move
     */
    @Test
    public void doValidMove() {
        //get class get methods
        Method[]  methodList = getClass().getDeclaredMethods();
        for(Method m :methodList){
            if(m.getName() == "doMove"){
                Class c = m.getDeclaringClass();
            }
        }
    }


    /**
     * Tests an invalid move
     */

    @Test
    public void doInValidMove() {

    }

}
