package Persistence;

import Maze.Tile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LoadJSON {

    public Tile[][] loadLevelFromJSON(int level) {
        Tile[][] levelArray = new Tile[32][32];

        for (int i = 0; i < levelArray.length; i++)
            for (int j = 0; j < levelArray[i].length; j++)
                // levelArray[i][j] = default ground tile

        try {
            BufferedReader in = new BufferedReader(new FileReader("src/Utility/Level-" + level + ".json"));

            // Read each of the tiles and overwrite the
        } catch (FileNotFoundException e) {
            throw new Error("Level-" + level + ".json not found");
        }

        return levelArray;
    }
}
