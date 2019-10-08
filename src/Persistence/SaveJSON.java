package Persistence;

import Maze.*;

import java.io.*;
import java.nio.Buffer;

/**
 * Class responsible for saving and loading the game
 */
public class SaveJSON {

    /**
     * Saves the current game state to a .JSON file
     */
    public static void SaveGame(LevelBoard levelBoard, String fileName, boolean endFile) {
        Tile[][] levelArray = levelBoard.getBoard();
        File jsonFile;
        jsonFile = new File(fileName);

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
            if (endFile) {
                builder.append("\n\t]\n}");
            }
            else {
                builder.append("\n\t],\n");
            }
            out.print(builder.toString());

            out.close();

        } catch (FileNotFoundException e) {
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

    public static void SaveMove(String fileName, LevelBoard.Direction direction, int tick, boolean firstMove) {
        try {
            StringBuilder move = new StringBuilder();
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter out = new BufferedWriter(fileWriter);
            if (firstMove) {
                move.append("\t\"moves\" : [\n");
            }
            String type = "playerMove";
            String directionText = "";
            if (LevelBoard.Direction.DOWN == direction) {
                directionText = "DOWN";
            }
            else if (LevelBoard.Direction.UP == direction) {
                directionText = "UP";
            }
            else if (LevelBoard.Direction.RIGHT == direction) {
                directionText = "RIGHT";
            }
            else {
                directionText = "LEFT";
            }
            if (!firstMove) {
                move.append(",\n");
            }
            move.append("\t\t{\n\t\t\t\"type\" : \""+ type +"\",\n" +
                    "\t\t\t\"direction\" : \"" + directionText + "\",\n" +
                    "\t\t\t\"tick\" : " + tick);

            move.append("\n\t\t}");
            out.append(move.toString());
            out.close();
            fileWriter.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void endRecord(String fileName) {
        try {
            StringBuilder move = new StringBuilder();
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter out = new BufferedWriter(fileWriter);
            move.append("\n\t]\n}");
            out.append(move.toString());
            out.close();
            fileWriter.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
