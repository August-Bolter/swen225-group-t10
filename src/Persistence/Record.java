package Persistence;

import Application.Main;

import java.io.File;

public class Record {
    boolean isRecording = false;
    int startTime, endTime;
    int count = -1;
    File moves;
    Main game;
    String fileName;

    public Record(Main game) {
        this.game = game;
    }

    public int getCount() {
        return count;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public File getMoves() {
        return moves;
    }

    public void setRecording(boolean recording) {
        isRecording = recording;
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
