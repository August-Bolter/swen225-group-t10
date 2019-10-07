package Application;

import Maze.*;
import Persistence.LoadJSON;
import Render.MainFrame;

import java.util.*;

public class Main {
    private int timeRemaining; //Level timer
    private Timer gameloop; //Timer object to ensure the game updates at a constant rate, regardless of the computer the game is running on
    private boolean gameover = false; //boolean the checks if the player has lost 3 levels

    private List<Chip> allChips = new ArrayList<Chip>();
    private int chipsRemaining;


    private Player player;
    private LevelBoard levelBoard;
    private MainFrame frame;
    private List<Enemy> enemies;
    private List<Fireblast> fireblasts = new ArrayList<>();


    private void setup() {
        levelBoard = LoadJSON.loadLevelFromJSON(2);
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

        timer(levelBoard.getTimeLimit());
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
            if (desiredTile.isWalkable() ) {
                Tile newTile = levelBoard.getTileAtPosition(currentPos, direction);
                player.move(newTile);
            }
            desiredTile.interact();
            player.setDirection(direction);
            for (Iterator<Item> iterator = desiredTile.getItems().iterator(); iterator.hasNext();) {
                Item item = iterator.next();
                item.interact();
                if (item instanceof GreenEnemy){
                    GreenEnemy g = (GreenEnemy) item;
                    if (!g.hasMoved()){
                        System.out.println("stuck");
                        player.move(currentPos);
                    }
                }
            }
            return true;
        }
        return false;
    }
    

    /**
     * Keeps track of the time left and is used to control enemies
     * @param seconds the number of seconds until game over
     */
    public void timer(int seconds){
        long lastTick = System.nanoTime();
        int tick = 0;
        int frameRate = 2;
        while (seconds > 0) {
            long now = System.nanoTime();
            if (now - lastTick > 1000000000 / frameRate) {
                lastTick = now;
                tick++;
                if (tick % frameRate == 0) {
                    frame.getInfoPanel().decrementTimeRemaining();
                    //frame.getInfoPanel().updateIntegers();
                    List<Fireblast> toRemove = new ArrayList<>();
                    for (Fireblast fb : fireblasts) {
                        if (!fb.moveBlast()) {
                            toRemove.add(fb);
                        }
                    }
                    fireblasts.removeAll(toRemove);

                    for (Enemy e : enemies) {
                        if (e instanceof BlueEnemy) {
                            ((BlueEnemy) e).moveEnemy();
                        }
                        if (timeRemaining % 3 == 0) {
                            if (e instanceof RedEnemy) {
                                fireblasts.add(((RedEnemy) e).shoot());
                            }
                        }
                    }

                    timeRemaining--;
                    levelBoard.updateFields();
                    seconds--;
                }
                frame.redraw();
            }
        }

        System.out.println("Out of time");
        frame.displayInfo("Out of time");
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

    public boolean allChipsCollected(){
        return chipsRemaining == 0;
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.setup();
    }


    public Player getPlayer() {
        return player;
    }
}
