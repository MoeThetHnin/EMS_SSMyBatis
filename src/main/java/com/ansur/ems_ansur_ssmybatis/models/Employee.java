package com.ansur.ems_ansur_ssmybatis.models;

import java.io.File;

public final class Employee {

	private int id;
	private String emp_id;
	private String name;
	private String password;
	private String email;
	private String img_name;
	private String address;
	private int total_charge;
	private String department;
	private String post;
	private String status;
	
	
	private int left_charge;
	private int total_pay;
	
	private File uploadImage;
	private String uploadImageContentType;
	private String uploadImageFileName;
	
	private String old_password;
	private String new_password;
	private String confirm_password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getImg_name() {
		return img_name;
	}
	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotal_charge() {
		return total_charge;
	}
	public void setTotal_charge(int total_charge) {
		this.total_charge = total_charge;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLeft_charge() {
		return left_charge;
	}
	public void setLeft_charge(int left_charge) {
		this.left_charge = left_charge;
	}
	public int getTotal_pay() {
		return total_pay;
	}
	public void setTotal_pay(int total_pay) {
		this.total_pay = total_pay;
	}
	public File getUploadImage() {
		return uploadImage;
	}
	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}
	public String getUploadImageContentType() {
		return uploadImageContentType;
	}
	public void setUploadImageContentType(String uploadImageContentType) {
		this.uploadImageContentType = uploadImageContentType;
	}
	public String getUploadImageFileName() {
		return uploadImageFileName;
	}
	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	public String getConfirm_password() {
		return confirm_password;
	}
	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Override
	public String toString() {
        return String.format("id=%s, name=%s, password=%s", id, name, password);
    }*/
}
