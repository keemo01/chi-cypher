package ie.gmit.sw;

import java.util.Map;
import java.util.concurrent.Callable;

public class ChiSquared implements Callable<Double> {
    private String cryptedFile;
    private int key;
    private Map<Character, Integer> occurrences;
    private Map<Character, Double> expected;

    public ChiSquared(int key, String cryptedFile, Map<Character, Double> expected,
            Map<Character, Integer> occurrences) {
        this.key = key;
        this.cryptedFile = cryptedFile;
        this.expected = expected;
        this.occurrences = occurrences;
    }

    @Override
    public Double call() throws Exception {
        double result = 0.0;

        for (Character c : occurrences.keySet()) {
            double expectedValue = expected.get(c);
            double occurrenceValue = occurrences.get(c);
            double calculatedValue = expectedValue * cryptedFile.length();
            double total = Math.pow(occurrenceValue - calculatedValue, 2) / calculatedValue;
            result += total;
        }

        return result;
    }
}
