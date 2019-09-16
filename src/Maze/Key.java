package Maze;
import java.awt.*;
public class Key extends Item {
    Color color;

    public Key(int row, int col, Color color) {
        super(row, col);
        this.color = color;
    }
}
