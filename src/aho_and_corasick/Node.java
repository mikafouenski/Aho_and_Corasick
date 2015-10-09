package aho_and_corasick;

import java.util.ArrayList;

public class Node<T> {
	private T data;
    private Node<T> parent;
    private ArrayList<Node<T>> children;

	public Node(T data, Node<T> parent) {
		super();
		this.data = data;
		this.parent = parent;
		this.children = new ArrayList<Node<T>>();
	}
	
	public void addChild(T data) {
		this.children.add(new Node<T>(data, this));
	}
		
	private static final int PRINT_INDENT = 2;
	public String print(int increment) {
    	String s = "";
    	String decalage = "";
    	for (int i = 0; i < increment; ++i) decalage = decalage + " ";
    	s = decalage + this.data;
    	for (Node<T> child : this.children) s += "\n" + child.print(increment + PRINT_INDENT);
    	return s;
    }
	
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * @return the parent
	 */
	public Node<T> getParent() {
		return parent;
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
	/**
	 * @return the children
	 */
	public ArrayList<Node<T>> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(ArrayList<Node<T>> children) {
		this.children = children;
	}
}
