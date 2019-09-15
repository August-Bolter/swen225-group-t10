package Application;

import Maze.Pokeball;
import Maze.Tile;

import java.util.*;

public class Main {
    private Timer timeRemaining = new Timer();
    private List<Pokeball> allPokeballs = new ArrayList<Pokeball>();


    private void setup() {
        timer(5);
    }

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
