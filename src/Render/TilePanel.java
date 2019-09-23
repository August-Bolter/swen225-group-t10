package Render;

import Maze.Tile;

import javax.swing.*;
import java.awt.*;

public class TilePanel extends JPanel {
    private Tile tile;
    private JLabel image;
//    private static final int SIZE = 32;

    public TilePanel(Tile tile) {
//        setPreferredSize(new Dimension());
//        setLayout(new FlowLayout(FlowLayout.CENTER));
        this.tile = tile;
    }


    public void paint(Graphics g) {
        super.paint(g);
//        getComponent(0);
        if(tile.getImage() == null) throw new RuntimeException(tile.getClass().getName() + "Tile image was null");
        image = new JLabel(new ImageIcon(tile.getImage()));
        add(image);
        setVisible(true);
        if (tile.hasItem())
            g.drawImage(tile.getItems().get(0).getImage(), 0, 0, this); // FIXME currently only displaying the first item in the list

    }


}
