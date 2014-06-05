package io.github.luiseduardobrito.androidboilerplate.exception;

import io.github.luiseduardobrito.androidboilerplate.R;

import java.util.Locale;

public enum ErrorCode {

	/**
	 * Unknown errors
	 */
	UNKNOWN_EXCEPTION(100, R.string.unknown_exception),
	UNKNOWN_RUNTIME_EXCEPTION(101, R.string.unknown_runtime_exception),

	/**
	 * Validation Errors
	 */
	INVALID_FIELD(200, R.string.invalid_field),
	INVALID_NAME(201, R.string.invalid_name),
	INVALID_EMAIL(202, R.string.invalid_email),
	INVALID_PASSWORD(203, R.string.invalid_password);

	/**
	 * Protected fields
	 */
	int mErrorCode;
	int mStringResourceId;

	/**
	 * Constructor
	 * 
	 * @param code
	 * @param mStringResourceId
	 */
	private ErrorCode(int code, int mStringResourceId) {
		this.mStringResourceId = mStringResourceId;
	}

	/**
	 * Get error string resourd id
	 * 
	 * @return mStringResourceId
	 */
	public int getStringResourceId() {
		return mStringResourceId;
	}

	/**
	 * Get error name
	 * 
	 * @return error
	 */
	public String getName() {
		return name().toLowerCase(Locale.getDefault());
	}

	/**
	 * Get error code
	 * 
	 * @return code
	 */
	public int getCode() {
		return mErrorCode;
	}
}
