package com.pmf.codejam.social;

public class NotificationUserData {
	private String message;
	private String summary;
	
	public NotificationUserData(String message) {
		super();
		this.message = message;
		
	}
	
	public NotificationUserData(String message,String summary) {
		super();
		this.message = message;
		this.summary = summary;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
}
