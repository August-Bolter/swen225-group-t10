package Render;

import Application.Main;
import Maze.LevelBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

public class TitleFrame extends JFrame implements MouseListener {

    private static Main game;
    private BoardPanel boardpanel;
    private InfoPanel infoPanel;
    private Set<Integer> pressedKeys;
    private JMenuItem quit;
    private JLabel background;
    private LevelBoard level;
    private int row = 1;

    public TitleFrame(Main game) {
        this.game = game;
        // setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Makes the program have exit dialogue instead
        addKeyListener(this);
        addMouseListener(this);
        setResizable(false);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JLabel
        background = new JLabel(new ImageIcon("Resources/levels/startscreen.jpg"));

        background.setLayout(null);

        setContentPane(background);
        background.setLayout(new FlowLayout());
        pack();

        setVisible(true);

    }

    /**
     * Should redraw the little pokeball based on whether you've pressed up or down
     */
    public void redraw(){

    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        //LEVEL ONE CLICKED
        if((e.getX() >= 163 && e.getX() <= 291) && (e.getY() >= 324 && e.getY() <= 369)){
            System.out.println("YOU HAVE CLICKED BUTTON 1");
        }
        if((e.getX() >= 331 && e.getX() <= 460) && (e.getY() >= 324 && e.getY() <= 370)){
            System.out.println("YOU HAVE CLICKED BUTTON 2");
        }

    }


    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new TitleFrame(game);
    }
}
