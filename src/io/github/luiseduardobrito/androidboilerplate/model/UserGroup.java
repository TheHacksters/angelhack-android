package io.github.luiseduardobrito.androidboilerplate.model;

public class UserGroup extends Model {

	/**
	 * Private attributes
	 */
	private String name;

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public UserGroup(String name) {
		this.name = name;
	}

	/**
	 * Get group name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set group name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
