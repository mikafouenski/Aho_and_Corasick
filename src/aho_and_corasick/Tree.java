package aho_and_corasick;

public class Tree<T> {
	private Node<T> root;
	/**
	 * @param rootData
	 */
    public Tree(T rootData) {
        this.root = new Node<T>(rootData, null);
    }
    
    public String printTree() {
    	return this.root.print(0);
    }
	/**
	 * @return the root
	 */
	public Node<T> getRoot() {
		return root;
	}
	/**
	 * @param root the root to set
	 */
	public void setRoot(Node<T> root) {
		this.root = root;
	}
}
