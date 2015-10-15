package aho_and_corasick;

/**
 * EdgeList avec un tableau... de 256
 */

class DenseEdgeList {
	private State[] array;

	public DenseEdgeList() {
		this.array = new State[256];
		for (int i = 0; i < array.length; i++)
			this.array[i] = null;
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
