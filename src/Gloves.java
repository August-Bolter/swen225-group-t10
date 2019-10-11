import Maze.InventoryException;
import Maze.Item;

public class Gloves extends Item {
    public Gloves(int row, int col) {
        super(row, col);
        this.setPriority(5);
    }

    public void interact() {
        this.getTile().removeItem(this);

        try {
            this.main.getPlayer().addInventory(this);
        } catch (InventoryException var2) {
            var2.printStackTrace();
        }

    }
}