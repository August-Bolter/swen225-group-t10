package Maze;

public class Boots extends Item {

    public Boots(int row, int col) {
        super(row, col);
    }

    @Override
    public void interact() {
        //remove boots from tile
        getTile().removeItem(this);

        //Add boots to players inventory
        try {
            main.getPlayer().addInventory(this);
        }
        catch(InventoryException e) {
            e.printStackTrace();
        }
    }
}
