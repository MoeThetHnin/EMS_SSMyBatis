package com.ansur.ems_ansur_ssmybatis.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.ansur.ems_ansur_ssmybatis.models.EkiLine;
import com.ansur.ems_ansur_ssmybatis.models.EkiLineMapper;
import com.ansur.ems_ansur_ssmybatis.models.EkiOperator;
import com.ansur.ems_ansur_ssmybatis.models.EkiOperatorMapper;
import com.opensymphony.xwork2.ActionSupport;

public class EkiLineAction implements ServletRequestAware {

	@Autowired
	private EkiLineMapper elMapper;

	@Autowired
	private EkiOperatorMapper eoMapper;

	private EkiLine ekiLine;
	private List<EkiLine> ekiLineList;

	private List<EkiLine> ekiLineListMetro;

	private List<EkiLine> ekiLineListToei;

	private List<EkiLine> ekiLineByOperatorList;

	private List<EkiOperator> ekiOperatorList;

	private List<EkiLine> ekiLineListDistinct;

	private HttpServletRequest servletRequest;

	public String execute() {

		ekiOperatorList = eoMapper.getEONameAndNumberList();
		ekiLineListDistinct = elMapper.getEOLogoList();
		ekiLineList = elMapper.getEkiLineList();

		return ActionSupport.SUCCESS;
	}

	//âwê¸ÇçÏê¨Ç∑ÇÈÇΩÇﬂÇ…
	public String ekiLineInsert() throws IOException {
		String filePath = "C:\\Users\\ansur02\\MawPaingThu\\FileServer\\EkiLineLogo";
		File fileToCreate = new File(filePath, getEkiLine().getUploadImageFileName());
		FileUtils.copyFile(getEkiLine().getUploadImage(), fileToCreate);
		
		EkiOperator eo = new EkiOperator();
		eo = eoMapper.getAllEoByEONumber(ekiLine.getEo_number());

		ekiLine.setEo_logo_name(eo.getEo_logo_name());
		ekiLine.setEo_name(eo.getEo_name());
		elMapper.insertEkiLine(getEkiLine());

		ekiOperatorList = eoMapper.getEONameAndNumberList();
		ekiLineListDistinct = elMapper.getEOLogoList();
		ekiLineList = elMapper.getEkiLineList();
		
		clear();
		
		return ActionSupport.SUCCESS;
	}

	private void clear() {
		// TODO Auto-generated method stub
		ekiLine.setEl_number("");
		ekiLine.setEl_line("");
		ekiLine.setEo_number("");
	}

	public EkiLine getEkiLine() {
		return ekiLine;
	}

	public void setEkiLine(EkiLine ekiLine) {
		this.ekiLine = ekiLine;
	}

	public List<EkiLine> getEkiLineList() {
		return ekiLineList;
	}

	public void setEkiLineList(List<EkiLine> ekiLineList) {
		this.ekiLineList = ekiLineList;
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

	public List<EkiOperator> getEkiOperatorList() {
		return ekiOperatorList;
	}

	public void setEkiOperatorList(List<EkiOperator> ekiOperatorList) {
		this.ekiOperatorList = ekiOperatorList;
	}

	public List<EkiLine> getEkiLineListDistinct() {
		return ekiLineListDistinct;
	}

	public void setEkiLineListDistinct(List<EkiLine> ekiLineListDistinct) {
		this.ekiLineListDistinct = ekiLineListDistinct;
	}

	public List<EkiLine> getEkiLineByOperatorList() {
		return ekiLineByOperatorList;
	}

	public void setEkiLineByOperatorList(List<EkiLine> ekiLineByOperatorList) {
		this.ekiLineByOperatorList = ekiLineByOperatorList;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
	}

}
