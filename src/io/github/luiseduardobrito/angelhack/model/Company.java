package io.github.luiseduardobrito.angelhack.model;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Company")
public class Company extends ParseObject {

	Integer count;

	public Company() {
		try {
			getUserCount();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 * @throws ParseException
	 */
	public Company(String name, User admin) throws ParseException {
		setName(name);
		setAdmin(admin);
		save();
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

	public Integer getUserCount() throws ParseException {

		if (count == null || count == 0) {
			ParseQuery<User> query = new ParseQuery<User>(User.class);
			query.whereEqualTo("companies", this);
			count = query.count();
		}

		return count;
	}

	public void batchInvite(List<String> emailList) throws ParseException {

		List<ParseQuery<User>> queries = new ArrayList<ParseQuery<User>>();

		for (String email : emailList) {
			ParseQuery<User> query = new ParseQuery<User>(User.class);
			query.whereEqualTo("email", email);
			queries.add(query);
		}

		ParseQuery<User> mainQuery = ParseQuery.or(queries);

		for (User user : mainQuery.find()) {
			user.addCompany(this);
		}
	}

	public void addMember(User user) throws ParseException {
		add("members", user);
		save();
	}
}
