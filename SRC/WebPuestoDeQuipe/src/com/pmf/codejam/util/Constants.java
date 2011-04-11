package com.pmf.codejam.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface Constants {
	
	public final DateFormat COMMON_DATE_FORMAT = new SimpleDateFormat("MM/dd/yy"); // 10/22/84
	public final DateFormat COMMON_FULL_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	public final String GLOBAL_USER_SESSION_KEY = "USER";
	public final String SOCIAL_FACEBOOK = "Facebook";
	public final String SOCIAL_TWITTER = "Twitter";
	public final int 	SOCIAL_TWITTER_LENGTH = 140;
	public final int SOCIAL_SUMMARY_LENGTH = 1000;
}
