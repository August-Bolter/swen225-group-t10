import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;


public class MainTest {
    /**
     * Tests a valid move
     * @return
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
     * @return
     */

    @Test
    public void doInValidMove() {

    }

}
