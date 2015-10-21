package aho_and_corasick;

import java.util.HashSet;
import java.util.Set;

/**
 * L'etat est l'element de base de l'arbre
 */

class State {

	private int depth;
	private SonsList edgeList = new SonsList();
	private State fail = null;
	private Set display = new HashSet();

	public State(int depth) {
		this.depth = depth;
	}

	public State extend(byte b) {
		if (this.edgeList.get(b) != null)
			return this.edgeList.get(b);
		State nextState = new State(this.depth + 1);
		this.edgeList.put(b, nextState);
		return nextState;
	}

	public State extendAll(byte[] bytes) {
		State state = this;
		for (int i = 0; i < bytes.length; i++) {
			if (state.edgeList.get(bytes[i]) != null)
				state = state.edgeList.get(bytes[i]);
			else
				state = state.extend(bytes[i]);
		}
		return state;
	}

	/**
	 * Retourne la taille comme si la racine est l'element courant, N'utilise
	 * pas ca après .prepare() (you fool !)
	 */
	public int size() {
		byte[] keys = edgeList.keys();
		int result = 1;
		for (int i = 0; i < keys.length; i++)
			result += edgeList.get(keys[i]).size();
		return result;
	}

	public State get(byte b) {
		return this.edgeList.get(b);
	}

	public void put(byte b, State s) {
		this.edgeList.put(b, s);
	}

	public byte[] keys() {
		return this.edgeList.keys();
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
