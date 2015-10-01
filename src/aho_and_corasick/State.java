package aho_and_corasick;

import java.util.ArrayList;

public class State {
	private Integer id;
	private String prefix;
	private static Integer count = 0;
	
	private State father;
	private ArrayList<State> sons;
	
	/**
	 * @param id
	 * @param prefix
	 */
	public State(String prefix) {
		super();
		this.id = State.count;
		State.count++;
		this.prefix = prefix;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * @return the nexts
	 */
	public ArrayList<State> getNexts() {
		return sons;
	}
	/**
	 * @param nexts the nexts to set
	 */
	public void setNexts(ArrayList<State> nexts) {
		this.sons = nexts;
	}
	/**
	 * @return the father
	 */
	public State getFather() {
		return father;
	}
	/**
	 * @param father the father to set
	 */
	public void setFather(State father) {
		this.father = father;
	}
	
	
	public void addSon(State state) {
		this.sons.add(state);
		state.setFather(this);
	}
}
