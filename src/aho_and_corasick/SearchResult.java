package aho_and_corasick;

import java.util.Set;

/**
 * Contient le resultat jusqu'a present ! Pointeur vers le dernier match. Garde
 * en privé un historique (un peu sale, a voir)
 */
public class SearchResult {
	State lastMatchedState;
	byte[] bytes;
	int lastIndex;

	SearchResult(State s, byte[] bs, int i) {
		this.lastMatchedState = s;
		this.bytes = bs;
		this.lastIndex = i;
	}

	/**
	 * Sort la liste de sortie de ce match
	 */
	public Set getDisplay() {
		return lastMatchedState.getDisplay();
	}

	/**
	 * Sort l'index ou la recherche se termine. 1 après le dernier char match.
	 */
	public int getLastIndex() {
		return lastIndex;
	}
}
