package com.pmf.codejam.social;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.management.Notification;
import javax.management.NotificationListener;

import com.pmf.codejam.entity.Special;
import com.pmf.codejam.exception.SpecialExpiredException;
import com.pmf.codejam.util.EjbConstants;

public class SpecialsPublisher {
    private Long id;
    private String description;
    private String summary;
    private List<NotificationListener> listeners;
    
    public SpecialsPublisher(Special special) throws SpecialExpiredException {
    	super();

    	load(special);
    	listeners = new ArrayList<NotificationListener>();
    }
    public void load(Special special) {
    	setId(special.getId());
    	setDescription(special.getDescription());
    	setSummary(special.getSummary());
    }
    
    public void addListener(NotificationListener listener) {
    	listeners.add(listener);
    }
    
    public void notifyListeners() {
    	Iterator<NotificationListener> it = listeners.iterator();
    	while(it.hasNext()) {
    		NotificationListener listener = it.next();
    		Notification notification = new Notification(EjbConstants.NOTIFICATION_SOCIAL_TYPE, EjbConstants.NOTIFICATION_SOCIAL_SOURCE, id);
    		NotificationUserData userData = new NotificationUserData(description, summary);
    		notification.setUserData(userData);
    		listener.handleNotification(notification, new Object()); // Special.class ?? to be called Special.failedNotification(Message)
    	}
    }

    public void removeListener(NotificationListener listener) {
    	listeners.remove(listener);
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<NotificationListener> getListeners() {
		return listeners;
	}

	public void setListeners(List<NotificationListener> listeners) {
		this.listeners = listeners;
	}
    
    
    
   
}
