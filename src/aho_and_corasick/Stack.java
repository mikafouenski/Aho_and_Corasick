package aho_and_corasick;

import java.util.Vector;

/**
 * Pille pour stocker les valeurs equivalement a la Queue mais moins prise de
 * tête
 */
public class Stack<T> {
	Vector<T> vector = new Vector<T>();

	public Stack() {
		super();
	}

	public void add(T t) {
		this.vector.add(0, t);
	}

	public boolean isEmpty() {
		return this.vector.isEmpty();
	}

	public T pop() {
		return this.vector.remove(this.vector.size() - 1);
	}
}
