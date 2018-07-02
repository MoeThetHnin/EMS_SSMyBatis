package com.ansur.ems_ansur_ssmybatis.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ansur.ems_ansur_ssmybatis.models.Department;
import com.ansur.ems_ansur_ssmybatis.models.DepartmentMapper;
import com.opensymphony.xwork2.ActionSupport;

public class DepartmentAction {

	@Autowired
	private DepartmentMapper depMapper;

	private Department department;

	private List<Department> departmentList;

	private String message_one;
	private String errorMessage_one;

	public String execute() {
		departmentList = depMapper.getDepartmentList();
		department = new Department();
		department.setDep_number(getAutoDepNumber());
		return ActionSupport.SUCCESS;
	}

	// 部門作成のために
	public String departmentInsert() {
		
		if (getDepartment().getDep_name().equals("")) {
			setErrorMessage_one("赤い「＊」の項目は必要です。");
			departmentList = depMapper.getDepartmentList();
			return ActionSupport.SUCCESS;
		}
		if (depMapper.checkAlreadyExist(getDepartment().getDep_name())) {
			setErrorMessage_one("この部門はもう登録しました。");
			departmentList = depMapper.getDepartmentList();
			return ActionSupport.SUCCESS;
		}
		depMapper.insertDepartment(getDepartment());
		departmentList = depMapper.getDepartmentList();
		clear();
		return ActionSupport.SUCCESS;
	}

	public String departmentEditPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		department = depMapper.getDepartmentById(Integer.valueOf(request.getParameter("id")));
		return ActionSupport.SUCCESS;
	}

	public String departmentUpdate() {
		if (getDepartment().getDep_name().equals("")) {
			setErrorMessage_one("赤い「＊」の項目は必要です。");
			department = depMapper.getDepartmentById(Integer.valueOf(getDepartment().getDep_id()));
			return ActionSupport.ERROR;
		}
		Department dep = new Department();
		try {
			dep = depMapper.getDepartmentByName(getDepartment().getDep_name());
			if (!Integer.valueOf(dep.getDep_id()).equals(department.getDep_id())) {
				setErrorMessage_one("この部門はもう登録しました。");
				department = depMapper.getDepartmentById(Integer.valueOf(getDepartment().getDep_id()));
				return ActionSupport.ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		depMapper.updateDepartment(getDepartment());
		setMessage_one("更新できました。");
		department = depMapper.getDepartmentById(Integer.valueOf(getDepartment().getDep_id()));
		return ActionSupport.SUCCESS;
	}
	
	public String departmentDelete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		depMapper.deleteById(Integer.valueOf(request.getParameter("id")));
		departmentList = depMapper.getDepartmentList();
		department = new Department();
		department.setDep_number(getAutoDepNumber());
		return ActionSupport.SUCCESS;
	}

	// 部門番号を作るために
	private String getAutoDepNumber() {
		// TODO Auto-generated method stub
		String nextDepNum = "dep01";
		String depNumPrefix = "dep";
		String depNumSuffix;
		try {
			int lastDepNum = Integer.valueOf(depMapper.getLastDepNum().split("p")[1]) + 1;
			depNumSuffix = (lastDepNum < 9) ? "0" + String.valueOf(lastDepNum) : String.valueOf(lastDepNum);
			nextDepNum = depNumPrefix.concat(depNumSuffix);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return nextDepNum;
	}

	private void clear() {
		// TODO Auto-generated method stub
		department.setDep_number(getAutoDepNumber());
		department.setDep_name("");
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getMessage_one() {
		return message_one;
	}

	public void setMessage_one(String message_one) {
		this.message_one = message_one;
	}

	public String getErrorMessage_one() {
		return errorMessage_one;
	}

	public void setErrorMessage_one(String errorMessage_one) {
		this.errorMessage_one = errorMessage_one;
	}

}
