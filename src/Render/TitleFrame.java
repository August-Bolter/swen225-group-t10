package Render;

import Application.Main;
import Maze.LevelBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TitleFrame extends JFrame implements MouseListener {

    private static Main game;
    private JLabel background;
    //private int level;

    /**
     * Constructor for Title Frame
     * the Main game that is currenltly being used
     */
    public TitleFrame() {
        addMouseListener(this);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        background = new JLabel(new ImageIcon("Resources/levels/startscreen.jpg"));

        background.setLayout(null);

        setContentPane(background);
        background.setLayout(new FlowLayout());
        pack();

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
        int levelUserClicked;
        if((e.getX() >= 163 && e.getX() <= 291) && (e.getY() >= 324 && e.getY() <= 369)){
            System.out.println("YOU HAVE CLICKED BUTTON 1");
            new Main(1);
            dispose();
            //createNewLevel(levelUserClicked);
        }
        //level two button clicked
        if((e.getX() >= 331 && e.getX() <= 460) && (e.getY() >= 324 && e.getY() <= 370)){
            levelUserClicked = 2;
            System.out.println("YOU HAVE CLICKED BUTTON 2");
            createNewLevel(levelUserClicked);
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
