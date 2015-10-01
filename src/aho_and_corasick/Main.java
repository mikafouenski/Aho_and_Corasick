package aho_and_corasick;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("coucou");
		arrayList.add("cocu");
		Pretreatement pretreatement = new Pretreatement(arrayList);
		pretreatement.makeState();
		pretreatement.makeTree();
		ArrayList<State> states = pretreatement.getStates();
		for (int i = 0; i < states.size(); i++) {
			System.out.println(states.get(i).getId().toString() + " " + states.get(i).getPrefix());
		}
	}

}
