package Utility;


import java.io.*;

/**
 * Takes a text file representing a level of Chip's Challenge and converts it to a JSON file describing the level.
 * Level images are available from https://strategywiki.org/wiki/Chip%27s_Challenge/Levels_1-20#Level_1:_LESSON_1.
 * All levels are 32x32.
 */
class TextToJSON {
    private static final String TEXT_FILENAME = "Level-1.txt";

    /**
     * Converts the text file to a JSON file
     * @param args no arguments
     */
    public static void main(String[] args) {
        if (!TEXT_FILENAME.matches("Level-[0-9]+\\.txt"))
            throw new Error("Incorrect format for filename " + TEXT_FILENAME);

        writeJSON();
    }

    private static void writeJSON() {
        File jsonFile = new File("src/Utility/" + TEXT_FILENAME.substring(0, TEXT_FILENAME.length() - 4) + ".json");

        try {
            BufferedReader in = new BufferedReader(new FileReader("src/Utility/" + TEXT_FILENAME));
            PrintStream out = new PrintStream(jsonFile);

            // Write the start of the JSON file
            out.print("{\n\t\"title\": \"Lesson 1\",\n" +
                    "\t\"chips\": 11,\n" +
                    "\t\"timeLimit\" : 100,\n" +
                    "\t\"tiles\" : [\n");

            int row = 0;
            String line;

            while ((line = in.readLine()) != null)  {

                char[] rowTiles = line.toCharArray();

                if (rowTiles.length != 32)
                    throw new Error("Row " + row + " has " + rowTiles.length + " tiles when it should have exactly 32.");

                for (int i = 0; i < rowTiles.length; i++) {
                    String type = "", extra = null;

                    if (rowTiles[i] == '_') {
                        continue;
                    } else if (rowTiles[i] == 'x') {
                        type = "WallTile";
                    } else if (rowTiles[i] == 'r') {
                        type = "Key";
                        extra = "red";
                    } else if (rowTiles[i] == 'g') {
                        type = "Key";
                        extra = "green";
                    } else if (rowTiles[i] == 'b') {
                        type = "Key";
                        extra = "blue";
                    } else if (rowTiles[i] == 'y') {
                        type = "Key";
                        extra = "yellow";
                    } else if (rowTiles[i] == 'e') {
                        type = "DoorTile";
                        extra = "blue";
                    } else if (rowTiles[i] == 'w') {
                        type = "DoorTile";
                        extra = "yellow";
                    } else if (rowTiles[i] == 'n') {
                        type = "DoorTile";
                        extra = "green";
                    } else if (rowTiles[i] == 'd') {
                        type = "DoorTile";
                        extra = "red";
                    } else {
                        System.out.println("Missing character " + rowTiles[i]);
                    }

                    out.print("\t{\n\t\t\"type\" : \""+ type +"\",\n" +
                            "\t\t\"row\" : " + row + ",\n" +
                            "\t\t\"col\" : " + i +
                            ((extra == null)?
                                    "\n\t},\n":
                                    ",\n\t\t\"extra\" : \"" + extra + "\"\n\t},\n"));
                }




                row++;
            }

           /* if (row != 32) {
                jsonFile.delete();
                throw new Error("Wrong number of rows " + TEXT_FILENAME + ". There are " + row + ", there should be exactly 32.");
            }



            // Write the end of the JSON file
            out.print("\t}\n}");

            in.close();
            out.close();*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
