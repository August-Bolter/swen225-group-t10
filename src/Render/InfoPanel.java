package Render;

import Application.Main;
import Maze.FreeTile;
import Maze.Item;

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
        level = new JLabel("Level: " + game.getLevelBoard().getTitle());
        time = new JLabel("Time Remaining: " + game.getTimeRemaining());
        chips = new JLabel("Chips Remaining: " + game.getChipsRemaining());

        Item[] invItems = game.getPlayer().getInventory();

        // Makes an array of free tiles that hold the inventory items on top of them
        for (int i = 0; i < invItems.length; i++) {
            int row = i / 4 < 1 ? 0 : 1; // Inventory is 2 rows by 4 cols
            int col = i % 4;
            FreeTile t = new FreeTile(row, col);
            t.addItem(invItems[i]);
            inventory[i] = new TilePanel(t);
        }

        setVisible(true);
    }

}
