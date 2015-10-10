package aho_and_corasick;

/**
 * EdgeList avec un simple tableau SALE ;) Il faut le passer en arraylist mais j'en ai marre la !
 */

class DenseEdgeList implements EdgeList {
	private State[] array;

	public DenseEdgeList() {
		this.array = new State[256];
		for (int i = 0; i < array.length; i++)
			this.array[i] = null;
	}

	/**
	 * Conversion entre liste chainée (BETA ! ! ! !! )
	 */
	public static DenseEdgeList fromSparse(SparseEdgeList list) {
		byte[] keys = list.keys();
		DenseEdgeList newInstance = new DenseEdgeList();
		for (int i = 0; i < keys.length; i++) {
			newInstance.put(keys[i], list.get(keys[i]));
		}
		return newInstance;
	}

	public State get(byte b) {
		return this.array[(int) b & 0xFF];
	}

	public void put(byte b, State s) {
		this.array[(int) b & 0xFF] = s;
	}

	public byte[] keys() {
		int length = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null)
				length++;
		}
		byte[] result = new byte[length];
		int j = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				result[j] = (byte) i;
				j++;
			}
		}
		return result;
	}

}
