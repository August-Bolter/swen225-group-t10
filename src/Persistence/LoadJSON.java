package Persistence;

import Maze.*;

import javax.json.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for loading information from JSON files
 */
public class LoadJSON {

    /**
     * Creates a new LevelBoard from information in a JSON file
     * @param level the level number to load
     * @param selectedReplay
     * @return a new LevelBoard representing that level
     */
    public static LevelBoard loadLevelFromJSON(int level, File selectedReplay) {
        Tile[][] levelArray = new Tile[32][32];
        String title;
        int chips, timeLimit;

        // Initialise the levelArray with empty tiles
        for (int i = 0; i < levelArray.length; i++)
            for (int j = 0; j < levelArray[i].length; j++)
                levelArray[i][j] = new FreeTile(i,j);

        try {
            // Open the JSON file
            BufferedReader in;
            if (selectedReplay == null) {
                if (level > 0) {
                    in = new BufferedReader(new FileReader("src/Utility/Level-" + level + ".json"));
                } else {
                    in = new BufferedReader(new FileReader("src/Utility/save.json"));
                }
            }
            else {
                in = new BufferedReader(new FileReader(selectedReplay));
            }
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

                // Compulsory parameters for every tile
                String tileClassName = tileObject.getString("type");
                int row = tileObject.getInt("row");
                int col = tileObject.getInt("col");

                // Optional parameters
                Item item = null;
                String extra = null, itemExtra = null;

                if (tileObject.containsKey("item")) {
                    JsonObject itemObject = tileObject.get("item").asJsonObject();
                    String itemClassName = itemObject.getString("type");

                    if (itemObject.containsKey("extra")) {
                        itemExtra = itemObject.getString("extra");
                    }

                    item = createItem(itemClassName, row, col, itemExtra);
                }

                if (tileObject.containsKey("extra"))
                    extra = tileObject.getString("extra");

                try {
                    Class<?> clazz = Class.forName("Maze." + tileClassName);
                    Tile tile;
                    Constructor<?> constructor;

                    // Create tile
                    if (extra == null) {
                        constructor = clazz.getConstructor(Integer.TYPE, Integer.TYPE);
                        tile = (Tile) constructor.newInstance(row, col);
                    } else {
                        constructor = clazz.getConstructor(Integer.TYPE, Integer.TYPE, String.class);
                        tile = (Tile) constructor.newInstance(row, col, extra);
                    }

                    // Add item if necessary
                    if (item != null)
                        tile.addItem(item);

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


    private static Item createItem(String itemClassName, int row, int col, String extra) {
        Item item = null;
        try {
            Class<?> itemClazz = Class.forName("Maze."+itemClassName);

            if (extra == null) {
                Constructor<?> itemConstructor = itemClazz.getConstructor(Integer.TYPE, Integer.TYPE);
                item = (Item) itemConstructor.newInstance(row, col);
            } else if (itemClassName.equals("Player")) {
                String[] itemNames = extra.split(",");
                Constructor<?> itemConstructor = itemClazz.getConstructor(Integer.TYPE, Integer.TYPE);
                item = (Item) itemConstructor.newInstance(row, col);
                Player player = (Player) item;
                for (int i = 0; i < itemNames.length; i++) {
                    String[] itemInfo = itemNames[i].split("\\|");
                    if (itemInfo[0].equals("_")) {
                        player.getInventory()[i] = null;
                    } else {
                        player.getInventory()[i] = createItem(itemInfo[0], row, col, (itemInfo[1].equals("_")) ? null : itemInfo[1]);
                    }
                }
            } else {
                Constructor<?> itemConstructor = itemClazz.getConstructor(Integer.TYPE, Integer.TYPE, String.class);
                item = (Item) itemConstructor.newInstance(row, col, extra);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("Trying to create " + itemClassName + ", " + row + ", " + col);
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return item;
    }

    public static HashMap<Long, ArrayList<String>> loadMoves(File selectedReplay) {
        HashMap<Long, ArrayList<String>> tickAndMoves = new HashMap<Long, ArrayList<String>>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(selectedReplay));
            JsonReader reader = Json.createReader(in);
            JsonObject movesObject = reader.readObject();
            JsonArray movesArray = movesObject.getJsonArray("moves");
            for (JsonValue move : movesArray) {
                JsonObject moveObject = move.asJsonObject();
                //String moveType = moveObject.getString("type");
                String direction = moveObject.getString("direction");
                long time = moveObject.getJsonNumber("time").bigDecimalValue().longValueExact();
                ArrayList<String> currentDirections = tickAndMoves.get(time);
                if (currentDirections == null) {
                    currentDirections = new ArrayList<String>();
                    tickAndMoves.put(time, currentDirections);
                }
                currentDirections.add(direction);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return tickAndMoves;
    }
}
