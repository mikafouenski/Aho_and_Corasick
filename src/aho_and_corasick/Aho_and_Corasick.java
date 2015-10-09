package aho_and_corasick;

public class Aho_and_Corasick {

	public static void main(String[] args) {
		Tree<String> tree = new Tree<String>("root");
		tree.getRoot().addChild("Fils1");
		tree.getRoot().addChild("Fils2");
		System.out.println(tree.printTree());
	}

}
