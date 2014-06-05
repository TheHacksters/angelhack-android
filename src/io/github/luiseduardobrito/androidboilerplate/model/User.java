package io.github.luiseduardobrito.androidboilerplate.model;

import io.github.luiseduardobrito.androidboilerplate.exception.AppException;
import io.github.luiseduardobrito.androidboilerplate.exception.ErrorCode;

public class User extends Model {

	public static final int MIN_PASSWORD_LENGTH = 6;

	String name;
	String email;
	String password;

	public User(String name, String email, String password) throws AppException {

		// Prepare string fields
		this.name = name;
		this.email = email;

		// Check password length
		if (password.isEmpty() || password.length() < MIN_PASSWORD_LENGTH) {
			throw new AppException(ErrorCode.INVALID_PASSWORD);
		}
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}
