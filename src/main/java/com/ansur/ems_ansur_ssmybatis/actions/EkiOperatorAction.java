package com.ansur.ems_ansur_ssmybatis.actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.ansur.ems_ansur_ssmybatis.models.EkiOperator;
import com.ansur.ems_ansur_ssmybatis.models.EkiOperatorMapper;

import com.opensymphony.xwork2.ActionSupport;

public class EkiOperatorAction implements ServletRequestAware {

	@Autowired
	private EkiOperatorMapper eoMapper;

	private EkiOperator ekiOperator;

	private List<EkiOperator> ekiOperatorList;

	private String errorMessage_one;
	private String errorMessage_two;
	private String message_one;
	private String message_two;

	public String execute() {
		ekiOperatorList = eoMapper.getEkiOperatorList();
		ekiOperator = new EkiOperator();
		ekiOperator.setEo_number(getNextEoNumber());
		return ActionSupport.SUCCESS;
	}

	// 駅オペレータ作成するために
	public String ekiOperatorInsert() throws IOException {
		try {
			String filePath = "C:\\Users\\ansur02\\MawPaingThu\\FileServer\\EkiOperatorLogo";
			File fileToCreate = new File(filePath, getEkiOperator().getUploadImageFileName());
			FileUtils.copyFile(getEkiOperator().getUploadImage(), fileToCreate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (eoMapper.checkAlreadyExit(getEkiOperator().getEo_name())) {
			setErrorMessage_one("この駅オペレーターは「もう登録しました。");
			ekiOperatorList = eoMapper.getEkiOperatorList();
			return ActionSupport.ERROR;
		}
		eoMapper.insertEkiOperator(getEkiOperator());
		ekiOperatorList = eoMapper.getEkiOperatorList();
		clear();
		return ActionSupport.SUCCESS;
	}

	public String ekiOperatorEditPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		ekiOperator = eoMapper.getEkiOperatorById(Integer.valueOf(request.getParameter("id")));
		return ActionSupport.SUCCESS;
	}

	public String ekiOperatorUpdate() {

		if (getEkiOperator().getEo_name().equals("")) {
			setErrorMessage_one("赤い「＊」の項目は必要です。");
			ekiOperator = eoMapper.getEkiOperatorById(Integer.valueOf(getEkiOperator().getEo_id()));
			return ActionSupport.ERROR;
		}
		EkiOperator eo = new EkiOperator();
		try {
			eo = eoMapper.getEkiOperatorByName(getEkiOperator().getEo_name());
			if (!Integer.valueOf(eo.getEo_id()).equals(ekiOperator.getEo_id())) {
				setErrorMessage_one("この部門はもう登録しました。");
				ekiOperator = eoMapper.getEkiOperatorById(Integer.valueOf(getEkiOperator().getEo_id()));
				return ActionSupport.ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception

		}
		eoMapper.updateEkiOperator(getEkiOperator());
		setMessage_one("更新できました。");
		ekiOperator = eoMapper.getEkiOperatorById(Integer.valueOf(getEkiOperator().getEo_id()));
		return ActionSupport.SUCCESS;
	}
	
	public String ekiOperatorPhotoChange() {
		try {
			String filePath = "C:\\Users\\ansur02\\MawPaingThu\\FileServer\\EkiOperatorLogo";
			File fileToCreate = new File(filePath, getEkiOperator().getUploadImageFileName());
			FileUtils.copyFile(getEkiOperator().getUploadImage(), fileToCreate);
			eoMapper.updateImgName(getEkiOperator());
			setMessage_two("写真の変更できました。");
		} catch (Exception e) {
			// TODO: handle exception
			setErrorMessage_two("写真を選んでください。");
		}
		ekiOperator = eoMapper.getEkiOperatorById(getEkiOperator().getEo_id());
		return ActionSupport.SUCCESS;				
	}
	
	public String ekiOperatorDelete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		eoMapper.delete(Integer.valueOf(request.getParameter("id")));
		ekiOperatorList = eoMapper.getEkiOperatorList();
		ekiOperator = new EkiOperator();
		ekiOperator.setEo_number(getNextEoNumber());
		return ActionSupport.SUCCESS;
	}

	private void clear() {
		// TODO Auto-generated methsod stub

		ekiOperator.setEo_number(getNextEoNumber());
		ekiOperator.setEo_name("");
		ekiOperator.setEo_logo_name("");
	}

	// 駅オペレーター番号を作ること
	private String getNextEoNumber() {
		// TODO Auto-generated method stub
		String oldEoNumber = "";
		String nextEoNumber = "jpto01";
		String eoN = "jpto";
		try {
			oldEoNumber = eoMapper.getLastEkiOperatorNumber();
			int number = Integer.valueOf(oldEoNumber.split("o")[1]) + 1;
			if (number > 9) {
				nextEoNumber = eoN.concat(String.valueOf(number));
			} else {
				nextEoNumber = eoN.concat("0" + String.valueOf(number));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return nextEoNumber;
	}

	public List<EkiOperator> getEkiOperatorList() {
		return ekiOperatorList;
	}

	public void setEkiOperatorList(List<EkiOperator> ekiOperatorList) {
		this.ekiOperatorList = ekiOperatorList;
	}

	public EkiOperator getEkiOperator() {
		return ekiOperator;
	}

	public void setEkiOperator(EkiOperator ekiOperator) {
		this.ekiOperator = ekiOperator;
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

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub

	}

}
