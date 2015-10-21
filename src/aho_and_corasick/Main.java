package aho_and_corasick;

import java.util.Iterator;

public class Main {	

	public static void main(String[] args) {
		// Pour le temps
		long startTime = System.currentTimeMillis();

		AhoCorasick tree = new AhoCorasick();
		tree.add("coucou".getBytes(), "coucou");
		tree.add("cocu".getBytes(), "cocu");
		tree.prepare();

		Iterator searcher = tree.search("coucoucoucocu".getBytes());
		while (searcher.hasNext()) {
			SearchResult result = (SearchResult) searcher.next();
			System.out.println(result.getDisplay());
			System.out.println("Found at index: " + result.getLastIndex());
		}

		// Pour le temps
		long endTime = System.currentTimeMillis();
		System.out.println("Exec time = " + (endTime - startTime) + " ms");

	}

}
