package com.pmf.codejam.adapter.social;


import javax.management.NotificationListener;
import com.pmf.codejam.adapter.AdapterStatus;
import com.pmf.codejam.exception.SocialConnectionException;

public abstract class SocialAdapter implements NotificationListener {
	private AdapterStatus adapterStatus;
	public abstract void post() throws SocialConnectionException;
	public abstract void authenticate() throws SocialConnectionException;
	public AdapterStatus getAdapterStatus() {
		return adapterStatus;
	}
	public void setAdapterStatus(AdapterStatus status) {
		this.adapterStatus = status;
	}
	public void waiting() {
		this.adapterStatus = AdapterStatus.WAITING;
	}
	public void ready() {
		this.adapterStatus = AdapterStatus.READY;
	}
	public void skip() {
		this.adapterStatus = AdapterStatus.SKIPPED;
	}
	public void complete() {
		this.adapterStatus = AdapterStatus.COMPLETED;
	}
	@Override
	public abstract int hashCode();
	
	@Override
	public abstract boolean equals(Object obj);

	
	
	
	
	
	
}
