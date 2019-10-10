package Persistence;

import Application.Main;
import Maze.LevelBoard;
import Maze.Player;
import Render.InfoPanel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Replay {
    InfoPanel infoPanel;
    Main game;
    HashMap<Long, ArrayList<String>> tickToMovesMap;
    public Replay(Main game, InfoPanel panel) {
        this.game = game;
        infoPanel = panel;
    }

    public void replay() {
        game.setFrameRate(0000000000000000.1); //Stopping the game
        game.setReplay(this);

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

        tickToMovesMap = LoadJSON.loadMoves(selectedReplay);
        infoPanel.getMainFrame().getBoardPanel().redraw();
        infoPanel.getMainFrame().getBoardPanel().updateBoard();
    }

    public HashMap<Long, ArrayList<String>> getTickToMovesMap() {
        return tickToMovesMap;
    }
}
