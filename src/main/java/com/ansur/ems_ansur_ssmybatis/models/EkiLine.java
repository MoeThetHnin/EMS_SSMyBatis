package com.ansur.ems_ansur_ssmybatis.models;


import java.io.File;
public class EkiLine {
	
	private int el_id;
	private String el_number;
	private String el_line;
	private String el_line_logo_name;
	private String eo_number;
	private String eo_logo_name;
	private String eo_name;
	
	private File uploadImage;
	private String uploadImageContentType;
	private String uploadImageFileName;
	
	
	public int getEl_id() {
		return el_id;
	}
	public void setEl_id(int el_id) {
		this.el_id = el_id;
	}
	public String getEl_number() {
		return el_number;
	}
	public void setEl_number(String el_number) {
		this.el_number = el_number;
	}
	public String getEl_line() {
		return el_line;
	}
	public void setEl_line(String el_line) {
		this.el_line = el_line;
	}
	public String getEl_line_logo_name() {
		return el_line_logo_name;
	}
	public void setEl_line_logo_name(String el_line_logo_name) {
		this.el_line_logo_name = el_line_logo_name;
	}	
	public String getEo_number() {
		return eo_number;
	}
	public void setEo_number(String eo_number) {
		this.eo_number = eo_number;
	}
	public String getEo_logo_name() {
		return eo_logo_name;
	}
	public void setEo_logo_name(String eo_logo_name) {
		this.eo_logo_name = eo_logo_name;
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
	public String getEo_name() {
		return eo_name;
	}
	public void setEo_name(String eo_name) {
		this.eo_name = eo_name;
	}
	
	
	
	
	
	
	
	
	

}
