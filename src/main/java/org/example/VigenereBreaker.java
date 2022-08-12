package main.java.org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    // all languages mapped to a HashSet of their words
    private HashMap<String, HashSet<String>> dictionaries;

    // all languages mapped to their most common characters
    private HashMap<String, Character> mostCommonChars;

    public VigenereBreaker() throws IOException {
        dictionaries = new HashMap<>();
        mostCommonChars = new HashMap<>();

        // reading in all dictionaries
        putDictionary("German");
        putDictionary("English");
        putDictionary("Danish");
        putDictionary("Dutch");
        putDictionary("French");
        putDictionary("Italian");
        putDictionary("Portuguese");
        putDictionary("Italian");
        putDictionary("Spanish");

        // put all chars on the dictionaries
        for (String lang : dictionaries.keySet()) {
            System.out.println(lang);
            System.out.println(mostCommonCharIn(dictionaries.get(lang)));
            mostCommonChars.put(lang, mostCommonCharIn(dictionaries.get(lang)));
        }
    }

    /**
     * put dictionaries on the list of dictionaries.
     *
     * @param language
     * @throws IOException
     */
    private void putDictionary(String language) throws IOException {
        String source = "res/dictionaries/"+language;
        dictionaries.put(language, readDictionary(new FileResource(source)));

    }

    /**
     * reads in a dictionary and puts it on the list of dictionaries (instance variable).
     *
     * @param fr - the file resource.
     * @return the HashSet of this dictionary - all words of this language in lower cases.
     * @throws IOException
     */
    public HashSet<String> readDictionary(FileResource fr) throws IOException {
        HashSet<String> dictionary = new HashSet<>();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            dictionary.add(word);
        }
        return dictionary;
    }

    /**
     * finds the most common char in a given dictionary.
     *
     * @param dictionary the dictionary
     * @return the most common char
     */
    public Character mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> charCounter = new HashMap<>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                if (!charCounter.containsKey(c)) {
                    charCounter.put(c, 1);
                } else {
                    charCounter.put(c, charCounter.get(c) + 1);
                }
            }
        }

        int maxFreq = 0;
        int currentFreq;
        Character maxC = 'y';
        for (Character c : charCounter.keySet()) {
            currentFreq = charCounter.get(c);
            if (currentFreq > maxFreq) {
                maxFreq = currentFreq;
                maxC = c;
            }
        }
        //System.out.println("most common char is " + maxC);
        return maxC;
    }

    /**
     * counts the words in a given message that are contained in a given dictionary.
     *
     * @param message the message
     * @param dictionary the dictionary
     * @return count
     */
    public int countWords(String message, HashSet<String> dictionary) {
        String[] wordsInMessage = message.split("\\W+");
        int counter = 0;
        for (String word : wordsInMessage) {
            word = word.toLowerCase();
            if (dictionary.contains(word)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * gives back the best decryption given a language's dictionary.
     *
     * @param encrypted the encrypted message
     * @param language the language of the dictionary in use.
     * @return the best decryption.
     */
    public String breakForLanguage(String encrypted, String language) {

        HashMap<int[], Integer> myKeys = new HashMap<>();
        HashSet<String> thisDict = dictionaries.get(language);
        int maxWords = 0;
        int[] bestKeys = null;
        String bestDecryption = "";
        char mostCommonCharForThis = mostCommonChars.get(language);
        HashSet<String> usedDict = dictionaries.get(language);
        int count  = 0;
        System.out.println(mostCommonCharForThis);
        for (int keyLength = 1; keyLength <= 100; keyLength++) {
            int[] currentKeys = tryKeyLength(encrypted, keyLength, mostCommonCharForThis);
            VigenereCipher vC = new VigenereCipher(currentKeys);
            String decrypted = vC.decrypt(encrypted);
            int currentCount = countWords(decrypted, usedDict);
            System.out.println("keyLength: " + keyLength);
            if (currentCount > maxWords) {
                System.out.println("currentCount (" + currentCount + ") more than maxWords(" + maxWords + ").");
                maxWords = currentCount;
                bestDecryption = decrypted;
                bestKeys = currentKeys;
            }
        }

        System.out.println("Number of words for best decryption: " + maxWords);
        if (bestKeys != null) {
            System.out.println("Best keys for this decryption: ");
            for (int k : bestKeys) {
                System.out.println(k);
            }
            System.out.println("Best keys key length: " + bestKeys.length);
        }

        System.out.println(bestDecryption);
        return bestDecryption;
    }

    public String breakForAllLangs(String encrypted) {
        String currentBest;
        String finalBest = "";
        String finalBestLanguage = "";
        int currentCount;
        int bestCount = 0;
        for (String lang : dictionaries.keySet()) {
            currentBest = breakForLanguage(encrypted, lang);
            //System.out.println(currentBest);
            currentCount = countWords(currentBest, dictionaries.get(lang));
            if (currentCount > bestCount) {
                bestCount = currentCount;
                finalBest = currentBest;
                finalBestLanguage = lang;
            }
        }
        System.out.println("Final best is " + finalBestLanguage);
        System.out.println(finalBest);
        return finalBest;

    }

    /**
     * slices up the message into slices of size @totalSlices. Then gives back
     * a String with the character at @whichSlice-th index of each of these slices.
     *
     * @param message the message to slice up
     * @param whichSlice the index of the character of each slice, that will be received.
     * @param totalSlices the total size of each slice.
     * @return a String with the character at @whichSlice-th index of slice.
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //System.out.println("Message: " + message);
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            sb.append(message.charAt(i));
        }
        //System.out.println("Slice " + whichSlice + ": " +sb.toString());
        return sb.toString();
    }

    /**
     * finds the keys for the Vigenere Cipher based on the length of the key.
     *
     * @param encrypted the encrypted message
     * @param kLength the length of the key.
     * @param mostCommon the most common Character used for it.
     * @return a list of the keys as Integers
     */
    public int[] tryKeyLength(String encrypted, int kLength, char mostCommon) {
        int[] key = new int[kLength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        //System.out.println("Most common char: " + mostCommon);
        //System.out.println("Encrypted: " + encrypted);
        //System.out.println("kLength: " + kLength);

        for (int i = 0; i < kLength; i++) {
            String slice = sliceString(encrypted, i, kLength);
            int aKey = cracker.getKey(slice);
            //System.out.println("the key from getKey: " + aKey);
            key[i] = aKey;
        }
        return key;
    }

    // depreciated
    /**
     * puts the methods together and prints out the decrypted message.
     *
     * @param encryptedMessage the encrypted message
     * @param kLength the length of the key.
     * @param mostCommon the most common Character used for it.
     */
    public void breakVigenere(String encryptedMessage, int kLength, Character mostCommon) {
        int[] keys = tryKeyLength(encryptedMessage, kLength, mostCommon);
        VigenereCipher vC = new VigenereCipher(keys);
        String decrypted = vC.decrypt(encryptedMessage);
        //System.out.println(decrypted);
    }

    /**
     * puts the methods together and prints out the decrypted message.
     *
     * @param encryptedMessage the encrypted message
     */
    public void breakVigenere(String encryptedMessage) throws IOException {
        String decrypted = breakForAllLangs(encryptedMessage);
        System.out.println(decrypted);
    }

}
