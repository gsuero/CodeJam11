/**
 * 
 */
package com.pmf.codejam.exception;

/**
 * @author Frederick
 *
 */
@SuppressWarnings("serial")
public class SpecialException extends Exception {

	/**
	 * 
	 */
	public SpecialException() {

	}

	/**
	 * @param message
	 */
	public SpecialException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SpecialException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SpecialException(String message, Throwable cause) {
		super(message, cause);
	}

}
