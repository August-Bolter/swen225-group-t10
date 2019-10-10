package Persistence;

import Application.Main;
import Maze.LevelBoard;
import Maze.Player;
import Render.ReplayPanel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Replay {
    ReplayPanel replayPanel;
    Main game;
    HashMap<Long, ArrayList<String>> tickToMovesMap;
    public Replay(Main game, ReplayPanel panel) {
        this.game = game;
        replayPanel = panel;
    }

    public void replay() {
        game.setFrameRate(0000000000000000.1); //Stopping the game
        game.setReplay(this);

        File selectedReplay = replayPanel.openFileChooser();
        LevelBoard replayBoard = LoadJSON.loadLevelFromJSON(0, selectedReplay);
        game.setLevelBoard(replayBoard);
        game.getLevelBoard().setMain(game);

        game.setChipsRemaining(replayBoard.getTotalChips());
        game.setTimeRemaining(replayBoard.getTimeLimit());

        // FIXME We shouldn't set the chips twice the UI should update itself
//        replayPanel.setChipsLeft(replayBoard.getTotalChips());
//        replayPanel.setTimeLeft(replayBoard.getTimeLimit());

        Player p = replayBoard.getPlayer();
        p.setCurrentPos();
        game.setPlayer(p);
//        replayPanel.getMainFrame().getBoardPanel().setPlayer(p);

//        replayPanel.getMainFrame().getBoardPanel().setBoard(replayBoard.getBoard());
//        infoPanel.getMainFrame().getBoardPanel().updateBoard();
//        replayPanel.getMainFrame().getBoardPanel().redraw();

//        replayPanel.setInventory(p.getInventory());

//        replayPanel.getMainFrame().removeKeyListener(replayPanel.getMainFrame());

        replayPanel.changeButtons();
//        replayPanel.addReplayButtons();
//        replayPanel.redraw();

        tickToMovesMap = LoadJSON.loadMoves(selectedReplay);
        game.getFrame().redraw();
//        infoPanel.getMainFrame().getBoardPanel().updateBoard();
    }

    public HashMap<Long, ArrayList<String>> getTickToMovesMap() {
        return tickToMovesMap;
    }
}
