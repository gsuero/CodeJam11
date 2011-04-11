package com.pmf.codejam.exception;

public class ProductException extends Exception{
	private static final long serialVersionUID = 5597479021623884190L;
	
	public ProductException() {

	}
	public ProductException(String arg0) {
		super(arg0);
	}
	public ProductException(Throwable arg0) {
		super(arg0);
	}

	public ProductException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
