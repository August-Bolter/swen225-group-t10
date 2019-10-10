package Render;

import Application.Main;
import Maze.LevelBoard;
import Persistence.Replay;
import Persistence.SaveJSON;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        setContentPane(outerpanel);
        addBoardPanel();
        addInfoPanel();

        // Setup key listener
        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        createMenuBar();

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
        JMenuItem gameMenu = new JMenuItem("Game");
        JMenuItem settingsMenu = new JMenuItem("Settings");

        menu.add(gameMenu);
        menu.add(settingsMenu);

        setJMenuBar(menu);
    }

    private void addBoardPanel() {
        boardpanel = new BoardPanel(game.getLevelBoard().getBoard(), game.getPlayer());
        outerpanel.add(boardpanel);
    }

    private void addInfoPanel() {
        infoPanel = new InfoPanel(this);
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
                    SaveJSON.SaveGame(game.getLevelBoard(), "src/Utility/save.json", true);
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
                    int confirmed = JOptionPane.showConfirmDialog(null, "Exit Program?","EXIT",JOptionPane.YES_NO_OPTION);
                    if(confirmed == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
                return;
            case KeyEvent.VK_R:
                // Resumes the game
                if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
                    System.out.println("CTRL R");
                }
                return;
            case KeyEvent.VK_P:
                // Starts a new game at level 1
                if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
                    System.out.println("CTRL P");
                }
                return;
            case KeyEvent.VK_1:
                // Starts level 1
                if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
                    System.out.println("CTRL 1");
                }
                return;
            case KeyEvent.VK_2:
                // Starts level 2
                if (pressedKeys.contains(KeyEvent.VK_CONTROL)) {
                    System.out.println("CTRL 2");
                }
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
    }

    public void redraw() {
        outerpanel.remove(boardpanel);
        outerpanel.remove(infoPanel);
        boardpanel = new BoardPanel(game.getLevelBoard().getBoard(), game.getPlayer());
        ReplayPanel copyOfReplay = infoPanel.getReplayPanel();
        infoPanel = new InfoPanel(this, copyOfReplay);

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

    public void createChangeSpeedWindow() {
        JDialog changeSpeedWindow = new JDialog();
        JPanel changeSpeedPanel = new JPanel();
        changeSpeedPanel.setLayout(new GridLayout(3, 1));
        JLabel info = new JLabel("Please select a replay speed");
        String[] speeds = {"0.25", "0.5", "1.0", "2.0", "4.0"};
        JComboBox<String> speedOptions = new JComboBox<String>(speeds);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submitButton) {
                    String selectedSpeedString = (String) speedOptions.getSelectedItem();
                    game.setReplaySpeed(Double.parseDouble(selectedSpeedString));
                    changeSpeedWindow.dispose();
                }
            }
        });
        changeSpeedPanel.add(info);
        changeSpeedPanel.add(speedOptions);
        changeSpeedPanel.add(submitButton);
        changeSpeedWindow.add(changeSpeedPanel);
        changeSpeedWindow.setVisible(true);
        changeSpeedWindow.pack();
    }

    public Main getGame() {
        return game;
    }
}
