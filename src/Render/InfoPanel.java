package Render;

import Application.Main;
import Maze.FreeTile;
import Maze.Item;
import Maze.LevelBoard;
import Persistence.LoadJSON;
import Persistence.Record;
import Persistence.Replay;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class InfoPanel extends JPanel {
    Main game;

    JLabel level;
    JLabel timeRemaining;
    JLabel chipsRemaining;
    JPanel inventoryPanel;
    JButton replayButton, recordButton, nextStepButton, previousStepButton, exitButton;
    JPanel stepPanel;
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
        nextStepButton = new JButton("Next Step");
        nextStepButton.setVisible(false);
        previousStepButton = new JButton("Previous Step");
        previousStepButton.setVisible(false);
        stepPanel = new JPanel();
        stepPanel.setVisible(false);
        stepPanel.setLayout(new GridLayout(1, 2));
        stepPanel.add(nextStepButton);
        stepPanel.add(previousStepButton);

        exitButton = new JButton("Exit");
        exitButton.setVisible(false);

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
        });

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    replay.replay();
            }
        });
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
     * Only redraws the inventory
     */
    public void redraw() {
//        removeAll();
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
        add(recordButton);
        add(replayButton);
        add(stepPanel);
        add(exitButton);

        for (TilePanel tp : invPanels) {
            tp.repaint();
        }

        revalidate();
        repaint();
    }

    public File openFileChooser() {
        JFileChooser chooseFile = new JFileChooser("src/Utility");
        chooseFile.setDialogTitle("Please select a replay file (.json format)");
        int chooseValue = chooseFile.showOpenDialog(InfoPanel.this);
        if (chooseValue == JFileChooser.APPROVE_OPTION) {
            return chooseFile.getSelectedFile();
        }
        else {
            return null;
        }
    }

    public void changeButtons() {
        recordButton.setText("Play");
        recordButton.removeActionListener(recordButton.getActionListeners()[0]);
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (recordButton.getText().equals("Play")) {
                        game.setFrameRate(1);
                        recordButton.setText("Stop");
                        game.setReplayMode(true);
                        nextStepButton.setEnabled(false);
                        previousStepButton.setEnabled(false);
                    } else {
                        game.setFrameRate(0000000000000000.1);
                        game.setReplayMode(false);
                        recordButton.setText("Play");
                        nextStepButton.setEnabled(true);
                        previousStepButton.setEnabled(true);
                    }
            }
        });

        replayButton.setText("Change speed");
        replayButton.removeActionListener(replayButton.getActionListeners()[0]);
        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    mainFrame.createChangeSpeedWindow();
            }
        });
    }

    public void addReplayButtons() {
        stepPanel.setVisible(true);

        nextStepButton.setVisible(true);
        nextStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    game.nextStep();
            }
        });
        stepPanel.add(nextStepButton);

        previousStepButton.setVisible(true);
        previousStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    game.reverseStep();
            }
        });
        stepPanel.add(previousStepButton);

        exitButton.setVisible(true);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    game.setReplayMode(false);
                    //Needs to take user back to title screen
            }
        });
        add(stepPanel);
        add(exitButton);
    }
}
