package com.pmf.codejam.util;

public class BusinessValidator {
	public static boolean isNumber(String number){
		try {
            Integer.parseInt(number);        
        } catch (NumberFormatException ex) {
            return false;
        }        
        return true;
	}
	public static boolean isValidProductName(String productName){
		if(productName.length()>100){
			return false;
		}
		return true;
	}
}
