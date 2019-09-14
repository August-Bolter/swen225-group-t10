package Application;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private Timer timeRemaining = new Timer();

    public void timer(int seconds){
        timeRemaining.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("game over");
            }
        }, seconds*1000);
    }


    public static void main(String[] args) {
        System.out.println("espeon is the best Eevee evo");
        Main d = new Main();
        d.timer(3);

    }
}
