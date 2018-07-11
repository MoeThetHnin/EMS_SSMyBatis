package com.ansur.ems_ansur_ssmybatis.actions;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	private List<Transporation> monthlyTranspoList;
	
	private List<EkiOperator> ekiOperatorList;
	
	private Employee employee;
	
	private List<String> subwayOperators;
	
	private List<EkiLine> ekiLineListMetro;
	
	private List<EkiLine> ekiLineListToei;
	
	private int total;
	
	
	DateFormat monthFormat = new SimpleDateFormat("MM");
	DateFormat yearFormat = new SimpleDateFormat("yyyy");
	String month = monthFormat.format(new Date());
	String year = yearFormat.format(new Date());
	

	
	public String transportInsert() {
		
		
		
		int amount = transpo.getT_commutingFee()*Integer.valueOf(transpo.getT_commutingType());
		
		
		transpo.setT_commutingFee(amount);
		tMapper.insertTranspoInfo(getTranspo());
		ekiOperatorList = eoMapper.getEONameAndNumberList();
		monthlyTranspoList = tMapper.getMonthlyTranspoListByEmpId(Integer.valueOf(month), Integer.valueOf(year),transpo.getEmp_id());
		
		employee = empMapper.getEmployeeByEmpId(getTranspo().getEmp_id());
		
		transpo.setT_line("");
		transpo.setT_operator("");
	
		int totalCharge = tMapper.totalCharge(Integer.valueOf(month), Integer.valueOf(year), transpo.getEmp_id());
		int totalFee = tMapper.totalFee(Integer.valueOf(month), Integer.valueOf(year), transpo.getEmp_id());
		/*int total = tMapper.totalKuru(Integer.valueOf(month), Integer.valueOf(year), transpo.getEmp_id())
				+ tMapper.totalKaeru(Integer.valueOf(month), Integer.valueOf(year), transpo.getEmp_id());*/
		employee.setTotal_charge(totalCharge);
		/*employee.setLeft_charge(totalCharge-total);*/
		employee.setTotal_pay(totalFee);
		
		return ActionSupport.SUCCESS;
	}



	public Transporation getTranspo() {
		return transpo;
	}

	public void setTranspo(Transporation transpo) {
		this.transpo = transpo;
	}
	
	
	
	
	
	
	public List<Transporation> getMonthlyTranspoList() {
		return monthlyTranspoList;
	}



	public void setMonthlyTranspoList(List<Transporation> monthlyTranspoList) {
		this.monthlyTranspoList = monthlyTranspoList;
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
