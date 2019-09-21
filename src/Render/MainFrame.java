package Render;

import Application.Main;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Main game;

    public MainFrame(Main game){
        this.game = game;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        addBoardPanel();

        repaint();
    }

    public void addBoardPanel(){
        BoardPanel boardpanel = new BoardPanel(game.getLevelBoard().getBoard());
        add(boardpanel, BorderLayout.NORTH);

        repaint();
    }

    /**
     * It will go into persistence and by getting the name of the tile.
     * This will get passed to the board which will store it in a map for fast recovery
     * @param name name of the tile you want an image for
     * @return Image of the tile that you need for the board
     */
    public Image getImage(String name) {
        return null; // FIXME
    }

    @Override
    public void repaint() {
        super.repaint();

        pack();
        setVisible(true);
    }
}
