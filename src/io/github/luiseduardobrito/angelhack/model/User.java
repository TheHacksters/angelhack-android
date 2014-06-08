package io.github.luiseduardobrito.angelhack.model;

import io.github.luiseduardobrito.angelhack.exception.AppException;
import io.github.luiseduardobrito.angelhack.exception.ErrorCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * @author luiseduardobrito
 * 
 */
@ParseClassName("_User")
public class User extends ParseUser {

	public static final int MIN_PASSWORD_LENGTH = 6;

	public static User signUp(String name, String email, String password,
			Date birthDay) throws AppException, ParseException {
		return new User(name, email, password, birthDay);
	}

	/*
	 * Constructor
	 */
	public User() {
		// Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @throws AppException
	 * @throws ParseException
	 */
	public User(String name, String email, String password, Date birthDay)
			throws AppException, ParseException {

		// Check password length
		if (password.isEmpty() || password.length() < MIN_PASSWORD_LENGTH) {
			throw new AppException(ErrorCode.INVALID_PASSWORD);
		}

		// Set user fields
		setName(name);
		setUsername(email);
		setEmail(email);
		put("birthDay", birthDay);

		// Set user password
		setPassword(password);
		signUp();
	}

	/**
	 * Set user name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		put("name", name);
	}

	/**
	 * Get user name
	 * 
	 * @return name
	 */
	public String getName() {
		return getString("name");
	}

	/**
	 * Get user email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return super.getEmail();
	}

	/**
	 * Get user group
	 * 
	 * @return group
	 */
	public Company getGroup() {
		return (Company) getParseObject("group");
	}

	/**
	 * Get user birthday
	 * 
	 * @return birthDay
	 */
	public Date getBirthDay() {
		return getDate("birthDay");
	}

	/**
	 * Add user to a company
	 * 
	 * @param company
	 * @throws ParseException
	 */
	public void addCompany(Company company) throws ParseException {
		company.addMember(this);
	}

	/**
	 * Get companies lsit
	 * 
	 * @return
	 * @throws ParseException
	 */
	public List<Company> getCompanies() throws ParseException {
		ParseQuery<Company> query = new ParseQuery<Company>(Company.class);
		query.whereEqualTo("members", this);
		return query.find();
	}

	/**
	 * Get visible and available events
	 * 
	 * @return eventList
	 * @throws ParseException
	 */
	public List<Event> getAvailableEvents() throws ParseException {

		ParseQuery<Event> creatorQuery = new ParseQuery<Event>(Event.class);
		creatorQuery.whereEqualTo("creator", this);

		ParseQuery<Event> invitedQuery = new ParseQuery<Event>(Event.class);
		invitedQuery.whereEqualTo("invited", this);

		ParseQuery<Event> confirmedQuery = new ParseQuery<Event>(Event.class);
		confirmedQuery.whereEqualTo("confirmed", this);

		List<ParseQuery<Event>> mainQuery = new ArrayList<ParseQuery<Event>>();
		mainQuery.add(creatorQuery);
		mainQuery.add(invitedQuery);
		mainQuery.add(confirmedQuery);

		return ParseQuery.or(mainQuery).find();
	}
}
