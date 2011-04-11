package com.pmf.codejam.adapter.social;

import javax.management.Notification;

import twitter4j.TwitterException;

import com.pmf.codejam.exception.SocialConnectionException;
import com.pmf.codejam.social.NotificationUserData;
import com.pmf.codejam.social.twitter.TwitterPublisher;

public class TwitterAdapter extends SocialAdapter {

	private TwitterPublisher publisher;
	private String message;
	@Override
	public void post() throws SocialConnectionException {
		publisher.setMessage(message);
		try {
			publisher.publish();
		} catch (NullPointerException e) {
			throw new SocialConnectionException(e.getMessage());
		} catch (TwitterException e) {
			throw new SocialConnectionException(e.getMessage());
		}
	}

	@Override
	public void authenticate() throws SocialConnectionException {
		publisher = new TwitterPublisher();
	}

	@Override
	public void handleNotification(Notification notification, Object handback) {
		NotificationUserData notificationUserData = (NotificationUserData) notification.getUserData();
		this.message = notificationUserData.getMessage();
		try {
			this.authenticate();
			this.post();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		TwitterAdapter other = (TwitterAdapter) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
}
