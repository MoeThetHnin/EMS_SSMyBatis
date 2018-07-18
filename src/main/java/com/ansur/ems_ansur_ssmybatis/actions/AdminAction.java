package com.ansur.ems_ansur_ssmybatis.actions;

import com.ansur.ems_ansur_ssmybatis.models.Admin;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction {
	
	private Admin admin;

	public String execute() {

		return ActionSupport.SUCCESS;
	}

	public String welcome() {

		return ActionSupport.SUCCESS;
	}

	public String login() {
		if (admin.getAdmin_id().equals("admin") && admin.getAdmin_password().equals("@nsur"))
			
			return ActionSupport.SUCCESS;

		return ActionSupport.ERROR;
	}
	
	

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
}
