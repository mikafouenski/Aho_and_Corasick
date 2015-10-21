package aho_and_corasick;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Liste de matches par un Iterator.
 */

class Searcher implements Iterator {
	private SearchResult currentResult;
	private AhoCorasick tree;

	Searcher(AhoCorasick tree, SearchResult result) {
		this.tree = tree;
		this.currentResult = result;
	}

	/**
	 * Boolean pour savoir s'il y a un suivant
	 */
	public boolean hasNext() {
		return (this.currentResult != null);
	}

	/**
	 * Revoit le suivant. Ou une execption s'il n'y a pas de suivant, voir au
	 * dessus.
	 */
	public Object next() {
		if (!hasNext())
			throw new NoSuchElementException();
		Object result = currentResult;
		currentResult = tree.continueSearch(currentResult);
		return result;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
