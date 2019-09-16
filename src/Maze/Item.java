package Maze;

/**
 * Abstract class for Item - this will be implemented by all inventory items
 */
public abstract class Item {
    int row;
    int col;
    public Item(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
