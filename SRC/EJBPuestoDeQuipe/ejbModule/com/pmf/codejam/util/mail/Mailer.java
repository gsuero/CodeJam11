package com.pmf.codejam.util.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Mailer {

	private String from;
	private String to;
	private String cc;
	private String subject;
	private String text;
	private String contentType = "text/html;charset=iso-8859-1";
	private SMTPSetting smtpSetting;
	
	public Mailer(String from, String to, String subject, String text){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
	}
	
	public Mailer(String from, String to, String cc, String subject, String text, String contentType){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.cc  = cc;
		this.contentType =  contentType;
	}
	
	public Mailer(String from, String to, String cc, String subject, String text){
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.text = text;
		this.cc =  cc;
	}
	
	
	public SMTPSetting getSmtpSetting() {
		return smtpSetting;
	}

	public void setSmtpSetting(SMTPSetting smtpSetting) {
		this.smtpSetting = smtpSetting;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public synchronized void send() throws MessagingException {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpSetting.getHostName());
		props.put("mail.smtp.port", smtpSetting.getPort());
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtps.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", smtpSetting.getHostName());
		props.put("mail.smtp.socketFactory.port", smtpSetting.getPort());
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");


	    Authenticator auth = new SMTPAuthenticator();
	    Session session = Session.getDefaultInstance(props, auth);
		
		Message simpleMessage = new MimeMessage(session);
		
		InternetAddress fromAddress = null;
		InternetAddress toAddress = null;
		InternetAddress ccAddress = null;
		try {
			fromAddress = new InternetAddress(from);
			toAddress = new InternetAddress(to);
			if (cc != null && cc.length() > 0)
			ccAddress = new InternetAddress(cc);
		} catch (AddressException e) {
			e.printStackTrace();
		}
		
		try {
			simpleMessage.setFrom(fromAddress);
			simpleMessage.setRecipient(RecipientType.TO, toAddress);
			if (ccAddress != null)
				simpleMessage.setRecipient(RecipientType.CC, ccAddress);
			simpleMessage.setSubject(subject);
			simpleMessage.setContent(text, contentType);
			Transport.send(simpleMessage);			
		} catch (MessagingException e) {
			throw e;
		}		
	}
	
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{

	    public PasswordAuthentication getPasswordAuthentication()
	    {
	    	String username = "";
	    	if (smtpSetting.getUser() != null)
	    		username = smtpSetting.getUser();
	    	
	    	String password = "";
	    	if (smtpSetting.getPassword() != null)
	    		password = smtpSetting.getPassword();
	    	
	        return new PasswordAuthentication(username, password);
	    }
	}

}
