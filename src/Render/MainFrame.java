package Render;

import Application.Main;
import Maze.LevelBoard;
import Persistence.SaveJSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The main frame that contains other panels.
 */
public class MainFrame extends JFrame implements KeyListener, WindowListener, ActionListener {
    private Main game;
    private JPanel outerpanel;
    private BoardPanel boardpanel;
    private InfoPanel infoPanel;
    private Set<Integer> pressedKeys;


    private JMenuItem quit;

    /**
     * Creates a new main frame.
     * @param game the game to represent
     */
    public MainFrame(Main game){
        super("Chip's Challenge");
        this.game = game;

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Makes the program have exit dialogue instead
        addWindowListener(this);

        outerpanel = new JPanel();

//        setMinimumSize(new Dimension(2000, 800));

        setContentPane(outerpanel);
        addBoardPanel();
        addInfoPanel();
//        redraw();
        // Setup key listener
        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        createMenuBar();
//        outerpanel.setLayout(new BoxLayout(outerpanel, BoxLayout.X_AXIS));
        outerpanel.setLayout(new GridBagLayout());
        outerpanel.setBorder(new GameBorder(Color.BLUE));
        pack();
        setLocationRelativeTo(null);
        repaint();
        setResizable(false);
        setVisible(true);
        pressedKeys = new HashSet<>();
    }

    private void createMenuBar() {
        JMenuBar menu = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenuItem restart = new JMenuItem("Restart");
        quit = new JMenuItem("Quit");
        quit.addActionListener(this);

        JMenuItem settingsMenu = new JMenuItem("Settings");

        menu.add(gameMenu);
        gameMenu.add(settingsMenu);
        gameMenu.add(restart);
        gameMenu.add(quit);

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
                    save();
                    return;
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
                    quit();
                }
                return;
            case KeyEvent.VK_R:
                // Resumes the game
                if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
                    resume();
                }
                return;
            case KeyEvent.VK_P:
                // Pauses the game
                if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
                    pause();
                }
                return;
            case KeyEvent.VK_1:
                // Starts level 1
                if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
                    restart(1);
                }
                return;
            case KeyEvent.VK_2:
                // Starts level 2
                if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
                    restart(2);
                }
                return;
            case KeyEvent.VK_SPACE:
                // Pauses the game
                pause();
                return;
            case KeyEvent.VK_ESCAPE:
                // Closes the pause dialog
                resume();
                return;

            default:
                return;
        }

        game.doMove(direction);
    }

    /**
     * Redraws the main frame.
     */
    public void redraw() {
        outerpanel.remove(boardpanel);
        outerpanel.remove(infoPanel);
        boardpanel = new BoardPanel(game.getLevelBoard().getBoard(), game.getPlayer());
        infoPanel = new InfoPanel(game);

        outerpanel.add(boardpanel);
        outerpanel.add(infoPanel);

        revalidate();
        repaint();
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

    /**
     * Saves the game.
     */
    public void save() {
        SaveJSON.SaveGame(game.getLevelBoard());
    }

    /**
     * Quits the game.
     */
    public void quit() {
        int confirmed = JOptionPane.showConfirmDialog(null, "Exit Program?","EXIT",JOptionPane.YES_NO_OPTION);
        if(confirmed == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * Resume the game.
     */
    public void resume() {
        System.out.println("Resume");
    }

    /**
     * Resume a given level
     * @param level the number of the level to restart
     */
    public void restart(int level) {
        System.out.println("CTRL "+ level);
    }

    /**
     * Pauses the game.
     */
    public void pause() {
        System.out.println("PAUSE");
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        quit();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(quit)) {
            quit();
        }
    }
}
