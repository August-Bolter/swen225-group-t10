import Maze.InventoryException;
import Maze.Item;

public class Boots extends Item {
    public Boots(int row, int col) {
        super(row, col);
        this.setPriority(4);
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