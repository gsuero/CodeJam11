package com.pmf.codejam.social.fb;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;

import com.pmf.codejam.util.mail.Mailer;
import com.pmf.codejam.util.mail.SMTPSetting;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;

public class FacebookPublisher {
	private FacebookClient facebookClient;
	private String message;
	private String mailAddress;
	private LinkedList<InputStream> pictures;
	private SMTPSetting SMTPSettings;
	private final String facebookEmailUpdater = "cereal589donut@m.facebook.com";
	public FacebookPublisher(String accessToken) {
		facebookClient = new DefaultFacebookClient(accessToken);
	}

	public FacebookPublisher(String message, String mailAddress, SMTPSetting settings) {
		super();
		setMessage(message);
		setMailAddress(mailAddress);
		setSMTPSettings(settings);
	}
	
	public void updateStatusViaMail() throws MessagingException {
		Mailer mailer =  new Mailer(SMTPSettings.getUser(), facebookEmailUpdater, message, "");
		mailer.send();
	}
	public String publishMessage() {
		FacebookType publishMessageResponse = facebookClient.publish("me/feed",
				FacebookType.class, Parameter.with("message", message));
		return publishMessageResponse.getId();
	}

	public String publishEvent(Calendar startTime, Calendar endTime, String eventName) {
		FacebookType publishEventResponse = facebookClient.publish("me/events",
				FacebookType.class, Parameter.with("name", eventName),
				Parameter.with("start_time", startTime.getTime()),
				Parameter.with("end_time", endTime));
		return publishEventResponse.getId();
	}

	public List<String> publishPictures() {
		List<String> ids = new ArrayList<String>();
		if (pictures != null && pictures.size() > 0) {
		Iterator<InputStream> it = pictures.iterator();
		while (it.hasNext()) {
			InputStream picture = it.next();
			FacebookType publishPhotoResponse = facebookClient.publish("me/photos",
					FacebookType.class, picture,
					Parameter.with("message", message));
				ids.add(publishPhotoResponse.getId());
			}
		}
		return ids;
	}

	public void delete(String objectId) {
		facebookClient.deleteObject(objectId);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public LinkedList<InputStream> getPictures() {
		return pictures;
	}

	public void setPictures(LinkedList<InputStream> pictures) {
		this.pictures = pictures;
	}
	public void addPicture(InputStream picture) {
		if (pictures == null) 
			pictures = new LinkedList<InputStream>();
		
		pictures.add(picture);
	}

	public SMTPSetting getSMTPSettings() {
		return SMTPSettings;
	}

	public void setSMTPSettings(SMTPSetting sMTPSettings) {
		SMTPSettings = sMTPSettings;
	}
}
