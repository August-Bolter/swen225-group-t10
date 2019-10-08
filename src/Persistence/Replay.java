package Persistence;

import Application.Main;
import Maze.LevelBoard;
import Maze.Player;
import Render.InfoPanel;

import java.io.File;

public class Replay {
    InfoPanel infoPanel;
    Main game;
    public Replay(Main game, InfoPanel panel) {
        this.game = game;
        infoPanel = panel;
    }

    public void replay() {
        File selectedReplay = infoPanel.openFileChooser();
        LevelBoard replayBoard = LoadJSON.loadLevelFromJSON(0, selectedReplay);
        game.setLevelBoard(replayBoard);
        game.getLevelBoard().setMain(game);

        game.setChipsRemaining(replayBoard.getTotalChips());
        game.setTimeRemaining(replayBoard.getTimeLimit());

        infoPanel.setChipsLeft(replayBoard.getTotalChips());
        infoPanel.setTimeLeft(replayBoard.getTimeLimit());

        Player p = replayBoard.getPlayer();
        p.setCurrentPos();
        game.setPlayer(p);
        infoPanel.getMainFrame().getBoardPanel().setPlayer(p);

        infoPanel.getMainFrame().getBoardPanel().setBoard(replayBoard.getBoard());
        infoPanel.getMainFrame().getBoardPanel().updateBoard();
        infoPanel.getMainFrame().getBoardPanel().redraw();

        infoPanel.setInventory(p.getInventory());

        infoPanel.getMainFrame().removeKeyListener(infoPanel.getMainFrame());

        infoPanel.changeButtons();
        infoPanel.addReplayButtons();
        infoPanel.redraw();
    }
}
