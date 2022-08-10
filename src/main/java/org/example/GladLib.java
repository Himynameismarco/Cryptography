package main.java.org.example;

import edu.duke.*;
import java.util.*;

//TODO - delete

public class GladLib {

	private HashMap<String, ArrayList<String>> mapOfCategories;
	private HashMap<String, ArrayList<String>> usedList;

	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLib(){
		this.mapOfCategories = new HashMap<>();
		this.usedList = new HashMap<>();
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLib(String source){
		this.mapOfCategories = new HashMap<>();
		this.usedList = new HashMap<>();
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		String[] labels = {"adjective", "noun", "color", "country", "name"
				, "animal", "timeframe", "verb", "fruit"};
		for (String s : labels) {
			ArrayList<String> listOfWords = readIt(source + "/" + s + ".txt");
			mapOfCategories.put(s, listOfWords);
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		if (label.equals(label)) {
			String word = randomFrom(mapOfCategories.get(label));
			System.out.println("Word: " + word);
			if (!usedList.containsKey(label)) {
				ArrayList<String> listOfWords = new ArrayList<>();
				listOfWords.add(word);
				usedList.put(label, listOfWords);
			} else {
				while (usedList.get(label).contains(word)) {
					word = randomFrom(mapOfCategories.get(label));
          System.out.println("New Word: " + word);
				}
				usedList.get(label).add(word);
			}
			return word;
		}
		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		String story = fromTemplate("data/madtemplate2.txt");
		printOut(story, 60);
	}

	/**
	 *
	 * @return the total number of words in all categories.
	 */
	public int getTotalNumberOfWords() {
		int counter = 0;
		for (String key : mapOfCategories.keySet()) {
			counter += mapOfCategories.get(key).size();
		}
		return counter;
	}

	public int getTotalNumberOfWordsConsidered() {
		int counter = 0;
		for (String key : usedList.keySet()) {
			counter += usedList.get(key).size();
		}
		return counter;
	}

}
