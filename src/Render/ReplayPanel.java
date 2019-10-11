package Render;

import Application.Main;
import Persistence.Record;
import Persistence.Replay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Optional;

public class ReplayPanel extends JPanel implements ActionListener {
    private JButton replayButton, recordButton, nextStepButton, exitButton;

    private Record record;
    private Replay replay;

    private MainFrame frame;
    private Main game;
    private JPanel replayJPanel;

    public ReplayPanel(MainFrame frame) {
        this.frame = frame;
        this.game = frame.getGame();
        replayJPanel = new JPanel();
        replayJPanel.setLayout(new GridLayout(2, 2));

        replayButton = new JButton("Replay");
        recordButton = new JButton("Record");
        recordButton.addActionListener(this);
        replayButton.addActionListener(this);

        record = new Record(game);
        replay = new Replay(game, this);

        nextStepButton = new JButton("Next Step");
        nextStepButton.setVisible(false);

        exitButton = new JButton("Exit");
        exitButton.setVisible(false);

        replayJPanel.add(recordButton);
        replayJPanel.add(replayButton);
        add(replayJPanel);
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
        replayButton.setText("Change speed");
    }

    public void addReplayButtons() {
        nextStepButton.setVisible(true);
        nextStepButton.addActionListener(this);

        exitButton.setVisible(true);
        exitButton.addActionListener(this);

        replayJPanel.add(nextStepButton);
        replayJPanel.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == recordButton) {
            if (recordButton.getText().equals("Record")) {
                recordButton.setText("Stop recording");
                game.setRecord(record);
                game.setStartTime(System.nanoTime());
                record.record();
                replayButton.setEnabled(false);
            }
            else if (recordButton.getText().equals("Stop recording")){
                recordButton.setText("Record");
                record.stopRecording();
                replayButton.setEnabled(true);
                game.getCurrentRecord().setFinalTime(System.nanoTime());
            }
            else if (recordButton.getText().equals("Play")) {
                game.setFrameRate(1);
                recordButton.setText("Stop");
                game.setReplayMode(true);
                nextStepButton.setEnabled(false);
            }
            else {
                game.setFrameRate(0000000000000000.1);
                game.setReplayMode(false);
                recordButton.setText("Play");
                nextStepButton.setEnabled(true);
            }
        }

        else if (e.getSource() == replayButton) {
            if (replayButton.getText().equals("Replay")) {
                replay.replay();
            }
            else {
                frame.createChangeSpeedWindow();
            }
        }

        else if (e.getSource() == nextStepButton) {
            game.nextStep();
        }

        else if (e.getSource() == exitButton) {
            game.setReplayMode(false);
            new TitleFrame(game.getFrame());
            if (game.getLevel() == 1) {
                game.restartLevel(Optional.of(1));
            }
            else {
                game.restartLevel(Optional.of(2));
            }
            game.getFrame().redraw();
        }
    }

    public void disablePlayButton() {
        replayButton.setText("Play");
        replayButton.setEnabled(false);
        game.setFrameRate(0000000000000000.1);
    }
}
