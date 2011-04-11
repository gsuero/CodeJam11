package com.pmf.codejam.exception;

public class SpecialExpiredException extends Exception {
	private static final long serialVersionUID = 5597479021623884190L;

	public SpecialExpiredException() {

	}

	public SpecialExpiredException(String arg0) {
		super(arg0);
	}

	public SpecialExpiredException(Throwable arg0) {
		super(arg0);
	}

	public SpecialExpiredException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
