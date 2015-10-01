package aho_and_corasick;

import java.util.ArrayList;
import java.util.Collections;

public class Pretreatement {
	private ArrayList<String> key_words;
	ArrayList<State> states = new ArrayList<State>();
	

	/**
	 * @param key_word
	 */
	public Pretreatement(ArrayList<String> key_words) {
		super();
		Collections.sort(key_words);
		this.key_words = key_words;
	}
	
	public void makeState() {
		
		this.states.add(new State(""));
		
		Integer max = getMax(key_words);
		
		for (int i = 0; i < max; i++) {
			String tmp = new String("");
			for (String key_word : this.key_words) {
				if (i < key_word.length()) {
					String souschaine = key_word.substring(0, i + 1);
					if (! tmp.equals(souschaine)) {
						this.states.add(new State(souschaine));
						tmp = souschaine;
					}
				}
			}
		}
	}
	
	private Integer getMax(ArrayList<String> a) {
		Integer max = 0;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).length() > max) max = a.get(i).length();
		}
		return max;
	}
	
	public void  makeTree() {
		State state = this.states.get(0);
		
		for (int i = 1; i < states.size(); i++) {
			if(states.get(i).getPrefix().substring(0, state.getPrefix().length()) == state.getPrefix()) {
				states.get(i).setFather(state);
				state = this.states.get(i);
				
			} else {
				states.get(i).setFather(state.getFather());
				state = this.states.get(i);
			}
		}
		
		for (int i = 0; i < this.states.length(); i++) {
			//TODO
		}
	}

	/**
	 * @return the states
	 */
	public ArrayList<State> getStates() {
		return states;
	}
	
}
