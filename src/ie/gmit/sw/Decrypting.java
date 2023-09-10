package ie.gmit.sw;

public class Decrypting extends ChiCypher {
    private int key;
    private String sentence;

    public Decrypting(int key, String sentence) {
        this.key = key;
        this.sentence = sentence;
    }

    @Override
    public String getCracking() {
        StringBuilder decrypted = new StringBuilder();

        for (char oneChar : sentence.toCharArray()) {
            int beginPosition = oneChar - 32;
            int newestPosition = (beginPosition - key);

            if (newestPosition >= 0) {
                decrypted.append((char) (newestPosition + 32));
            } else {
                decrypted.append((char) (newestPosition + 127));
            }
        }
        return decrypted.toString();
    }
}
