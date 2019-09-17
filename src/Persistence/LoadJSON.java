package Persistence;

import Maze.FreeTile;
import Maze.Item;
import Maze.LevelBoard;
import Maze.Tile;

import javax.json.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Class for loading information from JSON files
 */
public class LoadJSON {

    /**
     * Creates a new LevelBoard from information in a JSON file
     * @param level the level number to load
     * @return a new LevelBoard representing that level
     */
    public static LevelBoard loadLevelFromJSON(int level) {
        Tile[][] levelArray = new Tile[32][32];
        String title;
        int chips, timeLimit;

        // Initialise the levelArray with empty tiles
        for (int i = 0; i < levelArray.length; i++)
            for (int j = 0; j < levelArray[i].length; j++)
                levelArray[i][j] = new FreeTile(i,j);

        try {
            // Open the JSON file
            BufferedReader in = new BufferedReader(new FileReader("src/Utility/Level-" + level + ".json"));
            JsonReader reader = Json.createReader(in);

            JsonObject levelObject = reader.readObject();

            // Get the header information
            title = levelObject.getString("title");
            chips = levelObject.getInt("chips");
            timeLimit = levelObject.getInt("timeLimit");

            // Read each tile object and update the levelArray
            JsonArray tilesArray = levelObject.getJsonArray("tiles");

            for (JsonValue tileValue : tilesArray) {
                JsonObject tileObject = tileValue.asJsonObject();

                int row = tileObject.getInt("row");
                int col = tileObject.getInt("col");
                String tileClassName = tileObject.getString("type");
                String itemClassName = tileObject.getString("item");

                try {
                    Class<?> clazz = Class.forName("Maze."+tileClassName);
                    Tile tile;
                    Constructor<?> constructor;

                    if (itemClassName.equals("null")) {
                        constructor = clazz.getConstructor(Integer.TYPE, Integer.TYPE);
                        tile = (Tile) constructor.newInstance(row,col);
                    } else {
                        Class<?> itemClazz = Class.forName("Maze."+itemClassName);
                        Constructor<?> itemConstructor = itemClazz.getConstructor(Integer.TYPE, Integer.TYPE);
                        Item item = (Item) itemConstructor.newInstance(row, col);

                        constructor = clazz.getConstructor(Integer.TYPE, Integer.TYPE, Item.class);
                        tile = (Tile) constructor.newInstance(row, col, item);
                    }

                    levelArray[row][col] = tile;

                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            throw new Error("Level-" + level + ".json not found");
        }

        return new LevelBoard(title, chips, timeLimit, levelArray);
    }
}
