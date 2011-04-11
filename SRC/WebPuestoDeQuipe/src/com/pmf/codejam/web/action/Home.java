package com.pmf.codejam.web.action;

import java.util.logging.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class Home extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(Home.class.getName());
	public String execute() throws Exception {

		return SUCCESS;
	}
}
