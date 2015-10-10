package aho_and_corasick;

/**
 * Interface pour essayer d'optimiser la place de la liste de bytes (BETA !)
 */
interface EdgeList {
	State get(byte ch);

	void put(byte ch, State state);

	byte[] keys();
}
