package Render;

import Application.Main;
import Maze.FreeTile;
import Maze.Item;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class InfoPanel extends JPanel {
    Main game;

    JLabel level;
    JLabel timeRemaining;
    JLabel chipsRemaining;
    JPanel inventoryPanel;
    int timeLeft, chipsLeft;

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
        timeRemaining = new JLabel("Time Remaining: " + game.getLevelBoard().getTimeLimit());
        timeLeft = game.getLevelBoard().getTimeLimit();
        chipsRemaining = new JLabel("Chips Remaining: " + game.getLevelBoard().getTotalChips());
        chipsLeft = game.getLevelBoard().getTotalChips();

        inventoryPanel = new JPanel(new GridLayout(2, 4));
        invPanels = new TilePanel[inventory.length];

        createBorder();
        redraw();
    }

    public void createBorder() {
        Border blackline = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        setBorder(blackline);
    }

    /**
     * Reduces the timeLeft by one second
     */
    public void decrementTimeRemaining() {
        timeRemaining.setText("Time Remaining: " + --timeLeft);
    }

    /**
     * Reduces the chips left by one
     */
    public void decrementChipsRemaining() {
        chipsRemaining.setText("Chips Remaining: " + --chipsLeft);
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
            invPanels[i].getTile().addItem(inventory[i]);
            inventoryPanel.add(invPanels[i]);
        }
        JPanel outerInvPanel = new JPanel(new FlowLayout());
        outerInvPanel.add(inventoryPanel);

        add(level);
        add(timeRemaining);
        add(chipsRemaining);
        add(outerInvPanel);

        for (TilePanel tp : invPanels) {
            tp.redraw();
        }
    }
}
