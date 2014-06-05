package io.github.luiseduardobrito.androidboilerplate.model;

import io.github.luiseduardobrito.androidboilerplate.exception.AppException;
import io.github.luiseduardobrito.androidboilerplate.exception.ErrorCode;

public class User extends Model {

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
	 * Log in new user instance
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws AppException
	 */
	public static User logIn(String email, String password) throws AppException {

		if (instance.email.equals(email) && instance.password.equals(password)) {
			return instance;
		}

		throw new AppException(ErrorCode.AUTH_INVALID_INFO);
	}

	/**
	 * Sign up new user instance
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 * @throws AppException
	 */
	public static User signUp(String name, String email, String password)
			throws AppException {
		instance = new User(name, email, password);
		return instance;
	}

	String name;
	String email;
	String password;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * @throws AppException
	 */
	public User(String name, String email, String password) throws AppException {

		// Prepare string fields
		this.name = name;
		this.email = email;

		// Check password length
		if (password.isEmpty() || password.length() < MIN_PASSWORD_LENGTH) {
			throw new AppException(ErrorCode.INVALID_PASSWORD);
		}
	}

	/**
	 * Get user name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get user email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
}
