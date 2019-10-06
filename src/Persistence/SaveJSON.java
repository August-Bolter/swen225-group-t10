package Persistence;

import Maze.*;

import java.io.*;

/**
 * Class responsible for saving and loading the game
 */
public class SaveJSON {

    /**
     * Saves the current game state to a .JSON file
     */
    public static void SaveGame(LevelBoard levelBoard, int count) {
        Tile[][] levelArray = levelBoard.getBoard();
        File jsonFile;
        if (count == -1) {
            jsonFile = new File("src/Utility/save.json");
        }
        else {
            jsonFile = new File("src/Utility/replay-" + count + ".json");
        }

        try {
            PrintStream out = new PrintStream(jsonFile);
            StringBuilder builder = new StringBuilder();

            // Write the start of the JSON file
            builder.append("{\n\t\"title\": \"Lesson 1\",\n" +
                    "\t\"chips\": " + levelBoard.getTotalChips() + ",\n" +
                    "\t\"timeLimit\" : " + levelBoard.getTimeLimit() + ",\n" +
                    "\t\"tiles\" : [\n");

            for (int row = 0; row < levelArray.length; row++) {
                for (int col = 0; col < levelArray[row].length; col++) {
                    // Get tile descriptions
                    Tile tile = levelArray[row][col];
                    Class<?> clazz = tile.getClass();
                    String type = clazz.getName().split("\\.")[1];
                    String extra = tile.getExtra();

                    // Get item description if the tile has one
                    Item itemObj;
                    String item, itemExtra;
                    if (tile.getItems().isEmpty()) {
                        itemObj = null;
                        item = null;
                    } else {
                        itemObj = tile.getItems().get(0);
                        item = itemObj.getClass().getName().split("\\.")[1];
                    }

                    if (itemObj == null) {
                        itemExtra = null;
                    } else if (itemObj instanceof Player) {
                        itemExtra = "";
                        Player player = (Player) itemObj;
                        for (int i = 0; i < player.getInventory().length; i++) {
                            if (player.getInventory()[i] == null) {
                                itemExtra += "_,";
                            } else {
                                itemExtra += player.getInventory()[i].getClass().getName().split("\\.")[1] + "|" + ((player.getInventory()[i].getExtra() == null) ? "_" :  player.getInventory()[i].getExtra()) + ",";
                            }
                        }
                        itemExtra = itemExtra.substring(0, itemExtra.length() - 1);
                        System.out.println(itemExtra);

                    } else {
                        itemExtra = itemObj.getExtra();
                    }

                    // Print to JSON
                    builder.append(tileAsJSON(type, extra, item, itemExtra, row, col));

                }
            }


            // Write the end of the JSON file
            builder.setLength(builder.length() - 2);
            builder.append("\n\t]\n}");
            out.print(builder.toString());

            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return A tile's information as a JSON object string
     */
    public static String tileAsJSON(String type, String extra, String item, String itemExtra, int row, int col) {
        StringBuilder builder = new StringBuilder();

        builder.append("\t\t{\n\t\t\t\"type\" : \""+ type +"\",\n" +
                "\t\t\t\"row\" : " + row + ",\n" +
                "\t\t\t\"col\" : " + col);

        if (extra != null) {
            builder.append(",\n\t\t\t\"extra\" : \"" + extra + "\"");
        }

        if (item != null) {
            builder.append(",\n\t\t\t\"item\" : {\n\t\t\t\t\"type\" : \"" + item + "\"");

            if (itemExtra != null) {
                builder.append(",\n\t\t\t\t\"extra\" : \"" + itemExtra + "\"" );
            }

            builder.append("\n\t\t\t}");
        }

        builder.append("\n\t\t},\n");

        return builder.toString();
    }
}
