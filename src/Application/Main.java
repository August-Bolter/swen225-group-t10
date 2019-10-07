package Application;

import Maze.*;
import Persistence.LoadJSON;
import Render.MainFrame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

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


    private void setup() {
        levelBoard = LoadJSON.loadLevelFromJSON(-1);
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
                iterator.next().interact();
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
        while (seconds > 0) {
            long now = System.nanoTime();
            if (now - lastTick > 1000000000) {
                frame.getInfoPanel().decrementTimeRemaining();
                //frame.getInfoPanel().updateIntegers();
                //System.out.println("tick " + seconds);


                for (Enemy e : enemies){
                    if (e instanceof BlueEnemy){
                        ((BlueEnemy) e).moveEnemy();
                    }
//                    if (e instanceof RedEnemy){
//                        ((RedEnemy) e).shoot();
//                    }
                }

//                frame.getBoardPanel().updateBoard();
                lastTick = now;
                timeRemaining--;
                levelBoard.updateFields();
                seconds--;
                //this.getFrame().getBoardPanel().redraw();
//                try {
//                    Robot r = new Robot();
//                    int keycode = KeyEvent.VK_0;
//                    r.keyPress(keycode);
//                } catch (AWTException e) {
//                    e.printStackTrace();
//                }

//                frame.keyPressed(new KeyEvent(new Button(), 1, 20, 1, 10, '0'));
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
