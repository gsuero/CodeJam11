package com.pmf.codejam.web.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.pmf.codejam.util.Constants;
import com.pmf.codejam.util.UserHelper;

public class LoginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public String execute() throws Exception {

		return SUCCESS;
	}
	public String doLogin() throws Exception {
		if(UserHelper.isValidUserAdmin(getLogin(), getPassword())){
			HttpSession session = request.getSession(true);
			session.setAttribute(Constants.GLOBAL_USER_SESSION_KEY, "Admin");
			session.setMaxInactiveInterval(18000);
			addActionMessage("Inicio de sesion correctamente");
			return SUCCESS;
		} else {
			addActionError("Login incorrecto...");
			return ERROR;
		}
	}
	public String doLogOut() throws Exception {
			HttpSession session = request.getSession();
			session.removeAttribute(Constants.GLOBAL_USER_SESSION_KEY);
			return SUCCESS;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	
	
}
