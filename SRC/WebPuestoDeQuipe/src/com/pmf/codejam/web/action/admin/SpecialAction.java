package com.pmf.codejam.web.action.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionSupport;
import com.pmf.codejam.util.Constants;
import com.pmf.codejam.util.DataLayerUtil;
import com.pmf.codejam.util.SpecialView;

public class SpecialAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	private String description;
	private String summary;
	private String expirationDate;
	private List<String> socialNetworks;
	private String social;

	public SpecialAction() {
		socialNetworks = new ArrayList<String>();
		socialNetworks.add(Constants.SOCIAL_FACEBOOK);
		socialNetworks.add(Constants.SOCIAL_TWITTER);
	}
	public String save() throws Exception {
		
		try {
			Calendar calendar = null;
			if (expirationDate != null && expirationDate.length() > 0) {
				calendar = Calendar.getInstance();
				calendar.setTime(Constants.COMMON_DATE_FORMAT.parse(expirationDate));
			}
			if (summary == null || summary.length() > Constants.SOCIAL_SUMMARY_LENGTH || description == null || description.length() > Constants.SOCIAL_TWITTER_LENGTH) 
				throw new Exception();
			
			SpecialView special = new SpecialView(getDescription(), getSummary(), calendar, Arrays.asList(social.split(",")));
			DataLayerUtil.addSpecial(special);
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			addActionError("Error en los datos");
			return ERROR;
		}
		return SUCCESS;
	}
	
	
	public String execute() throws Exception {
		
		return SUCCESS;
	}
	
	
	
	
	public List<String> getSocialNetworks() {
		return socialNetworks;
	}
	public void setSocialNetworks(List<String> socialNetworks) {
		this.socialNetworks = socialNetworks;
	}
	public String getSocial() {
		return social;
	}
	public void setSocial(String social) {
		this.social = social;
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




	public String getExpirationDate() {
		return expirationDate;
	}




	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}




	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}
}
