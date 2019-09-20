package Render;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
        setVisible(true);
    }

    @Override
    public void repaint() {
        super.repaint();
    }
}
