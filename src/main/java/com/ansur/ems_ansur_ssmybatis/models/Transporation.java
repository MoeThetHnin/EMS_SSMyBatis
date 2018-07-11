package com.ansur.ems_ansur_ssmybatis.models;

import java.util.Date;
import java.util.List;

public class Transporation {
	private int t_id;
	private String emp_id;
	private Date t_date;
	private String t_operator;
	private String t_line;
	private String t_kuru_eki;
	private String t_kaeru_eki;
	private int t_charge;
	private String t_purpose;
	private int t_kuru_charge;
	private int t_kaeru_charge;
	private String t_remarks;
	private String t_commutingType;
	private int t_commutingFee;
	
	private List<String> t_logo_name;
	
	
	
	
	
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Date getT_date() {
		return t_date;
	}
	public void setT_date(Date t_date) {
		this.t_date = t_date;
	}
	public String getT_operator() {
		return t_operator;
	}
	public void setT_operator(String t_operator) {
		this.t_operator = t_operator;
	}
	public String getT_line() {
		return t_line;
	}
	public void setT_line(String t_line) {
		this.t_line = t_line;
	}
	public String getT_kuru_eki() {
		return t_kuru_eki;
	}
	public void setT_kuru_eki(String t_kuru_eki) {
		this.t_kuru_eki = t_kuru_eki;
	}
	public String getT_kaeru_eki() {
		return t_kaeru_eki;
	}
	public void setT_kaeru_eki(String t_kaeru_eki) {
		this.t_kaeru_eki = t_kaeru_eki;
	}
	public int getT_charge() {
		return t_charge;
	}
	public void setT_charge(int t_charge) {
		this.t_charge = t_charge;
	}
	public String getT_purpose() {
		return t_purpose;
	}
	public void setT_purpose(String t_purpose) {
		this.t_purpose = t_purpose;
	}
	public int getT_kuru_charge() {
		return t_kuru_charge;
	}
	public void setT_kuru_charge(int t_kuru_charge) {
		this.t_kuru_charge = t_kuru_charge;
	}
	public int getT_kaeru_charge() {
		return t_kaeru_charge;
	}
	public void setT_kaeru_charge(int t_kaeru_charge) {
		this.t_kaeru_charge = t_kaeru_charge;
	}
	public String getT_remarks() {
		return t_remarks;
	}
	public void setT_remarks(String t_remarks) {
		this.t_remarks = t_remarks;
	}
	public List<String> getT_logo_name() {
		return t_logo_name;
	}
	public void setT_logo_name(List<String> t_logo_name) {
		this.t_logo_name = t_logo_name;
	}
	public String getT_commutingType() {
		return t_commutingType;
	}
	public void setT_commutingType(String t_commutingType) {
		this.t_commutingType = t_commutingType;
	}
	public int getT_commutingFee() {
		return t_commutingFee;
	}
	public void setT_commutingFee(int t_commutingFee) {
		this.t_commutingFee = t_commutingFee;
	}
	
	
}