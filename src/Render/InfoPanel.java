package Render;

import Application.Main;
import Maze.FreeTile;
import Maze.Item;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    Main game;

    JLabel level;
    JLabel timeRemaining;
    JLabel chipsRemaining;
    JPanel inventoryPanel;

    TilePanel[] invPanels;
    Item[] inventory;

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
        inventory = game.getPlayer().getInventory();

        setLayout(new GridLayout(4,1));

        level = new JLabel("Level: " + game.getLevelBoard().getTitle());
        timeRemaining = new JLabel("Time Remaining: " + game.getLevelBoard().getTimeLimit()); // FIXME Only gets the total time NOT current time
        chipsRemaining = new JLabel("Chips Remaining: " + game.getLevelBoard().getTotalChips()); // FIXME Only gets the total chips NOT current chips

        inventoryPanel = new JPanel(new GridLayout(2, 4));
        invPanels = new TilePanel[inventory.length];
        redraw();
    }

    /**
     * Only redraws the inventory
     */
    public void redraw() {
        removeAll();
        inventoryPanel.removeAll();
        for (int i = 0; i < inventory.length; i++) {
            int row = i / 4 >= 1 ? 1 : 0;
            int col = i % 4;
            invPanels[i] = new TilePanel(new FreeTile(row, col));
            invPanels[i].getTile().addItem(inventory[i]); // FIXME I shouldn't be adding new tiles
//            invPanels[i].removeAll();
            inventoryPanel.add(invPanels[i]);
        }
//        add(new JLabel(new ImageIcon(new FreeTile(0,0).getImage())));
        add(level);
        add(timeRemaining);
        add(chipsRemaining);
        add(inventoryPanel);

        for (TilePanel tp : invPanels) {
            tp.redraw();
        }
    }
}
