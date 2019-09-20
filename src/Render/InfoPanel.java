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


    @Override
    public void repaint() {
        super.repaint();
    }
}
