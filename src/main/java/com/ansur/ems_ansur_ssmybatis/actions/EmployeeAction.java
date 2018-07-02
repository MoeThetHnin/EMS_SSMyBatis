package com.ansur.ems_ansur_ssmybatis.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.ansur.ems_ansur_ssmybatis.models.Transporation;
import com.ansur.ems_ansur_ssmybatis.models.TransporationMapper;
import com.ansur.ems_ansur_ssmybatis.models.Department;
import com.ansur.ems_ansur_ssmybatis.models.DepartmentMapper;
import com.ansur.ems_ansur_ssmybatis.models.EkiLine;

import com.ansur.ems_ansur_ssmybatis.models.EkiOperator;
import com.ansur.ems_ansur_ssmybatis.models.EkiOperatorMapper;
import com.ansur.ems_ansur_ssmybatis.models.Employee;
import com.ansur.ems_ansur_ssmybatis.models.EmployeeMapper;
import com.ansur.ems_ansur_ssmybatis.models.Post;
import com.ansur.ems_ansur_ssmybatis.models.PostMapper;
import com.opensymphony.xwork2.ActionSupport;

public final class EmployeeAction {

	@Autowired
	private EmployeeMapper empMapper;
	@Autowired
	private TransporationMapper tMapper;
	@Autowired
	private EkiOperatorMapper eoMapper;
	@Autowired
	private DepartmentMapper depMapper;
	@Autowired
	private PostMapper pMapper;

	private Employee employee;
	private EkiOperator ekiOperator;

	private List<Employee> employeeList;
	private List<Transporation> transpoList;
	private List<EkiLine> ekiLineList;
	private List<EkiOperator> ekiOperatorList;
	private List<Department> departmentList;
	private List<Post> postList;

	private String message_one;
	private String message_two;
	private String message_three;
	private String errorMessage_one;
	private String errorMessage_two;
	private String errorMessage_three;

	public String execute() {
		employeeList = empMapper.getAllEmployee();// DB����Ј����X�g����邽�߂�
		getTwoRequiredList();
		employee = new Employee();
		employee.setEmp_id(autoCreateEmpId());// �V�Ј���o�^���߂ɎЈ��ԍ��������I�ɏo�邽�߂�
		return ActionSupport.SUCCESS;
	}

	/* ���O�C���`�F�b�N */
	public String empLogin() {
		if (employee.getEmp_id().equals("admin") && employee.getPassword().equals("@nsur")) {
			return ActionSupport.LOGIN;
		}
		employee = empMapper.checkLoginData(getEmployee());
		try {
			if (!employee.equals(null)) {
				ekiOperatorList = eoMapper.getEONameAndNumberList();// �w�I�y���[�^�[���X�g����邽�߂�
				transpoList = tMapper.getTranspoListByEmpId(getEmployee().getEmp_id());// �Ј��̌�ʔ�̏�����邽�߂�
				setLogoNameList(transpoList);

				// ���`���[�W���z���v�Z
				// �c�����`���[�W���z�v�Z
				// �x�����\�����z�v�Z����
				try {
					int totalCharge = tMapper.totalCharge();
					int total = tMapper.totalKuru(getEmployee().getEmp_id())
							+ tMapper.totalKaeru(getEmployee().getEmp_id());
					employee.setTotal_charge(totalCharge);
					employee.setLeft_charge(totalCharge - total);
					employee.setTotal_pay(total);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return ActionSupport.SUCCESS;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		setErrorMessage_one("���[�U�[�����p�X���[�h���Ԉ���Ă��܂��B");
		return ActionSupport.ERROR;
	}

	// ���O�A�E�g�̂��߂�
	public String logout() {
		return ActionSupport.SUCCESS;
	}

	// �V�Ј���o�^���߂�
	public String empInsert() throws IOException {
		
		if(!empMapper.checkEmail(getEmployee().getEmail())) {
			empMapper.insertEmployee(getEmployee());
			employeeList = empMapper.getAllEmployee();
			getTwoRequiredList();
			clear();	
			return ActionSupport.SUCCESS;
		}
		
		employeeList = empMapper.getAllEmployee();
		getTwoRequiredList();
		setErrorMessage_one("���݂܂��񂪁B���̃��[���A�h���X�͂����o�^���܂����B");
		return ActionSupport.ERROR;
	}

	// �Ј��̏����폜���邽�߂�
	public String empDelete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		empMapper.deleteEmployee(Integer.valueOf(request.getParameter("id")));
		employeeList = empMapper.getAllEmployee();
		getTwoRequiredList();
		employee = new Employee();
		employee.setEmp_id(autoCreateEmpId());
		return ActionSupport.SUCCESS;
	}

	// �Ј��̏��̕ҏW��ʂ֍s���i�A�h�~���j
	public String empEditByAdminPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		employee = empMapper.getEmployeeByEmpId(request.getParameter("emp_id"));
		employeeList = empMapper.getAllEmployee();
		getTwoRequiredList();
		return ActionSupport.SUCCESS;
	}

	// �Ј��̏��̕ҏW��ʂ֍s���i���[�U�j
	public String empEditPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		employee = empMapper.getEmployeeByEmpId(request.getParameter("emp_id"));
		employeeList = empMapper.getAllEmployee();
		getTwoRequiredList();
		return ActionSupport.SUCCESS;
	}

	// �Ј��̏����X�V���邽�߂Ɂi�A�h�~���j
	public String empUpdateByAdmin() {
		if(getEmployee().getName().equals("")) {			
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			getTwoRequiredList();
			setErrorMessage_one("�Ԃ��u���v�̍��ڂ͕K�v�ł��B");
			return ActionSupport.ERROR;
		}
		empMapper.updateEmployeeByAdmin(getEmployee());
		employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());		
		getTwoRequiredList();
		setMessage_one("�X�V�ł��܂����B");
		return ActionSupport.SUCCESS;
	}

	public String empPasswordChangeByAdmin() {
		if (getEmployee().getPassword().equals("")) {
			setErrorMessage_three("�Ԃ��u���v�̍��ڂ͕K�v�ł��B");
		} else {
			empMapper.updateEmpPassword(getEmployee());
			setMessage_three("�p�X���[�h�X�V�ł��܂����B");
		}
		employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
		getTwoRequiredList();

		return ActionSupport.SUCCESS;
	}

	// �Ј��̏����X�V���邽�߂Ɂi���[�U�j
	public String empUpdate() {
		if (getEmployee().getName().equals("") || getEmployee().getEmail().equals("")
				|| getEmployee().getAddress().equals("")) {
			setErrorMessage_one("�Ԃ��u���v�̍��ڂ͕K�v�ł��B");
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			return ActionSupport.SUCCESS;
		}
		Employee emp = new Employee();
		try {
			emp = empMapper.checkUpdateEmail(getEmployee().getEmail());
			if (!emp.getEmp_id().equals(employee.getEmp_id())) {
				setErrorMessage_one("���̃��[���A�h���X�͂����o�^���܂����B");
				employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
				return ActionSupport.SUCCESS;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		empMapper.updateEmployee(getEmployee());
		setMessage_one("�Ј����X�V�ł��܂����B");
		employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
		return ActionSupport.SUCCESS;
	}

	public String empPasswordChange() {
		if (getEmployee().getOld_password().equals("") && getEmployee().getPassword().equals("")
				&& getEmployee().getConfirm_password().equals("")) {
			setErrorMessage_three("�Ԃ��u���v�̍��ڂ͕K�v�ł��B");
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			return ActionSupport.SUCCESS;
		}
		if (!empMapper.checkOldPassword(getEmployee())) {
			setErrorMessage_three("�ȑO�̃p�X���[�h���Ԉ���Ă��܂��B");
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			return ActionSupport.SUCCESS;
		}
		if (getEmployee().getPassword().equals("")) {
			setErrorMessage_three("�p�X���[�h�̐��͂��߂ĂW�����ł��B");
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			return ActionSupport.SUCCESS;
		}
		if (!getEmployee().getPassword().equals(getEmployee().getConfirm_password())) {
			setErrorMessage_three("�V�����p�X���[�h�Ɗm�F�p�X���[�h����v���Ȃ���΂Ȃ�܂���B");
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			return ActionSupport.SUCCESS;
		}
		empMapper.updateEmpPassword(getEmployee());
		employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
		setMessage_three("�p�X���[�h�ύX�ł��܂����B");
		return ActionSupport.SUCCESS;
	}

	public String empPhotoChange() {
		try {
			String filePath = "C:\\Users\\ansur02\\MawPaingThu\\FileServer\\EmployeeProfilePicture";
			File fileToCreate = new File(filePath, getEmployee().getUploadImageFileName());
			FileUtils.copyFile(getEmployee().getUploadImage(), fileToCreate);
			empMapper.updateImgName(getEmployee());
			setMessage_two("�ʐ^�̕ύX�ł��܂����B");
		} catch (Exception e) {
			// TODO: handle exception
			setErrorMessage_two("�ʐ^��I��ł��������B");
		}
		employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
		return ActionSupport.SUCCESS;
	}

	// �X�V����̃L�����Z�����߂�
	public String updateCancel() {
		HttpServletRequest request = ServletActionContext.getRequest();
		employee = empMapper.getEmployeeByEmpId(request.getParameter("emp_id"));
		ekiOperatorList = eoMapper.getEONameAndNumberList();
		transpoList = tMapper.getTranspoListByEmpId(getEmployee().getEmp_id());
		setLogoNameList(transpoList);
		try {
			int totalCharge = tMapper.totalCharge();
			int total = tMapper.totalKuru(getEmployee().getEmp_id()) + tMapper.totalKaeru(getEmployee().getEmp_id());
			employee.setTotal_charge(totalCharge);
			employee.setLeft_charge(totalCharge - total);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ActionSupport.SUCCESS;
	}

	private void clear() {
		// TODO Auto-generated method stub
		employee.setEmp_id(autoCreateEmpId());
		employee.setName("");
		employee.setDepartment("");
		employee.setPost("");
		employee.setStatus("");
		employee.setEmail("");
		employee.setAddress("");
	}

	// �V�Ј���o�^���߂ɎЈ��ԍ�����邱��
	private String autoCreateEmpId() {
		// TODO Auto-generated method stub
		String nextEmpId = "ansur001";
		String empIdPrefix = "ansur";
		try {
			String lastEmpId = empMapper.getLastEmpId();
			int idNum = Integer.valueOf(lastEmpId.split("r")[1]) + 1;
			if (idNum > 99) {
				nextEmpId = empIdPrefix.concat(String.valueOf(idNum));
			} else if (idNum > 9) {
				nextEmpId = empIdPrefix.concat("0" + String.valueOf(idNum));
			} else {
				nextEmpId = empIdPrefix.concat("00" + String.valueOf(idNum));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return nextEmpId;
	}

	private void getTwoRequiredList() {
		// TODO Auto-generated method stub
		departmentList = depMapper.getDepartmentList();// DB���畔�僊�X�g����邽�߂�
		postList = pMapper.getPostList();// DB�����E���X�g����邽�߂�
	}

	private void setLogoNameList(List<Transporation> transpoList2) {
		// TODO Auto-generated method stub
		for (Transporation t : transpoList) {
			List<String> logo = new ArrayList<String>(Arrays.asList(t.getT_line().split(",\\s*")));
			t.setT_logo_name(logo);
		}
	}

	/* �Q�b�^�[�A�Z�b�^�[ */
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public List<Transporation> getTranspoList() {
		return transpoList;
	}

	public void setTranspoList(List<Transporation> transpoList) {
		this.transpoList = transpoList;
	}

	public List<EkiLine> getEkiLineList() {
		return ekiLineList;
	}

	public void setEkiLineList(List<EkiLine> ekiLineList) {
		this.ekiLineList = ekiLineList;
	}

	public EkiOperator getEkiOperator() {
		return ekiOperator;
	}

	public void setEkiOperator(EkiOperator ekiOperator) {
		this.ekiOperator = ekiOperator;
	}

	public List<EkiOperator> getEkiOperatorList() {
		return ekiOperatorList;
	}

	public void setEkiOperatorList(List<EkiOperator> ekiOperatorList) {
		this.ekiOperatorList = ekiOperatorList;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public String getMessage_one() {
		return message_one;
	}

	public void setMessage_one(String message_one) {
		this.message_one = message_one;
	}

	public String getMessage_two() {
		return message_two;
	}

	public void setMessage_two(String message_two) {
		this.message_two = message_two;
	}

	public String getMessage_three() {
		return message_three;
	}

	public void setMessage_three(String message_three) {
		this.message_three = message_three;
	}

	public String getErrorMessage_one() {
		return errorMessage_one;
	}

	public void setErrorMessage_one(String errorMessage_one) {
		this.errorMessage_one = errorMessage_one;
	}

	public String getErrorMessage_two() {
		return errorMessage_two;
	}

	public void setErrorMessage_two(String errorMessage_two) {
		this.errorMessage_two = errorMessage_two;
	}

	public String getErrorMessage_three() {
		return errorMessage_three;
	}

	public void setErrorMessage_three(String errorMessage_three) {
		this.errorMessage_three = errorMessage_three;
	}

}