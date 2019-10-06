package Persistence;

import Application.Main;

public class Record {
    boolean isRecording = false;
    int startTime, endTime;
    int count = 0;
    Main game;

    public Record(Main game) {
        this.game = game;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void setRecording(boolean recording) {
        isRecording = recording;
    }

    public void record() {
        setRecording(true);
        startTime = game.getTimeRemaining();
        game.recordMoves(true);
        SaveJSON.SaveGame(game.getLevelBoard(), count);
        count++;
    }

    public void stopRecording() {
        setRecording(false);
        endTime = game.getTimeRemaining();
    }
}
