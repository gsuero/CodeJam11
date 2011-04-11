package com.pmf.codejam.exception;

public class InventoryItemException extends Exception{
	private static final long serialVersionUID = 5597479021623884190L;
	
	public InventoryItemException() {

	}
	public InventoryItemException(String arg0) {
		super(arg0);
	}
	public InventoryItemException(Throwable arg0) {
		super(arg0);
	}

	public InventoryItemException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}