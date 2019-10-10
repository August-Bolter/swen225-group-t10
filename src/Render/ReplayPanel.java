package Render;

import Application.Main;
import Persistence.Record;
import Persistence.Replay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ReplayPanel extends JPanel implements ActionListener {
    private JButton replayButton, recordButton, nextStepButton, previousStepButton, exitButton;
    private JPanel stepPanel;

    private Record record;
    private Replay replay;

    private MainFrame frame;
    private Main game;

    public ReplayPanel(MainFrame frame) {
        this.frame = frame;
        this.game = frame.getGame();

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

        replayButton = new JButton("Replay");
        recordButton = new JButton("Record");

        record = new Record(game);
        replay = new Replay(game, this);

        recordButton.addActionListener(this);
        replayButton.addActionListener(this);

        add(recordButton);
        add(replayButton);
    }

    public File openFileChooser() {
        JFileChooser chooseFile = new JFileChooser("src/Utility");
        chooseFile.setDialogTitle("Please select a replay file (.json format)");
        int chooseValue = chooseFile.showOpenDialog(null);
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
                frame.createChangeSpeedWindow();
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == recordButton) {
            if (!record.isRecording()) {
                recordButton.setText("Stop recording");
                game.setRecord(record);
                record.record();
                replayButton.setEnabled(false);
            } else {
                recordButton.setText("Record");
                record.stopRecording();
                replayButton.setEnabled(true);
            }
        }

        else if (e.getSource() == replayButton) {
            replay.replay();
        }
    }
}
