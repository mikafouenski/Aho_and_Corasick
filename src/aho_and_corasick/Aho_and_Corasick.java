package aho_and_corasick;

import java.util.ArrayList;

public class Aho_and_Corasick {

	public static void main(String[] args) {
		Tree<String> tree = new Tree<String>("root");
		tree.getRoot().addChild("Fils1");
		tree.getRoot().addChild("Fils2");
		ArrayList<Node<String>> arrayList = tree.getRoot().getChildren();
		for (int i = 0; i < arrayList.size(); ++i) {
			arrayList.get(i).addChild("Fils" + (i + 1) + "1");
			arrayList.get(i).addChild("Fils" + (i + 1) + "2");
		}
		System.out.println(tree.printTree());
	}

}
