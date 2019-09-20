package Render;

import Application.Main;

import javax.swing.*;

public class MainFrame extends JFrame {
    private Main game;

    public MainFrame(Main game){
        this.game = game;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addBoardPanel();


        pack();
        setVisible(true);
    }

    public void addBoardPanel(){
        BoardPanel boardpanel = new BoardPanel(game.getLevelBoard().getBoard());

    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
