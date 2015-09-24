package aho_and_corasick;

import java.util.ArrayList;
import java.util.Collections;

public class Pretreatement {
	private ArrayList<String> key_words;
	

	/**
	 * @param key_word
	 */
	public Pretreatement(ArrayList<String> key_words) {
		super();
		Collections.sort(key_words);
		this.key_words = key_words;
	}
	
	public void makeState() {
		ArrayList<State> states = new ArrayList<State>();
		states.add(new State(null));
		Integer max = getMax(key_words);
		
		for (int i = 0; i < max; i++) {
			for (String key_word : this.key_words) {
				if (i <= key_word.length())	states.add(new State(key_word.substring(0, i)));
			}
		}
		
	}
	
	private Integer getMax(ArrayList<String> a) {
		Integer max = 0;
		for (String string : a) {
			if(string.length() < max) max = string.length();
		}
		return max;
	}
}
