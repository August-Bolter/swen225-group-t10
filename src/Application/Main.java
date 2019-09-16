package Application;

import Maze.Pokeball;
import Maze.Tile;
import Render.MainFrame;

import java.util.*;

public class Main {
    private MainFrame frame;
    private Timer timeRemaining = new Timer();
    private List<Pokeball> allPokeballs = new ArrayList<Pokeball>();


    private void setup() {
        frame = new MainFrame();
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

    public MainFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        System.out.println("espeon is the best Eevee evo");
        Main game = new Main();
        game.setup();
    }


}
