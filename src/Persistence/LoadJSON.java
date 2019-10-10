package Persistence;

import Maze.*;

import javax.json.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
            BufferedReader in;
            if (level > 0) {
                in = new BufferedReader(new FileReader("src/Utility/Level-" + level + ".json"));
            } else {
                in = new BufferedReader(new FileReader("src/Utility/save.json"));
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
                List<Item> items = null;
                String extra = null, itemExtra = null;

                if (tileObject.containsKey("item")) {
                    JsonObject itemObject = tileObject.get("item").asJsonObject();
                    String descriptor= itemObject.getString("descriptor");
                    items = createItems(descriptor, row, col);
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
                    if (items != null)
                        tile.addAllItems(items);

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

    private static List<Item> createItems(String descriptor, int row, int col) {
        List<Item> items = new ArrayList<>();
        String[] descriptions = descriptor.split(",");

        for (int i = 0; i < descriptions.length; i++) {
            String[] itemDescription = descriptions[i].split("\\|");

            String itemClassName = itemDescription[0];
            String extra = ((itemDescription[1].equals("_")) ? null : itemDescription[1]);

            items.add(createItem(itemClassName, row, col, extra));
        }

        return items;
    }


    private static Item createItem(String itemClassName, int row, int col, String extra) {
        Item item = null;
        try {
            Class<?> itemClazz = Class.forName("Maze."+itemClassName);
            if (extra == null || extra.equals("null")) {
                Constructor<?> itemConstructor = itemClazz.getConstructor(Integer.TYPE, Integer.TYPE);
                item = (Item) itemConstructor.newInstance(row, col);
            } else if (itemClassName.equals("Player")) {
                String[] itemNames = extra.split(">");
                Constructor<?> itemConstructor = itemClazz.getConstructor(Integer.TYPE, Integer.TYPE);
                item = (Item) itemConstructor.newInstance(row, col);
                Player player = (Player) item;
                for (int i = 0; i < itemNames.length; i++) {
                    String[] itemInfo = itemNames[i].split("/");
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

}
