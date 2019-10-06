package Render;

import Application.Main;
import Maze.LevelBoard;
import Persistence.SaveJSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class MainFrame extends JFrame implements KeyListener {
    private Main game;
    private JPanel outerpanel;
    private BoardPanel boardpanel;
    private InfoPanel infoPanel;
    private Set<Integer> pressedKeys;

    public MainFrame(Main game){
        super("Chip's Challenge");
        this.game = game;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        outerpanel = new JPanel();
        outerpanel.setLayout(new GridBagLayout());

        setContentPane(outerpanel);
        addBoardPanel();
        addInfoPanel();

        // Setup key listener
        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        createMenuBar();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        repaint();
        setResizable(false);

        pressedKeys = new HashSet<>();
    }

    private void createMenuBar() {
        JMenuBar menu = new JMenuBar();
        JMenuItem settingsMenu = new JMenuItem("Settings");

        menu.add(settingsMenu);
//        menu.add();

        setJMenuBar(menu);
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
        pressedKeys.add(keyCode);
        LevelBoard.Direction direction;

        switch (keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                direction = LevelBoard.Direction.UP;
                break;
            case KeyEvent.VK_S:
                if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
                    SaveJSON.SaveGame(game.getLevelBoard());
                }
            case KeyEvent.VK_DOWN:
                direction = LevelBoard.Direction.DOWN;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                direction = LevelBoard.Direction.LEFT;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                direction = LevelBoard.Direction.RIGHT;
                break;
            case KeyEvent.VK_X:
                if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
                    int confirmed = JOptionPane.showConfirmDialog(null, "Exit Program?","EXIT",JOptionPane.YES_NO_OPTION);
                    if(confirmed == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
                return;
            case KeyEvent.VK_R:
                // Resumes the game
                System.out.println("CTRL R");
                return;
            case KeyEvent.VK_P:
                // Starts a new game at level 1
                System.out.println("CTRL P");
                return;
            case KeyEvent.VK_1:
                // Starts level 1
                System.out.println("CTRL 1");
                return;
            case KeyEvent.VK_2:
                // Starts level 2
                System.out.println("CTRL 2");
                return;
            case KeyEvent.VK_SPACE:
                // Pauses the game
                System.out.println("SPACE");
                return;
            case KeyEvent.VK_ESCAPE:
                // Closes the pause dialog
                System.out.println("ESC");
                return;
            default:
                return;
        }

        game.doMove(direction);
        boardpanel.redraw();
        infoPanel.redraw();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        pressedKeys.remove(keyEvent.getKeyCode());
    }

    /**
     * Creates an info popup box
     * @param info the information to display
     */
    public void displayInfo(String info) {
        JOptionPane.showMessageDialog(this, info);
    }

    /**
     * @return the board panel
     */
    public BoardPanel getBoardPanel() {
        return boardpanel;
    }

    /**
     * @return the info panel
     */
    public InfoPanel getInfoPanel() {
        return infoPanel;
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
