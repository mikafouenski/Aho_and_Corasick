package aho_and_corasick;

/**
 * Test avec les listes chainées, copie venant du projet c++ du DUT ^^
 */

class SparseEdgeList implements EdgeList {
	private Cons head;

	public SparseEdgeList() {
		head = null;
	}

	public State get(byte b) {
		Cons c = head;
		while (c != null) {
			if (c.b == b)
				return c.s;
			c = c.next;
		}
		return null;
	}

	public void put(byte b, State s) {
		this.head = new Cons(b, s, head);
	}

	public byte[] keys() {
		int length = 0;
		Cons c = head;
		while (c != null) {
			length++;
			c = c.next;
		}
		byte[] result = new byte[length];
		c = head;
		int j = 0;
		while (c != null) {
			result[j] = c.b;
			j++;
			c = c.next;
		}
		return result;
	}
	
	// oui je declare ma classe la. Je ne sais meme plus a quoi elle sert...
	static private class Cons {
		byte b;
		State s;
		Cons next;

		public Cons(byte b, State s, Cons next) {
			this.b = b;
			this.s = s;
			this.next = next;
		}
	}

}
