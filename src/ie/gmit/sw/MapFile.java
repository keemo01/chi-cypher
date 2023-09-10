package ie.gmit.sw;

import java.io.*;
import java.util.HashMap;

public class MapFile {
    private final String filePath = "src/ie/gmit/sw/monograms-ASCII-32-127.txt";

    public HashMap<Character, Double> mapTheFile() {
        HashMap<Character, Double> mappedFile = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(",")) continue;
                String[] fragments = line.split(",");
                String theCharacters = fragments[0].trim();
                String value = fragments[1].trim();

                if (!theCharacters.isEmpty() && !value.isEmpty()) {
                    double probability = Double.parseDouble(value);
                    mappedFile.put(theCharacters.charAt(0), probability / 100d);
                }
            }
            mappedFile.put(',', 0.00323418 / 100d);
            mappedFile.put(' ', 0.00189169 / 100d);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mappedFile;
    }

    public String readFile(String filePath) throws FileNotFoundException {
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                fileContent.append(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent.toString();
    }
}
