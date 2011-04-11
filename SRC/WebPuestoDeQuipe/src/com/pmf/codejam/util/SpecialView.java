package com.pmf.codejam.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SpecialView {

	private int specialId;
	private String description;
	private String summary;
	private Calendar expirationDate;
	private List<String> socialNetworks;

	
	
	public SpecialView(String description, String summary,
			Calendar expirationDate, List<String> socialNetworks) {
		super();
		this.description = description;
		this.summary = summary;
		this.expirationDate = expirationDate;
		this.socialNetworks = socialNetworks;
	}
	
	
	public SpecialView(String description, String summary,
			Calendar expirationDate) {
		super();
		this.description = description;
		this.summary = summary;
		this.expirationDate = expirationDate;
	}


	public SpecialView(int specialId, String description, String summary,
			Calendar expirationDate, List<String> socialNetworks) {
		super();
		this.specialId = specialId;
		this.description = description;
		this.summary = summary;
		this.expirationDate = expirationDate;
		this.socialNetworks = socialNetworks;
	}


	public int getSpecialId() {
		return specialId;
	}

	public void setSpecialId(int specialId) {
		this.specialId = specialId;
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
	public Calendar getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}
	public List<String> getSocialNetworks() {
		return socialNetworks;
	}
	public void setSocialNetworks(List<String> socialNetworks) {
		this.socialNetworks = socialNetworks;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + specialId;
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
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
		SpecialView other = (SpecialView) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (specialId != other.specialId)
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		return true;
	}
	
	
}
