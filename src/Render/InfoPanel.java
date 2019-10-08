package Render;

import Application.Main;
import Maze.FreeTile;
import Maze.Item;
import Maze.LevelBoard;
import Persistence.LoadJSON;
import Persistence.Record;
import Persistence.Replay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoPanel extends JPanel {
    Main game;

    JLabel level;
    JLabel timeRemaining;
    JLabel chipsRemaining;
    JPanel inventoryPanel;
    JButton replayButton, recordButton;
    int timeLeft, chipsLeft;
    Record record;
    Replay replay;
    MainFrame mainFrame;

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
    public InfoPanel(Main game, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
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

        replayButton = new JButton("Replay");
        replayButton.setFocusable(false);
        recordButton = new JButton("Record");
        recordButton.setFocusable(false);
        record = new Record(game);
        replay = new Replay(game, this);
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == recordButton) {
                    if (!record.isRecording()) {
                        recordButton.setText("Stop recording");
                        game.setRecord(record);
                        record.record();
                        replayButton.setEnabled(false);
                    }
                    else {
                        recordButton.setText("Record");
                        record.stopRecording();
                        replayButton.setEnabled(true);
                    }
                }
            }
        });

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == replayButton) {
                    replay.replay();
                }
            }
        });
        redraw();
    }

    public InfoPanel(LevelBoard board) {
        inventory = board.getPlayer().getInventory();

        setLayout(new GridLayout(4,1));

        level = new JLabel("Level: " + board.getTitle());
        timeRemaining = new JLabel("Time Remaining: " + board.getTimeLimit());
        timeLeft = board.getTimeLimit();
        chipsRemaining = new JLabel("Chips Remaining: " + board.getTotalChips());
        chipsLeft = board.getTotalChips();

        inventoryPanel = new JPanel(new GridLayout(2, 4));
        invPanels = new TilePanel[inventory.length];

        redraw();
    }

    /**
     * Reduces the timeLeft by one second
     */
    public void decrementTimeRemaining() {
        timeRemaining.setText("Time Remaining: " + --timeLeft);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    /**
     * Reduces the chips left by one
     */
    public void decrementChipsRemaining() {
        chipsRemaining.setText("Chips Remaining: " + --chipsLeft);
    }

    public Record getRecord() {
        return record;
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
//            invPanels[i].removeAll();
            inventoryPanel.add(invPanels[i]);
        }
//        add(new JLabel(new ImageIcon(new FreeTile(0,0).getImage())));
        add(level);
        add(timeRemaining);
        add(chipsRemaining);
        add(inventoryPanel);
        add(recordButton);
        add(replayButton);

        for (TilePanel tp : invPanels) {
            tp.redraw();
        }
    }

    public void openFileChooser() {
        JFileChooser chooseFile = new JFileChooser("src/Utility");
        chooseFile.setDialogTitle("Please select a replay file (.json format)");
        int chooseValue = chooseFile.showOpenDialog(InfoPanel.this);
        if (chooseValue == JFileChooser.APPROVE_OPTION) {

        }
    }
}
