package com.pmf.codejam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.pmf.codejam.util.Constants;

public class AuthorizeInterceptor extends AbstractInterceptor  {
	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
	    final ActionContext context = invocation.getInvocationContext ();
	    HttpServletRequest request = (HttpServletRequest) context.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	    HttpSession session =  request.getSession(true);

		if(session.getAttribute(Constants.GLOBAL_USER_SESSION_KEY) == null) {
			addActionError(invocation, "Debe iniciar sesion para poder accesar.");
			return Action.LOGIN;
		} 
		return invocation.invoke();
	}
	

	private void addActionError(ActionInvocation invocation, String message) {
		Object action = invocation.getAction();
		if(action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(message);
		}
	}

}