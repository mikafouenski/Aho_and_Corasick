package aho_and_corasick;

public class State {
	private Integer id;
	private String prefix;
	private static Integer count = 0;
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
	
}
