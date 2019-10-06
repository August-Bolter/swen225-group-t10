package Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Enemy extends Item {
    protected Tile currentPos;
    protected Maze.LevelBoard.Direction direction;
    private int row, col;

    public Enemy(int row, int col){
        super(row, col);
        this.row = row;
        this.col = col;
        direction = LevelBoard.Direction.DOWN;
    }

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

    public void doMove(Tile toMoveTo){
        currentPos.removeItem(this);
        toMoveTo.addItem(this);
        setCurrentPos(toMoveTo);
    }
    @Override
    public Image getImage() {
        String itemName = getClass().getName().substring(5);
        String path = "Resources/enemy/"+itemName+direction.toString().toLowerCase()+".png";

        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new Error(path+"\nThe file failed to load: " + e);
        }
    }

    public Tile getCurrentPos() {
        return currentPos;
    }

    public LevelBoard.Direction getDirection() {
        return direction;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    public void setCurrentPos(Tile currentPos) {
        this.currentPos = currentPos;
    }

    /**
     * Sets the current position of the Enemy by getting a tile from the board
     */
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
