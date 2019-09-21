package Render;

import Application.Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Main game;
    private JPanel outerpanel;
    private BoardPanel boardpanel;

    public MainFrame(Main game){
        super("Chip's Challenge");
        this.game = game;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        outerpanel = new JPanel();
        outerpanel.setLayout(new GridLayout(1,1));

        setContentPane(outerpanel);
        addBoardPanel();

//        Tile t = boardpanel.board[0][0];
//        outerpanel.add(new JLabel(new ImageIcon(t.getImage())));
        pack();
        setVisible(true);
        repaint();
    }

    private void addBoardPanel(){
        boardpanel = new BoardPanel(game.getLevelBoard().getBoard());
        outerpanel.add(boardpanel);
    }

//    /**
//     * It will go into persistence and by getting the name of the tile.
//     * This will get passed to the board which will store it in a map for fast recovery
//     * @param name name of the tile you want an image for
//     * @return Image of the tile that you need for the board
//     */
//    public Image getImage(String name) {
//        return null; // FIXME
//    }
}
