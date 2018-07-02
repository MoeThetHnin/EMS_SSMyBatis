package com.ansur.ems_ansur_ssmybatis.actions;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ansur.ems_ansur_ssmybatis.models.Transporation;
import com.ansur.ems_ansur_ssmybatis.models.TransporationMapper;
import com.ansur.ems_ansur_ssmybatis.models.EkiLine;
import com.ansur.ems_ansur_ssmybatis.models.EkiOperator;
import com.ansur.ems_ansur_ssmybatis.models.EkiOperatorMapper;
import com.ansur.ems_ansur_ssmybatis.models.Employee;
import com.ansur.ems_ansur_ssmybatis.models.EmployeeMapper;
import com.opensymphony.xwork2.ActionSupport;

public class TransporationAction {

	@Autowired
	private TransporationMapper tMapper;
	
	@Autowired
	private EmployeeMapper empMapper;
	
	@Autowired
	private EkiOperatorMapper eoMapper;
	


	
	

	private Transporation transpo;
	
	private List<Transporation> transpoList;
	
	private List<EkiOperator> ekiOperatorList;
	
	private Employee employee;
	
	private List<String> subwayOperators;
	
	private List<EkiLine> ekiLineListMetro;
	
	private List<EkiLine> ekiLineListToei;
	
	private int total;
	

	//åí îÔèäñ@çÏê¨Ç∑ÇÈÇΩÇﬂÇ…
	public String transportInsert() {
		tMapper.insertTranspoInfo(getTranspo());
		ekiOperatorList = eoMapper.getEONameAndNumberList();
		transpoList = tMapper.getTranspoListByEmpId(getTranspo().getEmp_id());
		
		employee = empMapper.getEmployeeByEmpId(getTranspo().getEmp_id());
		
		transpo.setT_line("");
		transpo.setT_operator("");
	
		int totalCharge = tMapper.totalCharge();
		int total = tMapper.totalKuru(getEmployee().getEmp_id())+tMapper.totalKaeru(getEmployee().getEmp_id());
		employee.setTotal_charge(totalCharge);
		employee.setLeft_charge(totalCharge-total);
	
	
		return ActionSupport.SUCCESS;
	}



	public Transporation getTranspo() {
		return transpo;
	}

	public void setTranspo(Transporation transpo) {
		this.transpo = transpo;
	}
	
	
	public List<Transporation> getTranspoList() {
		return transpoList;
	}

	public void setTranspoList(List<Transporation> transpoList) {
		this.transpoList = transpoList;
	}
	
	
	
	public List<String> getSubwayOperators() {
		return subwayOperators;
	}

	public void setSubwayOperators(List<String> subwayOperators) {
		this.subwayOperators = subwayOperators;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



	public List<EkiLine> getEkiLineListMetro() {
		return ekiLineListMetro;
	}



	public void setEkiLineListMetro(List<EkiLine> ekiLineListMetro) {
		this.ekiLineListMetro = ekiLineListMetro;
	}



	public List<EkiLine> getEkiLineListToei() {
		return ekiLineListToei;
	}



	public void setEkiLineListToei(List<EkiLine> ekiLineListToei) {
		this.ekiLineListToei = ekiLineListToei;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public List<EkiOperator> getEkiOperatorList() {
		return ekiOperatorList;
	}



	public void setEkiOperatorList(List<EkiOperator> ekiOperatorList) {
		this.ekiOperatorList = ekiOperatorList;
	}
	
	

	
	
}
