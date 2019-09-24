package Render;

import Application.Main;
import Maze.Item;

import javax.swing.*;

public class InfoPanel extends JPanel {
    Main game;

    JLabel level;
    JLabel timeRemaining;
    JLabel chipsRemaining;

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



    }
}
