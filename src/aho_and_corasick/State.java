package aho_and_corasick;

import java.util.HashSet;
import java.util.Set;

/**
 * L'etat est l'element de base de l'arbre
 */

class State {

	private int depth;
	private SonsList sonsList = new SonsList();
	private State fail = null;
	private Set display = new HashSet();

	public State(int depth) {
		this.depth = depth;
	}

	/**
	 * ajout d'un byte
	 */
	public State extend(byte b) {
		if (this.sonsList.get(b) != null)
			return this.sonsList.get(b);
		State nextState = new State(this.depth + 1);
		this.sonsList.put(b, nextState);
		return nextState;
	}

	/**
	 * ajout d'un lot d'elements.
	 */
	public State extendAll(byte[] bytes) {
		State state = this;
		for (int i = 0; i < bytes.length; i++) {
			if (state.sonsList.get(bytes[i]) != null)
				state = state.sonsList.get(bytes[i]);
			else
				state = state.extend(bytes[i]);
		}
		return state;
	}

	/**
	 * Retourne la taille à partir de l'element courant, N'utilise pas ca après
	 * .prepare() (you fool !)
	 */
	public int size() {
		byte[] keys = sonsList.keys();
		int result = 1;
		for (int i = 0; i < keys.length; i++)
			result += sonsList.get(keys[i]).size();
		return result;
	}

	public State get(byte b) {
		return this.sonsList.get(b);
	}

	public void put(byte b, State s) {
		this.sonsList.put(b, s);
	}

	public byte[] keys() {
		return this.sonsList.keys();
	}

	public State getFail() {
		return this.fail;
	}

	public void setFail(State f) {
		this.fail = f;
	}

	public void addDisplay(Object o) {
		this.display.add(o);
	}

	public Set getDisplay() {
		return this.display;
	}
}
