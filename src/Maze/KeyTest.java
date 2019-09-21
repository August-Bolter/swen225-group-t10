package Maze;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class KeyTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Key.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /**
     * Tests when the door and key are matching
     */
    @org.junit.Test
    public void isMatchingColour() {
        Key k = new Key(0, 0, "Blue");
        DoorTile d = new DoorTile(2, 2, "Blue");
        assertTrue(k.isMatchingColour(d));
    }

    /**
     * Tests when the door and key are not matching
     */
    @org.junit.Test
    public void isNotMatchingColour() {
        Key k = new Key(0, 0, "Blue");
        DoorTile d = new DoorTile(2, 2, "Green");
        assertFalse(k.isMatchingColour(d));
    }

    @org.junit.Test
    public void getColor() {
    }

    @org.junit.Test
    public void interact() {
    }
}
