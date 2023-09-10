package ie.gmit.sw;

import java.util.concurrent.Callable;

public class Mapper implements Callable<String> {
    private int key;
    private String cryptedFile;

    public Mapper(int key, String cryptedFile) {
        this.key = key;
        this.cryptedFile = cryptedFile;
    }

    @Override
    public String call() throws Exception {
        Decrypting de = new Decrypting(key, cryptedFile);
        return de.getCracking();
    }
}
