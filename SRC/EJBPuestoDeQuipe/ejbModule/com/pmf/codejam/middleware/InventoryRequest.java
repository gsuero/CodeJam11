package com.pmf.codejam.middleware;

import java.util.*;


public class InventoryRequest {

	private int id;
	private int clientId;
	private String authKey;
	private Set<RequestItem> requestItems;
	
	//Getters and Setters
	public int getId() {
		return id;
	}		
	public void setId(int id) {
		this.id = id;
	}		
	public int getClientId() {
		return clientId;
	}		
	public void setClientId(int id) {
		this.clientId = id;
	}
	public String getAuthKey() {
		return authKey;
	}		
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}		
	public Set<RequestItem> getRequestItems(){
		return requestItems;
	}
	public void setRequestItem(Set<RequestItem> requestItems){
		this.requestItems = requestItems;
	}

    /**
	 * Add a requestItem to the RequestItem list
	 */
	public void addItem(RequestItem requestItem){
		requestItems.add(requestItem);
	}	
}
