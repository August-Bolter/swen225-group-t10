package Persistence;

import Application.Main;
import Maze.LevelBoard;
import Maze.Player;
import Render.ReplayPanel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author August Bolter
 * Class to handle replaying recordings.
 */
public class Replay {
    ReplayPanel replayPanel;
    Main game;
    HashMap<Long, ArrayList<String>> tickToMovesMap;

    /**
     * Replays a given game
     * @param game the game to replay
     * @param panel the panel to use
     */
    public Replay(Main game, ReplayPanel panel) {
        this.game = game;
        replayPanel = panel;
    }

    /**
     * Method to actually replay the game
     */
    public void replay() {
        game.setFrameRate(0000000000000000.1); //Stopping the game
        game.setReplay(this);

        File selectedReplay = replayPanel.openFileChooser();
        LevelBoard replayBoard = LoadJSON.loadLevelFromJSON(game.getLevel(), selectedReplay);
        game.setLevelBoard(replayBoard);
        game.getLevelBoard().setMain(game);

        game.setChipsRemaining(replayBoard.getTotalChips());
        game.setTimeRemaining(replayBoard.getTimeLimit());

        Player p = replayBoard.getPlayer();
        p.setCurrentPos();
        game.setPlayer(p);

        game.getFrame().removeKeyListener(game.getFrame());

        replayPanel.changeButtons();
        replayPanel.addReplayButtons();
        if (!game.getFirstMove()) {
            tickToMovesMap = LoadJSON.loadMoves(selectedReplay);
        }
        game.getFrame().redraw();

    }

    /**
     * @return the map of ticks to moves
     */
    public HashMap<Long, ArrayList<String>> getTickToMovesMap() {
        return tickToMovesMap;
    }
}
