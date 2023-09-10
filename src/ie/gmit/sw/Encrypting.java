package ie.gmit.sw;

public class Encrypting extends ChiCypher {
    private int key;
    private String sentence;

    public Encrypting(int key, String sentence) {
        this.key = key;
        this.sentence = sentence;
    }

    @Override
    public String getCracking() {
        StringBuilder encrypted = new StringBuilder();

        for (char oneChar : sentence.toCharArray()) {
            int beginPosition = oneChar - 32;
            int newestPosition = (beginPosition + key);
            encrypted.append((char) (32 + newestPosition));
        }
        return encrypted.toString();
    }
}
