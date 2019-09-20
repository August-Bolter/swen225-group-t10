package Application;

import Maze.*;
import Persistence.LoadJSON;
import Render.MainFrame;

import java.util.*;

public class Main {
    private Timer timeRemaining = new Timer(); //Level timer
    private List<Chip> allChips = new ArrayList<Chip>();
    private int originalNumberOfPokeballs;
    private Player player;
    private LevelBoard levelBoard;
    private boolean gameover = false;
    private MainFrame frame;


    private void setup() {
        timer(100);
        createPokeballs(); //Probably don't need this
        levelBoard = LoadJSON.loadLevelFromJSON(1);

        frame = new MainFrame(this);
    }

    /**
     * Method to initalise pokeballs in their correct positions
     */
    private void createPokeballs() {
        allChips.add(new Chip(12, 10));
        allChips.add(new Chip(18, 10));
        allChips.add(new Chip(10, 13));
        allChips.add(new Chip(10, 15));
        allChips.add(new Chip(13, 14));
        allChips.add(new Chip(17, 14));
        allChips.add(new Chip(20, 13));
        allChips.add(new Chip(20, 15));
        allChips.add(new Chip(15, 16));
        allChips.add(new Chip(14, 19));
        allChips.add(new Chip(16, 19));
    }

    private boolean doMove(LevelBoard.Direction direction){
        Tile currentPos = player.getCurrentPos();
        Tile desiredTile = levelBoard.getTileAtPosition(currentPos, direction);
        if (desiredTile != null && desiredTile.isWalkable()) {
            player.setCurrentPos(desiredTile);
            desiredTile.interact();
            for (Item item : desiredTile.getItems()){
                item.interact();
            }
            return true;
        }
        return false;
    }

    private void run(){
        while (!gameover){
            //Render.updateGUI, and other stuff we need to update
        }
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

    public static void main(String[] args) {
        System.out.println("espeon is the best Eevee evo");
        Main game = new Main();
        game.setup();
    }


}
