package aho_and_corasick;

import java.util.Iterator;

public class AhoCorasick {
	private State root;
	private boolean prepared;

	public AhoCorasick() {
		this.root = new State(0);
		this.prepared = false;
	}

	/**
	 * Ajout d'un mot cle avec l'affichage qui correspond ( je ne sais pas trop pourquoi je l'ai fait comme ça..)
	 * Pendant la recherche on vera les mots trouvé dans SearchResults.getDisplay().
	 */
	public void add(byte[] keyword, Object display) {
		if (this.prepared)
			throw new IllegalStateException("can't add keywords after prepare() is called");
		State lastState = this.root.extendAll(keyword);
		lastState.addDisplay(display);
	}

	/**
	 * Wrap pour prepareFailTransitions();
	 * ATTENTION il faut le mettre avant la recherche (YOU FOOL !)
	 */
	public void prepare() {
		this.prepareFailTransitions();
		this.prepared = true;
	}

	/**
	 * Commencer la recherche et sortir l'iterateur des resultats. WRAP pour un peu de propreté
	 */
	public Iterator search(byte[] bytes) {
		return new Searcher(this, this.startSearch(bytes));
	}

	/**
	 * Echec transitions de tout les etats sauf la racine.
	 * Un peu obscur venant du psedo code du papier du prof...
	 */
	private void prepareFailTransitions() {
		Stack<State> q = new Stack<State>();
		for (int i = 0; i < 256; i++)
			if (this.root.get((byte) i) != null) {
				this.root.get((byte) i).setFail(this.root);
				q.add(this.root.get((byte) i));
			}
		this.prepareRoot();
		while (!q.isEmpty()) {
			State state = q.pop();
			byte[] keys = state.keys();
			for (int i = 0; i < keys.length; i++) {
				State r = state;
				byte a = keys[i];
				State s = r.get(a);
				q.add(s);
				r = r.getFail();
				while (r.get(a) == null)
					r = r.getFail();
				s.setFail(r.get(a));
				s.getDisplay().addAll(r.get(a).getDisplay());
			}
		}
	}

	/**
	 * Met les transitions de la racine vers elle meme, oui encore les 256 etats a la main ;)
	 */
	private void prepareRoot() {
		for (int i = 0; i < 256; i++)
			if (this.root.get((byte) i) == null)
				this.root.put((byte) i, this.root);
	}

	State getRoot() {
		return this.root;
	}

	/**
	 * Commence la nouvelle recherche avec le row.
	 */
	SearchResult startSearch(byte[] bytes) {
		if (!this.prepared)
			throw new IllegalStateException("can't start search until prepare()");
		return continueSearch(new SearchResult(this.root, bytes, 0));
	}

	/**
	 * Continue la recherche avec le resultat d'avant DOUTEUX
	 */
	SearchResult continueSearch(SearchResult lastResult) {
		byte[] bytes = lastResult.bytes;
		State state = lastResult.lastMatchedState;
		for (int i = lastResult.lastIndex; i < bytes.length; i++) {
			byte b = bytes[i];
			while (state.get(b) == null)
				state = state.getFail();
			state = state.get(b);
			if (state.getDisplay().size() > 0)
				return new SearchResult(state, bytes, i + 1);
		}
		return null;
	}

}
