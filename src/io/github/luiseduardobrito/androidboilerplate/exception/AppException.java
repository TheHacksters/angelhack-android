package io.github.luiseduardobrito.androidboilerplate.exception;

public class AppException extends Exception {

	/**
	 * Generated serial versio UID
	 */
	private static final long serialVersionUID = -8024583994086407492L;

	/**
	 * ErrorCode enum instance
	 */
	ErrorCode mErrorCode = ErrorCode.UNKNOWN_EXCEPTION;

	/**
	 * Constructor
	 */
	public AppException() {
		this.mErrorCode = ErrorCode.UNKNOWN_EXCEPTION;
	}

	/**
	 * Constructor
	 * 
	 * @param error
	 */
	public AppException(ErrorCode error) {
		this.mErrorCode = error;
	}

	/**
	 * Constructor
	 * 
	 * @param e
	 */
	public AppException(Exception e) {
		super(e);
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 */
	public AppException(String msg) {
		super(msg);
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 * @param code
	 */
	public AppException(String msg, int code) {
		super("Code " + code + ": " + msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {

		if (mErrorCode != null) {
			return mErrorCode.getName();
		}

		else {
			return super.toString();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {

		if (mErrorCode != null) {
			return mErrorCode.getName();
		}

		else {
			return super.getMessage();
		}

	}

	/**
	 * Get exception error code enum
	 * 
	 * @return error
	 */
	public ErrorCode getErrorCode() {
		return mErrorCode;
	}
}
