package aho_and_corasick;

import java.io.*;
import java.util.Iterator;

public class Main {	
	public static AhoCorasick tree = new AhoCorasick();
	public static String text = null;

	public static void main(String[] args) {
		// Pour le temps
		long startTime = System.currentTimeMillis();


		//AhoCorasick tree = new AhoCorasick();
		//tree.add("coucou".getBytes(), "coucou");
		//tree.add("cocu".getBytes(), "cocu");
		tree.prepare();

		Iterator searcher = tree.search(text.getBytes());
		while (searcher.hasNext()) {
			SearchResult result = (SearchResult) searcher.next();
			System.out.println(result.getDisplay());
			System.out.println("Found at index: " + result.getLastIndex());
			gui.GUIAhoCorasick.Result(result.getDisplay().toString(), result.getLastIndex());
		}

		/* BAH CA MARCHE PAS, a cause du NULL :'(
		try {
			long startTime = System.currentTimeMillis();
			AhoCorasick tree = new AhoCorasick();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(new FileInputStream("txt/MotsClesPourTest.txt")));
			String line;
			while ((line = reader.readLine()) != null)
				tree.add(line.getBytes(), null);
			tree.prepare();

			Iterator searcher = tree.search("aabccababcbababbacaaccababcbabcbaacbcabcbaccbabcabcba".getBytes());
			while (searcher.hasNext()) {
				SearchResult result = (SearchResult) searcher.next();
				System.out.println(result.getDisplay());
				System.out.println("Found at index: " + result.getLastIndex());
			}

			long endTime = System.currentTimeMillis();
			System.out.println("endTime - startTime = " + (endTime - startTime) + " milliseconds");
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/



		// Pour le temps
		long endTime = System.currentTimeMillis();
		System.out.println("Exec time = " + (endTime - startTime) + " ms");

	}

}
