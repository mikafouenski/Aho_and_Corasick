package aho_and_corasick;

/**
 * EdgeList avec un tableau... de 256
 */

class SonsList {
	private State[] array;

	public SonsList() {
		this.array = new State[256];
		for (int i = 0; i < array.length; i++)
			this.array[i] = null;
	}

	/**
	 * Retourne l'état fils a la position de la lettre dans le tableau. C'est a
	 * dire la valleur ascii de la lettre ou caractère. ( Le et binaire sert a
	 * vérifier que l'on est bien sur 256).
	 */
	public State get(byte b) {
		return this.array[(int) b & 0xFF];
	}
	/**
	 * Met l'état dans la liste de fils à la postion de la lettre.
	 */
	public void put(byte b, State s) {
		this.array[(int) b & 0xFF] = s;
	}
	/**
	 * Revoie le tableau de fils, plus petit, avec juste les elements dedans.
	 */
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
