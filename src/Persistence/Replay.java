package Persistence;

import Application.Main;
import Render.InfoPanel;

public class Replay {
    InfoPanel infoPanel;
    Main game;
    public Replay(Main game, InfoPanel panel) {
        this.game = game;
        infoPanel = panel;
    }

    public void replay() {
        infoPanel.openFileChooser();
    }
}
