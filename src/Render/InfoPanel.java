package Render;

import Application.Main;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private Main game;

    private JLabel level;
    private JLabel time;
    private JLabel chips;

    private JPanel inventoryPanel;
    private TilePanel[] inventory;


    /**
     * Info panel needs:
     * Level label + level number
     * Time + time remaining
     * Chips left
     * Inventory (an array with 8 pos) so it'll be 8 labels that can have
     * an image drawn over them
     */
    public InfoPanel(Main game) {
        this.game = game;

        setLayout(new GridLayout(4,1));

        display();
    }

    public void display() {
        setVisible(true);
    }

}
