package io.github.luiseduardobrito.angelhack.model;

import io.github.luiseduardobrito.angelhack.exception.AppException;
import io.github.luiseduardobrito.angelhack.exception.ErrorCode;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * @author luiseduardobrito
 * 
 */
@ParseClassName("_User")
public class User extends ParseUser {

	public static final int MIN_PASSWORD_LENGTH = 6;

	public static User instance;

	/**
	 * Get current user instance
	 * 
	 * @return
	 */
	public static User getCurrent() {
		return instance;
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

		// Set user fields
		setName(name);
		setEmail(email);
		put("birthDay", birthDay);

		// Check password length
		if (password.isEmpty() || password.length() < MIN_PASSWORD_LENGTH) {
			throw new AppException(ErrorCode.INVALID_PASSWORD);
		}

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
		saveEventually();
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
}
