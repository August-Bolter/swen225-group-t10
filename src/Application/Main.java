package Application;

import Maze.Board;
import Maze.Pokeball;
import Maze.Tile;

import java.util.*;

public class Main {
    private Timer timeRemaining = new Timer();
    private List<Pokeball> allPokeballs = new ArrayList<Pokeball>();
    private int originalNumberOfPokeballs;
    private Player player;
    private Board board;


    private void setup() {
        timer(5);
        createPokeballs();
    }

    private void createPokeballs() {
        allPokeballs.add(new Pokeball(12, 10));
        allPokeballs.add(new Pokeball(18, 10));
        allPokeballs.add(new Pokeball(10, 13));
        allPokeballs.add(new Pokeball(10, 15));
        allPokeballs.add(new Pokeball(13, 14));
        allPokeballs.add(new Pokeball(17, 14));
        allPokeballs.add(new Pokeball(20, 13));
        allPokeballs.add(new Pokeball(20, 15));
        allPokeballs.add(new Pokeball(15, 16));
        allPokeballs.add(new Pokeball(14, 19));
        allPokeballs.add(new Pokeball(16, 19));
    }

    private void doMove(String direction){
        Tile currentPos = player.getCurrentPosition();
        Tile desiredTile = board.getTileAtPosition(currentPos, direction);
        if (desiredTile.isWalkable()){
            player.setCurrentPosition(desiredTile);
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
            }
        }, seconds*1000);
    }


    public static void main(String[] args) {
        System.out.println("espeon is the best Eevee evo");
        Main game = new Main();
        game.setup();
    }


}
