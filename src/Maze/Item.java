package Maze;

import Application.Main;

/**
 * Abstract class for Item - this will be implemented by all inventory items (keys, chips). Items are on top of tiles.
 */

public abstract class Item {
    private int row;
    private int col;
    protected Main main;
    private boolean inInventory;

    /** Creates an item.
     * @param row The row (in regards to the board) of the item
     * @param col The column (in regards to the board) of the item */
    public Item(int row, int col) {
        this.row = row;
        this.col = col;
        inInventory = false;
    }

    //Start of getter and setter methods

    /**
     * Changes the current location of the item
     * @param newRow
     * @param newCol
     */
    public void changeLocation(int newRow, int newCol){
        this.row = newRow;
        this.col = newCol;
    }

    /**
     * Gets the column
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     * Gets the row
     * @return
     */
    public int getRow(){
        return row;
    }

    /**
     * Sets the item's row number
     * @param rowNum
     */
    public void setRow(int rowNum){ this.row = rowNum;}

    /**
     * Sets the item's col number
     * @param colNum
     */
    public void setCol(int colNum){this.col = colNum;}

    /**
     * Get's the tile associated with the item
     * @return The associated tile
     * */
    public Tile getTile() {
        if (inInventory) {
            return null;
        }
        Tile[][] tiles = main.getLevelBoard().getBoard();
        return tiles[row][col];
    }

    /**
     * Set's the item to be out of/into the players inventory.
     * */
    public void setInInventory(boolean inInventory) {
        this.inInventory = inInventory;
    }

    /**
     * Method to interact with the tile
     */
    public abstract void interact();

    /**
     * Check's if an object equals an Item
     * @return Represents if the two objects are equal
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o.getClass() != this.getClass()) return false;
        Item i = (Item) o;
        if (i.getRow() != this.getRow() || i.getCol() != this.getCol()) return false;
        return true;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
