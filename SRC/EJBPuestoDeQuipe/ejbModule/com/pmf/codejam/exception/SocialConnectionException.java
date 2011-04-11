package com.pmf.codejam.exception;

public class SocialConnectionException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public SocialConnectionException() {

	}

	public SocialConnectionException(String arg0) {
		super(arg0);
	}

	public SocialConnectionException(Throwable arg0) {
		super(arg0);
	}

	public SocialConnectionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
