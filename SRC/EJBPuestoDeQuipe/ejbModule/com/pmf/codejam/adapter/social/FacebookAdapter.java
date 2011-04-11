package com.pmf.codejam.adapter.social;

import javax.management.Notification;

import com.pmf.codejam.exception.SocialConnectionException;
import com.pmf.codejam.social.NotificationUserData;
import com.pmf.codejam.social.fb.FacebookPublisher;
import com.pmf.codejam.util.mail.SMTPSetting;


public class FacebookAdapter extends SocialAdapter {
	
	private String message;
	private String emailAddress;
	private FacebookPublisher fbPublisher;
	@Override
	public void post() throws SocialConnectionException {
		fbPublisher.setMessage(getMessage());
		fbPublisher.setMailAddress(getEmailAddress());
		fbPublisher.publishMessage();		
	}

	@Override
	public void authenticate() throws SocialConnectionException {
		// get from property file? or Parameter table?... TODO
		SMTPSetting smtpSetting = new SMTPSetting("smtp.gmail.com", 465, "puestodequipe.pmf@gmail.com", "ProgrammingMotherfucker$5");
		fbPublisher = new FacebookPublisher(getMessage(), smtpSetting.getUser(), smtpSetting);
		
	}

	@Override
	public void handleNotification(Notification notification, Object handback) {
		NotificationUserData notificationUserData = (NotificationUserData) notification.getUserData();
		setMessage(notificationUserData.getMessage());
		setEmailAddress("cereal589donut@m.facebook.com"); // parametrized TODO

		try {
			authenticate();
			post();
		} catch (SocialConnectionException e) {
			e.printStackTrace();
		}

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacebookAdapter other = (FacebookAdapter) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}
