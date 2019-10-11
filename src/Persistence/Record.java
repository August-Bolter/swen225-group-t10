package Persistence;

import Application.Main;

import java.io.File;

public class Record {
    boolean isRecording;
    int startTime, endTime;
    int count = -1;
    long finalTime;
    Main game;
    String fileName;

    public Record(Main game) {
        this.game = game;
        isRecording = false;
    }

    public int getCount() {
        return count;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void setRecording(boolean recording) {
        isRecording = recording;
    }

    public void setFinalTime(long time) {
        finalTime = time;
    }

    public long getFinalTime() {
        return finalTime;
    }

    public void record() {
        count++;
        setRecording(true);
        startTime = game.getTimeRemaining();
        fileName = "src/Utility/record-" + count + ".json";
        game.recordMoves(true);
        SaveJSON.SaveGame(game.getLevelBoard(), fileName, false);
    }

    public void stopRecording() {
        setRecording(false);
        endTime = game.getTimeRemaining();
        game.recordMoves(false);
        SaveJSON.endRecord(fileName);
    }
}
