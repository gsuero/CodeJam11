/**
 * 
 */
package com.pmf.codejam.adapter.social;

import javax.management.Notification;

import com.pmf.codejam.exception.SocialConnectionException;

/**
 * @author Frederick
 *
 */
public class SMSAdapter extends SocialAdapter {

	/**
	 * 
	 */
	public SMSAdapter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see javax.management.NotificationListener#handleNotification(javax.management.Notification, java.lang.Object)
	 */
	@Override
	public void handleNotification(Notification notification, Object handback) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.pmf.codejam.adapter.social.SocialAdapter#post()
	 */
	@Override
	public void post() throws SocialConnectionException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.pmf.codejam.adapter.social.SocialAdapter#authenticate()
	 */
	@Override
	public void authenticate() throws SocialConnectionException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.pmf.codejam.adapter.social.SocialAdapter#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.pmf.codejam.adapter.social.SocialAdapter#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
