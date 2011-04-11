package com.pmf.codejam.social.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterPublisher {
	// to be parametrized TODO
	private static final String CONSUMER_KEY = "Cwf89DKPxyOF6P0a2HqEHA";
	private static final String CONSUMER_SECRET = "U1S5oyUEohmcxM5Ex6Po0Wgu7lWOUOknTDk89Jtg";
	private static final String ACCESS_TOKEN = "278827322-RF6Ja8I1dW0zQSlMSo9VJ02MgQ2AzvFXquguUVEo";
	private static final String ACCESS_TOKEN_SECRET = "jSqdjRyL8wGpXWLIEPbCbOfamIVr2HFdVynLmGJTP8 ";
	private ConfigurationBuilder cb;
	private Twitter twitter;
	private String message;

	/*
	 * Access Token: 278827322-RF6Ja8I1dW0zQSlMSo9VJ02MgQ2AzvFXquguUVEo Access
	 * Token Secret: jSqdjRyL8wGpXWLIEPbCbOfamIVr2HFdVynLmGJTP8
	 */

	public TwitterPublisher() {
		this.cb = new ConfigurationBuilder();
		this.cb.setOAuthConsumerKey(CONSUMER_KEY);
		this.cb.setOAuthConsumerSecret(CONSUMER_SECRET);
		this.cb.setOAuthAccessToken(ACCESS_TOKEN);
		this.cb.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
		this.cb.setUser("PuestoDeQuipePMF");
		this.cb.setPassword("ProgrammingMotherfucker$5");
		OAuthAuthorization auth = new OAuthAuthorization(cb.build());
		this.twitter =  new TwitterFactory().getInstance(auth);
	}

	
	public void publish() throws NullPointerException, TwitterException {
		try {
			if (this.message == null || this.message.length() == 0) 
				throw new NullPointerException("Twitter Message is empty...");
			twitter.updateStatus(this.message);
		} catch (TwitterException e) {
			throw e;
		}
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		if (message != null && message.length() > 140) 
			this.message = message.substring(0, 140);
		
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
		TwitterPublisher other = (TwitterPublisher) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	
}
