package com.ansur.ems_ansur_ssmybatis.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.ansur.ems_ansur_ssmybatis.models.Employee;
import com.opensymphony.xwork2.ActionSupport;

public class HelloAction {
	
	private String message;
	
	
	
	

	@Action(value = "/", results = {@Result(location = "/WEB-INF/jsp/hello.jsp")})
	public String hello() {
		setMessage("Welcome To Ansur Emplyoee Management System");
		return ActionSupport.SUCCESS;
	}
	
	
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
