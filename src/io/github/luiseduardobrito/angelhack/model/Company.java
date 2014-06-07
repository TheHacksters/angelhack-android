package io.github.luiseduardobrito.angelhack.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Company")
public class Company extends ParseObject {

	/**
	 * Constructor
	 * 
	 * @param name
	 */
	public Company(String name) {
		setName(name);
	}

	/**
	 * Get group name
	 * 
	 * @return name
	 */
	public String getName() {
		return getString("name");
	}

	/**
	 * Set group name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		put("name", name);
	}

	/**
	 * Set company admin user
	 * 
	 * @param admin
	 */
	public void setAdmin(User admin) {
		put("admin", admin);
	}

	/**
	 * Get admin user
	 * 
	 * @return
	 */
	public User getAdmin() {
		return (User) getParseUser("admin");
	}
}
