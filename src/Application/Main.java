package Application;

import Maze.*;
import Persistence.LoadJSON;
import Persistence.Record;
import Persistence.Replay;
import Persistence.SaveJSON;
import Render.MainFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    private int timeRemaining; //Level timer
    private Timer gameloop; //Timer object to ensure the game updates at a constant rate, regardless of the computer the game is running on
    private boolean gameover = false; //boolean the checks if the player has lost 3 levels

    private List<Chip> allChips = new ArrayList<Chip>();
    private int chipsRemaining;

    private Random generator = new Random();
    private long seed;

    private int level = 2;

    private Player player;
    private LevelBoard levelBoard;
    private MainFrame frame;
    private List<Enemy> enemies;
    private boolean recordMoves;
    private Record currentRecord;
    private Replay currentReplay;
    private boolean firstMove = true;
    private double frameRate;
    private boolean replayMode = false;
    private long startTime;
    private double replaySpeed;
    ArrayList<Long> executedTimes;
    private long totalStepTime;
    private long lastDiff;

    public final Map<String, BufferedImage> tileImages = new HashMap<>();
    public final Map<String, BufferedImage> itemImages = new HashMap<>();

    private void initialiseMaps() {
        try {
            // Initialise tileImages
            tileImages.put("FreeTile", ImageIO.read(new File("Resources/floor/FreeTile.png")));
            tileImages.put("ExitTile", ImageIO.read(new File("Resources/floor/ExitTile.png")));
            tileImages.put("WallTile", ImageIO.read(new File("Resources/floor/WallTile.png")));
            tileImages.put("GateTile", ImageIO.read(new File("Resources/floor/GateTile.png")));
            tileImages.put("InfoTile", ImageIO.read(new File("Resources/floor/InfoTile.png")));
            tileImages.put("blueDoor", ImageIO.read(new File("Resources/floor/blueDoor.png")));
            tileImages.put("greenDoor", ImageIO.read(new File("Resources/floor/greenDoor.png")));
            tileImages.put("redDoor", ImageIO.read(new File("Resources/floor/redDoor.png")));
            tileImages.put("yellowDoor", ImageIO.read(new File("Resources/floor/yellowDoor.png")));

            // Initialise itemImages
            itemImages.put("pokeball", ImageIO.read(new File("Resources/items/pokeball.png")));
            itemImages.put("blueKey", ImageIO.read(new File("Resources/items/blueKey.png")));
            itemImages.put("greenKey", ImageIO.read(new File("Resources/items/greenKey.png")));
            itemImages.put("redKey", ImageIO.read(new File("Resources/items/redKey.png")));
            itemImages.put("yellowKey", ImageIO.read(new File("Resources/items/yellowKey.png")));
            itemImages.put("downPlayer", ImageIO.read(new File("Resources/player/down.png")));
            itemImages.put("upPlayer", ImageIO.read(new File("Resources/player/up.png")));
            itemImages.put("leftPlayer", ImageIO.read(new File("Resources/player/left.png")));
            itemImages.put("rightPlayer", ImageIO.read(new File("Resources/player/right.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setup() {
        initialiseMaps();
        levelBoard = LoadJSON.loadLevelFromJSON(level, null);
        levelBoard.setMain(this);
        player = levelBoard.getPlayer();
        player.setCurrentPos();
        enemies = levelBoard.getEnemies();
        for (Enemy e : enemies){
            e.setCurrentPos();
        }
        levelBoard.setMain(this);
        frame = new MainFrame(this);
        chipsRemaining = levelBoard.getTotalChips();
        timeRemaining = levelBoard.getTimeLimit();

        timer();

        seed = System.currentTimeMillis();
        generator.setSeed(seed);
    }


    /**
     * Method that is called when you want to move in a direction
     * @param direction
     * @return true is move is valid else false
     */
    public boolean doMove(LevelBoard.Direction direction){
        Tile currentPos = player.getCurrentPos();
        Tile desiredTile = levelBoard.getTileAtPosition(currentPos, direction);
        Tile newTile = null;
        LevelBoard.Direction oldDirection = player.getDirection();
        if (desiredTile != null) {
            if (desiredTile.isWalkable()) {
                newTile = levelBoard.getTileAtPosition(currentPos, direction);
                player.move(newTile);
            }
            desiredTile.interact();
            player.setDirection(direction);
            for (Iterator<Item> iterator = desiredTile.getItems().iterator(); iterator.hasNext();) {
                Item item = iterator.next();
                item.interact();
            }
            if (recordMoves && !replayMode) {
                /* Need an if clause which doesn't record the move if the move doesn't change players direction or tile
                *  e.g. walking into a wall */
                String fileName = "src/Utility/record-" + currentRecord.getCount() + ".json";
                long time = System.nanoTime()-startTime;
                SaveJSON.SaveMove(fileName, direction, time, firstMove);
                firstMove = false;
            }

            return true;
        }
        return false;
    }
    

    /**
     * Keeps track of the time left and is used to control enemies
     */
    public void timer(){
        frameRate = 6; //Frame rate is 6, i.e. the board updates 6 times per second
        replaySpeed = 1; //The replay speed is 1 (100%) when the replay is loaded
        boolean firstTime = true; //A boolean that is used to get critical info only the first time the play button is pressed
        boolean beenPausedPerm = false; //Determines if the replay has been paused at least once
        boolean beenPaused = false; //Determines if the replay is currently paused
        long lastTick = System.nanoTime();
        startTime = System.nanoTime(); //The start time of the game
        executedTimes = new ArrayList<Long>(); //A list of times (of moves) that have been already executed by the replay
        long startReplayTime = 0; //The start time that is determined when the replay is loaded
        long pauseTime = 0; //The start time of the replay pause
        long unpauseTime = 0; //The end time of the replay pause
        long totalPauseTime = 0;
        totalStepTime = 0; //The total step time (time skipped by pressing the next step button)
        int tick = 0; //A tick is 1/6th of a second
        lastDiff = 0; //The last diff (before the game is paused) that is calculated
        long diff = 0; //The time that has elapsed so far for the replay
        while (timeRemaining > 0) {
            if (replayMode & firstTime) { //If we have pressed the play button for the first time
                startReplayTime = System.nanoTime();
                firstTime = false;
            }
            if (!replayMode & !firstTime & !beenPaused) { //If we have pressed the stop button
                beenPausedPerm = true; //Then the replay has been paused at least once
                beenPaused = true;
                setLastDiff(diff);
                pauseTime = System.nanoTime();
            }

            long now = System.nanoTime();
            if (replayMode) { //If we are replaying a record, (play button has been pressed)
                /* In the first iteration get the time of when the unpause happened and calculate the totalPauseTime from this */
                if (beenPaused) {
                    unpauseTime = System.nanoTime();
                    totalPauseTime = totalPauseTime + (unpauseTime - pauseTime);
                }
                /* If the game has been paused once then calculate the diff is different then if the game hasn't been paused */
                if (beenPausedPerm) {
                    /* replaySpeed is used to calculate diff, the higher the replay speed, the quicker moves will execute  */
                    diff = (long) (((now - startReplayTime) - (totalPauseTime - totalStepTime))*replaySpeed);
                }
                else {
                    diff = (long) ((now - startReplayTime + totalStepTime)*replaySpeed);
                }
                beenPaused = false;
                /* Iterate through the player moves map */
                for (Map.Entry<Long, ArrayList<String>> entry: currentReplay.getTickToMovesMap().entrySet()) {
                    /*Check if the time the move happened is less than the diff (time elapsed), i.e. the move should be executed
                      and don't execute moves that have already been executed */
                    if (diff > entry.getKey() && !executedTimes.contains(entry.getKey())) {
                        for (String s : entry.getValue()) {
                            replayMove(s); //Execute the move
                        }
                        executedTimes.add(entry.getKey()); //And register that this move has been executed
                    }
                }
            }

            //Every 1/6 of a second
            if (now - lastTick > (1000000000/replaySpeed)/frameRate) {
                lastTick = now;
                tick++;

                //Every second
                if (tick % frameRate == 0) {
                    //Update the InfoPanel information
                    frame.getInfoPanel().decrementTimeRemaining();

                    //Move each enemy
                    for (int i = 0; i < enemies.size(); i++) {
                        enemies.get(i).onTick();
                    }
                    //Update relevant variables
                    timeRemaining--;
                    levelBoard.updateFields();
                }
                frame.redraw();
            }

        }

        frame.displayInfo("Out of time");
        restartLevel();
    }

    private void replayMove(String dir) {
        /* Execute a move with a direction corresponding to the string passed in */
        if (dir.equals("LEFT")) {
            doMove(LevelBoard.Direction.LEFT);
        } else if (dir.equals("RIGHT")) {
            doMove(LevelBoard.Direction.RIGHT);
        } else if (dir.equals("UP")) {
            doMove(LevelBoard.Direction.UP);
        } else {
            doMove(LevelBoard.Direction.DOWN);
        }
        /* Update the board */
        frame.getBoardPanel().redraw();
        frame.getBoardPanel().updateBoard();
        frame.getInfoPanel().redraw();
    }

    public MainFrame getFrame() {
        return frame;
    }

    public LevelBoard getLevelBoard() {
        return levelBoard;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void decrementChipsRemaining(){
        if (chipsRemaining > 0) {
            chipsRemaining--;
        }
    }

    public int getChipsRemaining() {
        return chipsRemaining;
    }

    public void setChipsRemaining(int chipsLeft) {
        chipsRemaining = chipsLeft;
    }

    public void setTimeRemaining(int timeLeft) {
        timeRemaining = timeLeft;
    }

    public void setPlayer(Player p) {
        player = p;
    }

    public boolean allChipsCollected(){
        return chipsRemaining == 0;
    }

    public void setFrameRate(double frameRate) {
        this.frameRate = frameRate;
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.setup();
    }

    public int getRandomInt() {
        return generator.nextInt();
    }

    public void restartLevel() {
        System.out.println("RESTART CALLED");
        levelBoard = LoadJSON.loadLevelFromJSON(level, null);
        levelBoard.setMain(this);
        player = levelBoard.getPlayer();
        player.setCurrentPos();
        enemies = levelBoard.getEnemies();
        for (Enemy e : enemies){
            e.setCurrentPos();
        }

        frame.redraw();
        chipsRemaining = levelBoard.getTotalChips();
        timeRemaining = levelBoard.getTimeLimit();

        seed = System.currentTimeMillis();
        generator.setSeed(seed);
    }

    /**
     * @return the current level number e.g. 2 for level 2
     */
    public int getLevel() {
        return level;
    }

    /**
     * Adds an enemy
     * @param e the enemy to add
     */
    public void addEnemy(Enemy e) {
        enemies.add(e);
    }

    /**
     * Removes an enemy
     * @param e the enemy to remove
     */
    public void removeEnemy(Enemy e) {
        enemies.remove(e);
    }

    public Player getPlayer() {
        return player;
    }

    public void recordMoves(boolean b) {
        recordMoves = b;
    }

    public void setRecord(Record record) {
        currentRecord = record;
    }

    public void setReplay(Replay replay) {
        currentReplay = replay;
    }

    public void setLevelBoard(LevelBoard board) {
        levelBoard = board;
    }

    public void setReplayMode(boolean replayMode) {
        this.replayMode = replayMode;
    }

    public void setReplaySpeed(double replaySpeed) {
        this.replaySpeed = replaySpeed;
    }

    public void addStepTime(long stepTime) {
        long amountToAdd = stepTime - lastDiff;
        totalStepTime = totalStepTime + amountToAdd;
    }

    public void setLastDiff(long diff) {
        lastDiff = diff;
    }

    public void nextStep() {
        SortedSet<Long> sortedMoves = new TreeSet<Long>(currentReplay.getTickToMovesMap().keySet());
        for (long l : sortedMoves) {
            if (!executedTimes.contains(l)) {
                replayMove(currentReplay.getTickToMovesMap().get(l).get(0));
                executedTimes.add(l);
                addStepTime(l);
                break;
            }
        }
    }

    public void reverseStep() {
        if (executedTimes.size() != 0) {
            Long timeOfLastMove = executedTimes.get(executedTimes.size() - 1);
            String direction = currentReplay.getTickToMovesMap().get(timeOfLastMove).get(0);
            replayMove(LevelBoard.Direction.reverseDirection(direction));
            executedTimes.remove(executedTimes.size() - 1);
            addStepTime(timeOfLastMove);
        }
    }
}
