package Application;

import Maze.*;
import Persistence.LoadJSON;
import Render.MainFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Main Class: This contains the methods
 * setup() - setting up the game
 * domove()
 * getPlayer()
 *
 */
public class Main {
   //start of fields
    private int timeRemaining; //Level timer
    private Timer gameloop; //Timer object to ensure the game updates at a constant rate, regardless of the computer the game is running on
    private boolean gameover = false; //boolean the checks if the player has lost 3 levels

    //for actual game play
    private List<Chip> allChips = new ArrayList<Chip>();
    private int chipsRemaining;

    private Random generator = new Random();
    private long seed;

    private int level = 3;

    //for making the board
    private Player player;
    private LevelBoard levelBoard;
    private MainFrame frame;
    private List<Enemy> enemies;
    private List<Fireblast> fireblasts = new ArrayList<>();

    //for images
    public final Map<String, BufferedImage> tileImages = new HashMap<>();
    public final Map<String, BufferedImage> itemImages = new HashMap<>();

    /**
     * Used to initliase the maps with the correct .png files
     */
    private void initialiseMaps() {
        try {
            // Initialise tileImages
            tileImages.put("FreeTile", ImageIO.read(new File("Resources/floor/FreeTile.png")));
            tileImages.put("ExitTile", ImageIO.read(new File("Resources/floor/ExitTile.png")));
            tileImages.put("FireTile", ImageIO.read(new File("Resources/floor/FireTile.png")));
            tileImages.put("TeleportTile", ImageIO.read(new File("Resources/floor/TeleportTile.png")));
            tileImages.put("WallTile", ImageIO.read(new File("Resources/floor/WallTile.png")));
            tileImages.put("GateTile", ImageIO.read(new File("Resources/floor/GateTile.png")));
            tileImages.put("InfoTile", ImageIO.read(new File("Resources/floor/InfoTile.png")));
            tileImages.put("blueDoor", ImageIO.read(new File("Resources/floor/blueDoor.png")));
            tileImages.put("greenDoor", ImageIO.read(new File("Resources/floor/greenDoor.png")));
            tileImages.put("redDoor", ImageIO.read(new File("Resources/floor/redDoor.png")));
            tileImages.put("yellowDoor", ImageIO.read(new File("Resources/floor/yellowDoor.png")));

            // Initialise itemImages
            itemImages.put("pokeball", ImageIO.read(new File("Resources/items/pokeball.png")));
            itemImages.put("gloves", ImageIO.read(new File("Resources/items/gloves.png")));
            itemImages.put("blueKey", ImageIO.read(new File("Resources/items/blueKey.png")));
            itemImages.put("greenKey", ImageIO.read(new File("Resources/items/greenKey.png")));
            itemImages.put("redKey", ImageIO.read(new File("Resources/items/redKey.png")));
            itemImages.put("yellowKey", ImageIO.read(new File("Resources/items/yellowKey.png")));
            itemImages.put("boots", ImageIO.read(new File("Resources/items/boots.png")));
            itemImages.put("downPlayer", ImageIO.read(new File("Resources/player/down.png")));
            itemImages.put("upPlayer", ImageIO.read(new File("Resources/player/up.png")));
            itemImages.put("leftPlayer", ImageIO.read(new File("Resources/player/left.png")));
            itemImages.put("rightPlayer", ImageIO.read(new File("Resources/player/right.png")));
            itemImages.put("BlueEnemyDown", ImageIO.read(new File("Resources/enemy/BlueEnemyDown.png")));
            itemImages.put("BlueEnemyLeft", ImageIO.read(new File("Resources/enemy/BlueEnemyLeft.png")));
            itemImages.put("BlueEnemyRight", ImageIO.read(new File("Resources/enemy/BlueEnemyRight.png")));
            itemImages.put("BlueEnemyUp", ImageIO.read(new File("Resources/enemy/BlueEnemyUp.png")));
            itemImages.put("GreenEnemyDown", ImageIO.read(new File("Resources/enemy/GreenEnemyDown.png")));
            itemImages.put("GreenEnemyLeft", ImageIO.read(new File("Resources/enemy/GreenEnemyLeft.png")));
            itemImages.put("GreenEnemyRight", ImageIO.read(new File("Resources/enemy/GreenEnemyRight.png")));
            itemImages.put("GreenEnemyUp", ImageIO.read(new File("Resources/enemy/GreenEnemyUp.png")));
            itemImages.put("RedEnemyDown", ImageIO.read(new File("Resources/enemy/RedEnemyDown.png")));
            itemImages.put("RedEnemyLeft", ImageIO.read(new File("Resources/enemy/RedEnemyLeft.png")));
            itemImages.put("RedEnemyRight", ImageIO.read(new File("Resources/enemy/RedEnemyRight.png")));
            itemImages.put("RedEnemyUp", ImageIO.read(new File("Resources/enemy/RedEnemyUp.png")));
            itemImages.put("FireblastLeft", ImageIO.read(new File("Resources/enemy/fireblastLeft.png")));
            itemImages.put("FireblastRight", ImageIO.read(new File("Resources/enemy/fireblastRight.png")));
            itemImages.put("FireblastDown", ImageIO.read(new File("Resources/enemy/FireblastDown.png")));
            itemImages.put("FireblastUp", ImageIO.read(new File("Resources/enemy/fireblastUp.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that setups the actual playing board
     */
    private void setup() {
        //setting up the correct level
        initialiseMaps();
        levelBoard = LoadJSON.loadLevelFromJSON(level);
        levelBoard.setMain(this);

        //setting up players
        player = levelBoard.getPlayer();
        player.setCurrentPos();

        //setting up enemies
        enemies = levelBoard.getEnemies();
        for (Enemy e : enemies){
            e.setCurrentPos();
        }
        levelBoard.setMain(this);
        frame = new MainFrame(this);
        chipsRemaining = levelBoard.getTotalChips();
        timeRemaining = levelBoard.getTimeLimit();      //level must be completed before this time limit runs out

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
        //Fetching tiles you are on want to move onto
        Tile currentPos = player.getCurrentPos();
        Tile desiredTile = levelBoard.getTileAtPosition(currentPos, direction);

        //To move onto a tile, it has to be empty
        if (desiredTile != null) {
            if (desiredTile.isWalkable() ) {
                Tile newTile = levelBoard.getTileAtPosition(currentPos, direction);
                player.move(newTile);
            }
            desiredTile.interact();
            player.setDirection(direction);

            //gets each item on the tile and interacts with them
            for (Iterator<Item> iterator = desiredTile.getItems().iterator(); iterator.hasNext();) {
                Item item = iterator.next();
                item.interact();
                //what happens when an enemy is encountered
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
     */
    private void timer(){
        long lastTick = System.nanoTime();
        int tick = 0;
        int frameRate = 6;
        while (timeRemaining > 0) {
            long now = System.nanoTime();
            if (frameRate > 0 && now - lastTick > 1000000000 / frameRate) {
                lastTick = now;
                tick++;
                if (tick % frameRate == 0) {
                    frame.getInfoPanel().decrementTimeRemaining();
                    for (Fireblast fb : fireblasts) {
                        fb.moveBlast();
                    }

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
                }
                frame.redraw();
            }
        }

        frame.displayInfo("Out of time");
        restartLevel();
    }

    //start of getter and setters

    /**
     * Getter method: Fetches the current frame
     * @return current frame
     */
    public MainFrame getFrame() {
        return frame;
    }

    /**
     * Getter method: fetches the current levelboard
     * @return levelboard we are on
     */
    public LevelBoard getLevelBoard() {
        return levelBoard;
    }

    /**
     * Getter method: fetches the time left - level must be completed before time runs out
     * @return levelboard we are on
     */
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

    public int getRandomInt() {
        return generator.nextInt();
    }

    public void restartLevel() {
        System.out.println("RESTART CALLED");
        levelBoard = LoadJSON.loadLevelFromJSON(level);
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


    public Player getPlayer() {
        return player;
    }
}
