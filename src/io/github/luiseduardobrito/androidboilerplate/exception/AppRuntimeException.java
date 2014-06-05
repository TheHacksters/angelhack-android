package io.github.luiseduardobrito.androidboilerplate.exception;

public class AppRuntimeException extends RuntimeException {

	/**
	 * Generated serial versio UID
	 */
	private static final long serialVersionUID = 851529531750061546L;

	/**
	 * ErrorCode enum instance
	 */
	ErrorCode mErrorCode = ErrorCode.UNKNOWN_EXCEPTION;

	/**
	 * Constructor
	 */
	public AppRuntimeException() {
		this.mErrorCode = ErrorCode.UNKNOWN_RUNTIME_EXCEPTION;
	}

	/* (non-Javadoc)
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

	/**
	 * Get exception error code enum
	 * 
	 * @return error
	 */
	public ErrorCode getErrorCode() {
		return mErrorCode;
	}
}
