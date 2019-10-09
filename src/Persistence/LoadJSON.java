package Persistence;

import Maze.*;

import javax.json.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
        String title = null;
        int chips = 0, timeLimit = 0;

        // Initialise the levelArray with empty tiles
        for (int i = 0; i < levelArray.length; i++)
            for (int j = 0; j < levelArray[i].length; j++)
                levelArray[i][j] = new FreeTile(i,j);


        try {
            // Open the JSON file
            BufferedReader in;
            if (level > 0) {
                if (level == 1)
                    in = new BufferedReader(new FileReader("src/Utility/Level-" + level + ".json"));
                else {
                    readFilesInZip(level);
                    in = new BufferedReader(new FileReader("src/Utility/Level-" + level + "/Level-" + level + ".json"));
                }
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
                String extra = null;

                if (tileObject.containsKey("item")) {
                    JsonObject itemObject = tileObject.get("item").asJsonObject();
                    String descriptor= itemObject.getString("descriptor");


                    items = createItems(descriptor, row, col, level);
                }

                if (tileObject.containsKey("extra"))
                    extra = tileObject.getString("extra");

                try {
                    Class<?> clazz;
                    try {
                        clazz = Class.forName("Maze." + tileClassName);
                    } catch (ClassNotFoundException e) {
                        clazz = loadClassFromZip(tileClassName, level);
                    }
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
                }
            }

        } catch (FileNotFoundException e) {
            throw new Error("Level-" + level + ".json not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new LevelBoard(title, chips, timeLimit, levelArray);
    }

    private static List<Item> createItems(String descriptor, int row, int col, int level) {
        List<Item> items = new ArrayList<>();
        String[] descriptions = descriptor.split(",");

        for (int i = 0; i < descriptions.length; i++) {
            String[] itemDescription = descriptions[i].split("\\|");

            String itemClassName = itemDescription[0];
            String extra = ((itemDescription[1].equals("_")) ? null : itemDescription[1]);

            items.add(createItem(itemClassName, row, col, extra, level));
        }

        return items;
    }


    private static Item createItem(String itemClassName, int row, int col, String extra, int level) {
        Item item = null;
        try {
            Class<?> itemClazz;
            try {
                itemClazz = Class.forName("Maze." + itemClassName);
            } catch (ClassNotFoundException e) {
                itemClazz = loadClassFromZip(itemClassName, level);
            }
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
                        player.getInventory()[i] = createItem(itemInfo[0], row, col, (itemInfo[1].equals("_")) ? null : itemInfo[1], level);
                    }
                }
            } else {
                Constructor<?> itemConstructor = itemClazz.getConstructor(Integer.TYPE, Integer.TYPE, String.class);
                item = (Item) itemConstructor.newInstance(row, col, extra);
            }

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

    private static void readFilesInZip(int level) {
        try {
            ZipFile zipFile = new ZipFile("src/Utility/Level-" + level + ".zip");
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            new File("src/Utility/Level-"+level).mkdirs();
            new File("src/Utility/Level-" + level + "/Resources").mkdirs();
            new File("src/Utility/Level-" + level + "/Classes").mkdirs();

            while(entries.hasMoreElements()){
                ZipEntry entry = entries.nextElement();
                InputStream stream = zipFile.getInputStream(entry);
                if (entry.getName().endsWith(".png")) {
                    Files.copy(stream, Paths.get("src/Utility/Level-" + level + "/Resources/" + entry.getName().split("/")[1]), StandardCopyOption.REPLACE_EXISTING);
                } else if (entry.getName().endsWith(".class")){
                    Files.copy(stream, Paths.get("src/Utility/Level-" + level + "/Classes/" + entry.getName().split("/")[1]), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.copy(stream, Paths.get("src/Utility/Level-" + level + "/" + entry.getName().split("/")[1]), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Class loadClassFromZip(String className, int level) {
        try {
            // Load the class file from the new folder
            URL classURL = new File("src/Utility/Level-" + level + "/Classes").toURI().toURL();
            URL[] classURLs = {classURL};
            URLClassLoader classLoader = new URLClassLoader(classURLs);
            Class clazz = classLoader.loadClass(className);

            return clazz;

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        throw new Error("Couldn't load class " + className);
    }

}
