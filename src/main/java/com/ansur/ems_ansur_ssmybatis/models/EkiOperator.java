package com.ansur.ems_ansur_ssmybatis.models;

import java.io.File;

public class EkiOperator {

		private int eo_id;
		private String eo_number;
		private String eo_name;
		private String eo_logo_name;
		
		private File uploadImage;
		private String uploadImageContentType;
		private String uploadImageFileName;
		
		public int getEo_id() {
			return eo_id;
		}
		public void setEo_id(int eo_id) {
			this.eo_id = eo_id;
		}
		public String getEo_number() {
			return eo_number;
		}
		public void setEo_number(String eo_number) {
			this.eo_number = eo_number;
		}
		public String getEo_name() {
			return eo_name;
		}
		public void setEo_name(String eo_name) {
			this.eo_name = eo_name;
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
		
		
}
