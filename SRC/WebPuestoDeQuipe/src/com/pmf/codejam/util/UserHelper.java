package com.pmf.codejam.util;

public class UserHelper {
	public static boolean isValidUserAdmin(String user, String password) {
		return ("admin".equals(user) && "admin".equals(password));
	}
}
