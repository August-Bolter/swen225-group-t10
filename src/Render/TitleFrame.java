package Render;

import Application.Main;
import Maze.LevelBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;

public class TitleFrame extends JFrame implements MouseListener {

    private static Main game;
    private MainFrame frame;
    private JLabel background;
    //private int level;

    /**
     * Constructor for Title Frame
     * the Main game that is currently being used
     */
    public TitleFrame(MainFrame frame) {
        this.frame = frame;

        addMouseListener(this);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background = new JLabel(new ImageIcon("Resources/levels/startscreen.jpg"));

        background.setLayout(null);

        setContentPane(background);
        background.setLayout(new FlowLayout());
        pack();
        setLocationRelativeTo(null);

        setVisible(true);

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * This method 'clicks' a button
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //Level one button clicked
        if((e.getX() >= 163 && e.getX() <= 291) && (e.getY() >= 324 && e.getY() <= 369)){
            //game.restartLevel(Optional.of(1));
            frame.restart(1);
            frame.showFrame();
            dispose();
            //createNewLevel(levelUserClicked);
        }
        //level two button clicked
        if((e.getX() >= 331 && e.getX() <= 460) && (e.getY() >= 324 && e.getY() <= 370)){
            //game.restartLevel(Optional.of(2));
            frame.restart(2);
            frame.showFrame();
            dispose();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Close the current frame with the starting screen on it.
     * Then, it creates a whole new level with the correct level
     */
    public void createNewLevel(int l){
        dispose();
        //creating a new game
        game.setup(l);
//        game.tester();
    }

}
