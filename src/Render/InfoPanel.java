package Render;

import Application.Main;
import Maze.FreeTile;
import Maze.Item;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that display information about the game such as time left or chips left
 */
public class InfoPanel extends JPanel {
    private JLabel level;
    private JLabel timeRemaining;
    private JLabel chipsRemaining;
    private JPanel inventoryPanel;
    private ReplayPanel replayPanel;

    private int timeLeft, chipsLeft;

    private MainFrame frame;

    private TilePanel[] invPanels;
    private Item[] inventory;

    /**
     * Info panel needs:
     * Level label + level number
     * Time + time remaining
     * Chips left
     * Inventory (an array with 8 pos) so it'll be 8 labels that can have
     * an image drawn over them
     * @param game the game whose info to display
     */
    public InfoPanel(MainFrame frame) {
        this.frame = frame;
        Main game = frame.getGame();
        inventory = game.getPlayer().getInventory();

        setLayout(new GridLayout(5,1));

        level = new JLabel("Level: " + game.getLevelBoard().getTitle());
        timeRemaining = new JLabel("Time Remaining: " + game.getLevelBoard().getTimeLimit());
        timeLeft = game.getLevelBoard().getTimeLimit();
        chipsRemaining = new JLabel("Chips Remaining: " + game.getLevelBoard().getTotalChips());
        chipsLeft = game.getLevelBoard().getTotalChips();

        inventoryPanel = new JPanel(new GridLayout(2, 4));
        invPanels = new TilePanel[inventory.length];

        replayPanel = new ReplayPanel(frame);

        redraw();
    }


    /**
     * Creates a border
     */
    public void createBorder() {
        Border blackline = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        setBorder(blackline);
    }

    public InfoPanel(MainFrame frame, ReplayPanel replayPanel) {
        this.frame = frame;
        Main game = frame.getGame();
        inventory = game.getPlayer().getInventory();

        setLayout(new GridLayout(5,1));

        level = new JLabel("Level: " + game.getLevelBoard().getTitle());
        timeRemaining = new JLabel("Time Remaining: " + game.getLevelBoard().getTimeLimit());
        timeLeft = game.getLevelBoard().getTimeLimit();
        chipsRemaining = new JLabel("Chips Remaining: " + game.getLevelBoard().getTotalChips());
        chipsLeft = game.getLevelBoard().getTotalChips();

        inventoryPanel = new JPanel(new GridLayout(2, 4));
        invPanels = new TilePanel[inventory.length];

        this.replayPanel = replayPanel;
        redraw();
    }

    /**
     * Reduces the timeLeft by one second
     */
    public void decrementTimeRemaining() {
        timeRemaining.setText("Time Remaining: " + --timeLeft);
    }

    public MainFrame getFrame() {
        return frame;
    }

    /**
     * Reduces the chips left by one
     */
    public void decrementChipsRemaining() {
        chipsRemaining.setText("Chips Remaining: " + --chipsLeft);
    }

    public void setChipsLeft(int chips) {
        chipsLeft = chips;
        chipsRemaining.setText("Chips Remaining: " + chipsLeft);
    }

    public void setTimeLeft(int time) {
        timeLeft = time;
        timeRemaining.setText("Time Remaining: " + timeLeft);
    }

    public void setInventory(Item[] inventory) {
        this.inventory = inventory;
    }

    /**
     * Redraws the inventory
     */
    public void redraw() {
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
            tp.repaint();
        }

        add(replayPanel);

        revalidate();
        repaint();
    }

    public ReplayPanel getReplayPanel() {
        return replayPanel;
    }
}
