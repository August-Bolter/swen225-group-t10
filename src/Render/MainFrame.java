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
        setLayout(new FlowLayout());
        addBoardPanel();

        repaint();
    }

    public void addBoardPanel(){
        BoardPanel boardpanel = new BoardPanel(game.getLevelBoard().getBoard());
        add(boardpanel, FlowLayout.CENTER);

        repaint();
    }

    @Override
    public void repaint() {
        super.repaint();

        pack();
        setVisible(true);
    }
}
