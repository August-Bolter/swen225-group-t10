package Persistence;

import Maze.FreeTile;
import Maze.Tile;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class LoadJSON {

    public static Tile[][] loadLevelFromJSON(int level) {
        Tile[][] levelArray = new Tile[32][32];

        for (int i = 0; i < levelArray.length; i++)
            for (int j = 0; j < levelArray[i].length; j++)
                levelArray[i][j] = new FreeTile(i,j);

        try {
            BufferedReader in = new BufferedReader(new FileReader("src/Utility/Level-" + level + ".json"));

            // Read each of the tiles and overwrite the
            JsonReader reader = Json.createReader(in);

            JsonObject tiles = reader.readObject();
            System.out.println(tiles.get("tiles").getValueType());

        } catch (FileNotFoundException e) {
            throw new Error("Level-" + level + ".json not found");
        }

        return levelArray;
    }
}
