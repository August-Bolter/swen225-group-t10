package Application;

import Maze.Player;
import Maze.Tile;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Testcases for the methods located in the main method
 */

@RunWith(Arquillian.class)
public class MainTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Main.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }


    /**
     * Tests a valid move
     * @return
     */
    @org.junit.Test
    public void doValidMove() {
        //get class get methods
        Method[]  methodList = getClass().getDeclaredMethods();
        for(Method m :methodList){
            if(m.getName() == "doMove"){
                Class c = m.getDeclaringClass();
                createDeployment();
            }
        }
    }


    /**
     * Tests an invalid move
     * @return
     */
    @org.junit.Test
    public void doInValidMove() {

    }

}
