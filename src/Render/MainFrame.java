package Render;

import Application.Main;
import Maze.LevelBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame implements KeyListener {
    private Main game;
    private JPanel outerpanel;
    private BoardPanel boardpanel;
    private InfoPanel infoPanel;

    public MainFrame(Main game){
        super("Chip's Challenge");
        this.game = game;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLayout(new GridLayout(1,1)); // FIXME currently only 1x1
        outerpanel = new JPanel();
        outerpanel.setLayout(new GridLayout(1,2));

        setContentPane(outerpanel);
        addBoardPanel();
        addInfoPanel();

        // Setup key listener
        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        pack();
        setVisible(true);
        repaint();
    }

    private void addBoardPanel() {
        boardpanel = new BoardPanel(game.getLevelBoard().getBoard(), game.getPlayer());
        outerpanel.add(boardpanel);
    }

    private void addInfoPanel() {
        infoPanel = new InfoPanel(game);
        outerpanel.add(infoPanel);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        // Unimplemented
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        LevelBoard.Direction direction;

        switch (keyCode) {
            case KeyEvent.VK_W:
                direction = LevelBoard.Direction.UP;
                break;
            case KeyEvent.VK_S:
                direction = LevelBoard.Direction.DOWN;
                break;
            case KeyEvent.VK_A:
                direction = LevelBoard.Direction.LEFT;
                break;
            case KeyEvent.VK_D:
                direction = LevelBoard.Direction.RIGHT;
                break;
            default:
                return;
        }

        game.doMove(direction);
        boardpanel.redraw(); // TODO should be in the game loop
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        // Unimplemented
    }

//    /**
//     * It will go into persistence and by getting the name of the tile.
//     * This will get passed to the board which will store it in a map for fast recovery
//     * @param name name of the tile you want an image for
//     * @return Image of the tile that you need for the board
//     */
//    public Image getImage(String name) {
//        return null;
//    }
}
