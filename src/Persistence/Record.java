package Persistence;

import Application.Main;

/**
 * @author August Bolter
 * Class to record gameplay.
 */
public class Record {
    private boolean isRecording;
    private int count = -1;
    private Main game;
    private String fileName;
    private long finalTime;

    /**
     * Creates a new recording for a given game.
     * @param game the game to record.
     */
    public Record(Main game) {
        this.game = game;
        isRecording = false;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @return true if recording, false otherwise
     */
    public boolean isRecording() {
        return isRecording;
    }

    /**
     * @param recording sets recording to true or false
     */
    public void setRecording(boolean recording) {
        isRecording = recording;
    }

    /**
     * Method to actually record.
     */
    public void record() {
        count++;
        setRecording(true);
        fileName = "src/Utility/record-" + count + ".json";
        game.recordMoves(true);
        SaveJSON.saveGame(game.getLevelBoard(), fileName, false);
    }

    public void setFinalTime(long time) {
        finalTime = time;
    }

    public long getFinalTime() {
        return finalTime;
    }

    /**
     * Method to stop the recording.
     */
    public void stopRecording() {
        setRecording(false);
        game.recordMoves(false);
        SaveJSON.endRecord(fileName, game.getFirstMove());
    }
}
