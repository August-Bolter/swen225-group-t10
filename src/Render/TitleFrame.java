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

public class TitleFrame extends JFrame implements KeyListener, MouseListener {

    private Main game;
    private BoardPanel boardpanel;
    private InfoPanel infoPanel;
    private Set<Integer> pressedKeys;
    private JMenuItem quit;
    private JLabel background;
    private LevelBoard level;
    private int row = 1;

    public TitleFrame(Main game){
        this.game = game;
       // setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Makes the program have exit dialogue instead
        addKeyListener(this);
        addMouseListener(this);


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
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        //leave empty
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode){
            case KeyEvent.VK_UP:
                System.out.println("up");
                if(!(row <= 1)){
                    row--;
                }
                System.out.println("Row Num:   " + row );
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("down");
                if(!(row >= 3)){
                    row++;
                }
                System.out.println("Row Num:   " + row );
                break;
            case KeyEvent.VK_ENTER:
                System.out.println("PRESSED ENTER");
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
    //leave empty
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

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
