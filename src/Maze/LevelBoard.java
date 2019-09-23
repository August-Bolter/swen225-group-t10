package Maze;


import Application.Main;

public class LevelBoard {
    private static final int SIZE = 32;

    //these used to public and I changed them to private and used getter and setter methods  - is this okay?
    private final String title;



    public enum Direction {LEFT, RIGHT, UP, DOWN}
    private int totalChips, timeLimit;

    private Main main;

    private Tile[][] board;

    public LevelBoard(String title, int totalChips, int timeLimit, Tile[][] board) {
        this.title = title;
        this.totalChips = totalChips;
        this.timeLimit = timeLimit;
        this.board = board;
    }


    public Tile[][] getBoard() {
        return board;
    }

    /**
     * Gets the tile at a given direction from a given position.
     * @param currentPos the given position
     * @param direction the direction from the given position
     * @return the tile at the direction
     */
    public Tile getTileAtPosition(Tile currentPos, Direction direction) {
        switch (direction) {
            case LEFT:
                if (currentPos.getCol() > 0)
                    return board[currentPos.getRow()][currentPos.getCol()-1];
                else
                    return null;
            case RIGHT:
                if (currentPos.getCol() < board.length)
                    return board[currentPos.getRow()][currentPos.getCol()+1];
                else
                    return null;
            case UP:
                if (currentPos.getRow() > 0)
                    return board[currentPos.getRow()-1][currentPos.getCol()];
                else
                    return null;
            case DOWN:
                if (currentPos.getCol() < board[0].length)
                    return board[currentPos.getRow()+1][currentPos.getCol()];
                else
                    return null;
            default:
                return null;
        }
    }

    public void linkTilesAndItemsToMain() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setMain(main);
                for (Item item : board[i][j].getItems()){
                    item.setMain(main);
                }
            }
        }
    }

    public void replaceWithEmptyTile(Tile tile){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals(tile)){
                    board[i][j] = new FreeTile(tile.getRow(), tile.getCol());
                }
            }
        }
    }



    //getter and setter methods

    /**
     * Method that gets the total number of chips
     * @return number of chips
     */
    public int getTotalChips() {
        return totalChips;
    }

    /**
     * Method that gets the timelimit
     * @return the timeLimit
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Method that gets the title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    public void setMain(Main main) {
        this.main = main;
        linkTilesAndItemsToMain();
    }

    /**
     * Returns the player currently on the board
     */
    public Player getPlayer() {
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[row].length; col++)
                if (board[row][col].hasItem())
                    for (Item item : board[row][col].getItems())
                        if (item instanceof Player)
                            return (Player) item;

        return null;
    }


}
