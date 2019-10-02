package Application;

import Maze.*;
import Persistence.LoadJSON;
import Render.MainFrame;

import java.util.*;

public class Main {
    private Timer timeRemaining = new Timer(); //Level timer
    private Timer gameloop; //Timer object to ensure the game updates at a constant rate, regardless of the computer the game is running on
    private boolean gameover = false; //boolean the checks if the player has lost 3 levels

    private List<Chip> allChips = new ArrayList<Chip>();
    private int chipsRemaining;


    private Player player;
    private LevelBoard levelBoard;
    private MainFrame frame;


    private void setup() {
        gameloop = new Timer();

        //gameloop.schedule();

        gameloop.schedule(new GameLoop(), 0, 1000 / 60); //New timer at 60fps, the timing mechanism

        timer(100);

        levelBoard = LoadJSON.loadLevelFromJSON(1);
        levelBoard.setMain(this);
        player = levelBoard.getPlayer();
        player.setCurrentPos();
        levelBoard.setMain(this);
        frame = new MainFrame(this);
        chipsRemaining = levelBoard.getTotalChips();
    }



    /**
     * Method that is called when you want to move in a direction
     * @param direction
     * @return true is move is valid else false
     */
    public boolean doMove(LevelBoard.Direction direction){
        Tile currentPos = player.getCurrentPos();
        Tile desiredTile = levelBoard.getTileAtPosition(currentPos, direction);
        if (desiredTile != null) {
            desiredTile.interact();
            if (desiredTile.isWalkable()) {
                Tile newTile = levelBoard.getTileAtPosition(currentPos, direction);
                player.move(newTile);
            }
            player.setDirection(direction);
            for (Iterator<Item> iterator = desiredTile.getItems().iterator(); iterator.hasNext();) {
                iterator.next().interact();
            }
            return true;
        }
        return false;
    }

    /**
     * A timer method. Will print game over after a certain amount of time.
     * Used to measure how long the player is taking for each level.
     * @param seconds
     */
    public void timer(int seconds){
        timeRemaining.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("-------game over---------");
                gameover = true;
            }
        }, seconds*1000);
    }

    public MainFrame getFrame() {
        return frame;
    }

    public LevelBoard getLevelBoard() {
        return levelBoard;
    }

    public Timer getTimeRemaining() {
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

    public boolean allChipsCollected(){
        return chipsRemaining == 0;
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.setup();
    }

    private class GameLoop extends TimerTask{

        @Override
        public void run() {
            //gameupdates
            //render
            if (!gameover){
                gameloop.cancel();
            }
        }
    }

    public Player getPlayer() {
        return player;
    }
}
