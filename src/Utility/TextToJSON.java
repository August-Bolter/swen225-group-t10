package Utility;


import java.io.*;

/**
 * Takes a text file representing a level of Chip's Challenge and converts it to a JSON file describing the level.
 * Level images are available from https://strategywiki.org/wiki/Chip%27s_Challenge/Levels_1-20#Level_1:_LESSON_1.
 * All levels are 32x32.
 */
class TextToJSON {
    private static final String TEXT_FILENAME = "Level-1.json";

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
            out.print("{\n\t\"rows\":{\n");

            int row = 0;
            String line;

            while (!(line = in.readLine()).equals(""))  {
                row++;

                String[] rowTiles = line.split(" ");

                if (rowTiles.length != 32)
                    throw new Error("Row " + row + " has " + rowTiles.length + " tiles when it should have exactly 32.");

                out.print("\t\t\"row\": [");

                for (int i = 0; i < rowTiles.length; i++)
                    out.print((i == rowTiles.length - 1) ? "\"" + rowTiles[i] + "\"" : "\"" + rowTiles[i] + "\",");

                out.print("],\n");
            }

            if (row != 32) {
                jsonFile.delete();
                throw new Error("Wrong number of rows " + TEXT_FILENAME + ". There are " + row + ", there should be exactly 32.");
            }



            // Write the end of the JSON file
            out.print("\t}\n}");

            in.close();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
