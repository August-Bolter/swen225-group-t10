package Render;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    /**
     * Info panel needs:
     * Level label + level number
     * Time + time remaining
     * Chips left
     * Inventory (an array with 8 pos) so it'll be 8 labels that can have
     * an image drawn over them
     */
    public InfoPanel() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


    }

    //Maybe could implement the info panel with something like this
    //public static void showHints(String info) {
    //    Create JPanel
    //    With JLabel(info)
    // }


    @Override
    public void repaint() {
        super.repaint();
    }
}
