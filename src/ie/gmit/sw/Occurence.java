package ie.gmit.sw;

import java.util.HashMap;

public class Occurence {
    public HashMap<Character, Integer> characterCount(String inputString) {
        HashMap<Character, Integer> charCountMap = new HashMap<>();

        for (char c : inputString.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        return charCountMap;
    }
}
