package Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Enemy class: this is a parent class for all enemies: blueEnemy, greenEnemy and redEnemy
 */
public class Enemy extends Item {
    //start of fields
    protected Tile currentPos;
    protected Maze.LevelBoard.Direction direction;  //direction enemy is travelling in
    private int row, col;       //location of the enemy

    /**
     * Constructor for an enemy
     * @param row The row (in regards to the board) of the chip
     * @param col The column (in regards to the board) of the chip
     * @param direction enemy is travelling in
     */
    public Enemy(int row, int col, String direction){
        super(row, col);
        this.row = row;
        this.col = col;
        this.direction = LevelBoard.Direction.DOWN;
        this.setPriority(5);
    }

    /**
     * Method used to 'interact' (when the player steps on a square the enemy is on)
     * Note: the level will restart if this occurs
     */
    @Override
    public void interact() {
//        //Kill player, game over
//        if (this instanceof RedEnemy || this instanceof BlueEnemy) {
//            main.getFrame().displayInfo("Game over you lil bitch ass");
//        } else if (this instanceof GreenEnemy){
//            getTile().removeItem(this);
//            doMove(main.getLevelBoard().getTileAtPosition(currentPos, main.getPlayer().getDirection()));
//        }
    }

    /**
     * Makes the enemy move from one tile to another tile
     * @param toMoveTo - tile it is moving to
     */
    public void doMove(Tile toMoveTo){
        currentPos.removeItem(this);
        toMoveTo.addItem(this);
        setCurrentPos(toMoveTo);
    }

    @Override
    public Image getImage() {
        String itemName = getClass().getName().substring(5);
        String dir = direction.toString().toUpperCase();
        dir = dir.charAt(0) + dir.substring(1).toLowerCase();

        if (main != null) {
            BufferedImage img = main.itemImages.get(itemName+dir);
            if (img != null) {
                return img;
            }
        }



        String path = "Resources/enemy/"+itemName+dir+".png";

        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new Error(path+"\nThe file failed to load: " + e);
        }
    }


    //start of getters and setters

    /**
     * Getter method: fetches the current position of the enemy
     * @return currentPos - the tile the enemy is currently on
     */
    public Tile getCurrentPos() {
        return currentPos;
    }

    /**
     * Getter method: fetches the direction of the enemy
     * @return direction - direction enemy is going to move in
     */
    public LevelBoard.Direction getDirection() {
        return direction;
    }

    /**
     * Getter method: fetches the row of the enemy
     * @return row - row enemy is currently on
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Getter method: fetches the col of the enemy
     * @return col - col enemy is currently on
     */
    @Override
    public int getCol() {
        return col;
    }

    /**
     * Setter method: sets the row of the enemy
     * In one sense: updates the current position to the position the enemy has moved onto
     */
    public void setCurrentPos(Tile currentPos) {
        this.currentPos = currentPos;
    }


    public void setCurrentPos() {
        this.currentPos = main.getLevelBoard().getBoard()[row][col];
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public void setCol(int col) {
        this.col = col;
    }

    //straight = move in direction currently facing
    //always move straight
    //if you cannot move straight, then turn clockwise
}
