package ie.gmit.sw;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.*;

public class Runner {
    private static String answer;
    private static String text = "happy days!";
    private static int key = 6;
    private List<Future<String>> resultList = new ArrayList<>();
    private static int userInput;
    private static MapFile mf = new MapFile();
    private static Occurence occurrence = new Occurence();
    private Map<Future<Double>, Integer> resultChiListMap = new ConcurrentHashMap<>();

    public void goChi() throws InterruptedException {
        Map<Character, Double> expected = new HashMap<>();
        Map<Character, Integer> occurrences = new HashMap<>();

        occurrences = occurrence.characterCount(answer);
        expected = mf.mapTheFile();

        ExecutorService es = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            Mapper attempts = new Mapper(i, answer);
            Future<String> res = es.submit(attempts);
            resultList.add(res);
        }

        es.awaitTermination(3, TimeUnit.SECONDS);

        for (int i = 0; i < resultList.size(); i++) {
            Future<String> resu = resultList.get(i);
            String myString = "";

            try {
                myString = resu.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("Shifted " + i + " : " + myString);
        }
        System.out.println("\n ** Did we crack it? **");

        es.shutdown();
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Map<Character, Double> expected = new HashMap<>();
        Map<Character, Integer> occurrences = new HashMap<>();
        Scanner scan = new Scanner(System.in);

        int randomKey = ThreadLocalRandom.current().nextInt(1, 9 + 1);
        key = randomKey;

        System.out.println("1) Enter text to encrypt");
        System.out.println("2) Decrypt the text");
        System.out.println("3) Show Mapped mono file and encrypted character occurrences");
        System.out.println("4) Please enter file path to cypher text you wish to decrypt");
		System.out.println("5) Quit");
        System.out.println("6) Chi square statistic decrypt - WIP, return values not working");
        userInput = scan.nextInt();

        do {
            if (userInput == 1) {
                System.out.println("Please enter a sentence to encrypt");
                scan.nextLine();
                text = scan.nextLine();

                Encrypting en = new Encrypting(key, text);
                answer = en.getCracking();
                System.out.println("This is your new encrypted sentence: " + answer);
            } else if (userInput == 2) {
                new Runner().go();
            } else if (userInput == 3) {
                Encrypting en = new Encrypting(key, text);
                answer = en.getCracking();
                occurrences = occurrence.characterCount(answer);
                expected = mf.mapTheFile();

                System.out.println("Mapped mono file: " + expected);
                System.out.println("\nThis is the encrypted message: " + answer);
                System.out.println("\nDecrypted Character occurrences: " + occurrences);
            } else if (userInput == 4) {
                System.out.println("Please enter the path");
                scan.nextLine();
                String userEntry = scan.nextLine();

                String fileAns = mf.readFile(userEntry);
                System.out.println("Cipher code received from file: " + fileAns);
                answer = fileAns;
                new Runner().go();
            } else if (userInput == 6) {
                new Runner().goChi();
            }
            System.out.println("\n1) Enter text to encrypt");
            System.out.println("2) Decrypt the text");
            System.out.println("3) Show Mapped mono file and encrypted character occurrences");
            System.out.println("4) Please enter file path to cypher text you wish to decrypt");
            System.out.println("5) Quit");
            System.out.println("6) Chi square statistic decrypt - WIP, return values not working");
            userInput = scan.nextInt();
        } while (userInput != 5);

        System.out.println("Thank you and goodbye");
        scan.close();
    }

    private void go() {
        Decrypting de = new Decrypting(key, answer);
        String decrypted = de.getCracking();
        System.out.println("Decrypted message: " + decrypted);
    }
}
