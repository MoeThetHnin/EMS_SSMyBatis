package com.ansur.ems_ansur_ssmybatis.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ansur.ems_ansur_ssmybatis.models.Department;
import com.ansur.ems_ansur_ssmybatis.models.DepartmentMapper;
import com.ansur.ems_ansur_ssmybatis.models.EkiLine;
import com.ansur.ems_ansur_ssmybatis.models.EkiOperator;
import com.ansur.ems_ansur_ssmybatis.models.EkiOperatorMapper;
import com.ansur.ems_ansur_ssmybatis.models.Employee;
import com.ansur.ems_ansur_ssmybatis.models.EmployeeMapper;
import com.ansur.ems_ansur_ssmybatis.models.Post;
import com.ansur.ems_ansur_ssmybatis.models.PostMapper;
import com.ansur.ems_ansur_ssmybatis.models.Transporation;
import com.ansur.ems_ansur_ssmybatis.models.TransporationMapper;
import com.ansur.ems_ansur_ssmybatis.services.FileServlet;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.PdfWriter;
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
	private List<Transporation> monthlyTranspoList;
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

	private InputStream fileInputStream;

	private String filename;

	private String filePath;
	

	DateFormat monthFormat = new SimpleDateFormat("MM");
	DateFormat yearFormat = new SimpleDateFormat("yyyy");
	String month = monthFormat.format(new Date());
	String year = yearFormat.format(new Date());

	public String execute() {
		employeeList = empMapper.getAllEmployee();// DBから社員リストを取るために
		getTwoRequiredList();
		employee = new Employee();
		employee.setEmp_id(autoCreateEmpId());// 新社員を登録ために社員番号を自動的に出るために
		return ActionSupport.SUCCESS;
	}	
	

	public String getTranspoListByMonth() {
		HttpServletRequest request = ServletActionContext.getRequest();

		month = request.getParameter("p2");
		year = yearFormat.format(new Date());
		String empId = request.getParameter("p1");
		ekiOperatorList = eoMapper.getEONameAndNumberList();// 駅オペレーターリストを取るために
		employee = empMapper.getEmployeeByEmpId(request.getParameter("p1"));
		monthlyTranspoList = tMapper.getMonthlyTranspoListByEmpId(Integer.valueOf(month), Integer.valueOf(year), empId);
		employee.setCurrentDate(month + "/" + year);
		try {
			if (!request.getParameter("p3").equals(null)) {
				return ActionSupport.LOGIN;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			int totalCharge = tMapper.totalCharge(Integer.valueOf(month), Integer.valueOf(year), empId);
			int totalFee = tMapper.totalFee(Integer.valueOf(month), Integer.valueOf(year), empId);
			employee.setTotal_charge(totalCharge);
			employee.setTotal_pay(totalFee);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return ActionSupport.SUCCESS;
	}

	/* ログインチェック */
	public String login() {
	
		employee = empMapper.checkLoginData(getEmployee());
		try {
			if (!employee.equals(null)) {
				ekiOperatorList = eoMapper.getEONameAndNumberList();// 駅オペレーターリストを取るために
				

				/* transpoList = tMapper.getTranspoListByEmpId(employee.getEmp_id()); */
				monthlyTranspoList = tMapper.getMonthlyTranspoListByEmpId(Integer.valueOf(month), Integer.valueOf(year),
						employee.getEmp_id());

				/* setLogoNameList(transpoList); */

				// 総チャージ金額を計算
				// 残ったチャージ金額計算
				// 支払い申請金額計算する
				try {
					int totalCharge = tMapper.totalCharge(Integer.valueOf(month), Integer.valueOf(year),
							employee.getEmp_id());
					int totalFee = tMapper.totalFee(Integer.valueOf(month), Integer.valueOf(year),
							employee.getEmp_id());
					/*
					 * int totalPay = tMapper.totalKuru(Integer.valueOf(month),
					 * Integer.valueOf(year), employee.getEmp_id()) +
					 * tMapper.totalKaeru(Integer.valueOf(month), Integer.valueOf(year),
					 * employee.getEmp_id());
					 */
					employee.setTotal_charge(totalCharge);
					/* employee.setLeft_charge(totalCharge - totalPay); */
					employee.setTotal_pay(totalFee);
				} catch (Exception e) {
					// TODO: handle exception
				}

				employee.setCurrentDate(month + "/" + year);

				return ActionSupport.SUCCESS;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		setErrorMessage_one("ユーザー名かパスワードが間違っています。");
		return ActionSupport.ERROR;
	}

	// ログアウトのために
	public String logout() {
		return ActionSupport.SUCCESS;
	}

	public String empShow() {
		HttpServletRequest request = ServletActionContext.getRequest();		
		String empId = request.getParameter("p1");
		employee = empMapper.getEmployeeByEmpId(empId);

		monthlyTranspoList = tMapper.getMonthlyTranspoListByEmpId(Integer.valueOf(month), Integer.valueOf(year), empId);

		// 総チャージ金額を計算
		// 残ったチャージ金額計算
		// 支払い申請金額計算する
		try {
			int totalCharge = tMapper.totalCharge(Integer.valueOf(month), Integer.valueOf(year), employee.getEmp_id());
			int totalFee = tMapper.totalFee(Integer.valueOf(month), Integer.valueOf(year), employee.getEmp_id());
			employee.setTotal_charge(totalCharge);
			employee.setTotal_pay(totalFee);
		} catch (Exception e) {
			// TODO: handle exception
		}

		employee.setCurrentDate(month + "/" + year);
		return ActionSupport.SUCCESS;
	}

	// 新社員を登録ために
	public String insert() throws IOException {

		if (!empMapper.checkEmail(getEmployee().getEmail())) {
			empMapper.insertEmployee(getEmployee());
			employeeList = empMapper.getAllEmployee();
			getTwoRequiredList();
			clear();
			return ActionSupport.SUCCESS;
		}

		employeeList = empMapper.getAllEmployee();
		getTwoRequiredList();
		setErrorMessage_one("すみませんが。このメールアドレスはもう登録しました。");
		return ActionSupport.ERROR;
	}

	// 社員の情報を削除するために
	public String empDelete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		empMapper.deleteEmployee(Integer.valueOf(request.getParameter("id")));
		employeeList = empMapper.getAllEmployee();
		getTwoRequiredList();
		employee = new Employee();
		employee.setEmp_id(autoCreateEmpId());
		return ActionSupport.SUCCESS;
	}

	// 社員の情報の編集画面へ行く（アドミン）
	public String empEditByAdminPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		employee = empMapper.getEmployeeByEmpId(request.getParameter("emp_id"));
		employeeList = empMapper.getAllEmployee();
		getTwoRequiredList();
		return ActionSupport.SUCCESS;
	}

	// 社員の情報の編集画面へ行く（ユーザ）
	public String empEditPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		employee = empMapper.getEmployeeByEmpId(request.getParameter("emp_id"));
		employeeList = empMapper.getAllEmployee();
		getTwoRequiredList();
		return ActionSupport.SUCCESS;
	}

	// 社員の情報を更新するために（アドミン）
	public String empUpdateByAdmin() {
		if (getEmployee().getName().equals("")) {
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			getTwoRequiredList();
			setErrorMessage_one("赤い「＊」の項目は必要です。");
			return ActionSupport.ERROR;
		}
		empMapper.updateEmployeeByAdmin(getEmployee());
		employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
		getTwoRequiredList();
		setMessage_one("更新できました。");
		return ActionSupport.SUCCESS;
	}

	public String empPasswordChangeByAdmin() {
		if (getEmployee().getPassword().equals("")) {
			setErrorMessage_three("赤い「＊」の項目は必要です。");
		} else {
			empMapper.updateEmpPassword(getEmployee());
			setMessage_three("パスワード更新できました。");
		}
		employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
		getTwoRequiredList();

		return ActionSupport.SUCCESS;
	}

	// 社員の情報を更新するために（ユーザ）
	public String empUpdate() {
		if (getEmployee().getName().equals("") || getEmployee().getEmail().equals("")
				|| getEmployee().getAddress().equals("")) {
			setErrorMessage_one("赤い「＊」の項目は必要です。");
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			return ActionSupport.SUCCESS;
		}
		Employee emp = new Employee();
		try {
			emp = empMapper.checkUpdateEmail(getEmployee().getEmail());
			if (!emp.getEmp_id().equals(employee.getEmp_id())) {
				setErrorMessage_one("このメールアドレスはもう登録しました。");
				employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
				return ActionSupport.SUCCESS;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		empMapper.updateEmployee(getEmployee());
		setMessage_one("社員情報更新できました。");
		employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
		return ActionSupport.SUCCESS;
	}

	public String empPasswordChange() {
		if (getEmployee().getOld_password().equals("") && getEmployee().getPassword().equals("")
				&& getEmployee().getConfirm_password().equals("")) {
			setErrorMessage_three("赤い「＊」の項目は必要です。");
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			return ActionSupport.SUCCESS;
		}
		if (!empMapper.checkOldPassword(getEmployee())) {
			setErrorMessage_three("以前のパスワードが間違っています。");
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			return ActionSupport.SUCCESS;
		}
		if (getEmployee().getPassword().equals("")) {
			setErrorMessage_three("パスワードの数はせめて８文字です。");
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			return ActionSupport.SUCCESS;
		}
		if (!getEmployee().getPassword().equals(getEmployee().getConfirm_password())) {
			setErrorMessage_three("新しいパスワードと確認パスワードが一致しなければなりません。");
			employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
			return ActionSupport.SUCCESS;
		}
		empMapper.updateEmpPassword(getEmployee());
		employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
		setMessage_three("パスワード変更できました。");
		return ActionSupport.SUCCESS;
	}
	
	

	public String empPhotoChange() {
		try {
			String filePath = "C:\\Users\\ansur02\\MawPaingThu\\FileServer\\EmployeeProfilePicture";
			File fileToCreate = new File(filePath, getEmployee().getUploadImageFileName());
			FileUtils.copyFile(getEmployee().getUploadImage(), fileToCreate);
			empMapper.updateImgName(getEmployee());
			setMessage_two("写真の変更できました。");
		} catch (Exception e) {
			// TODO: handle exception
			setErrorMessage_two("写真を選んでください。");
		}
		employee = empMapper.getEmployeeByEmpId(getEmployee().getEmp_id());
		return ActionSupport.SUCCESS;
	}
	
	// 更新するのキャンセルために
		public String back() {
			HttpServletRequest request = ServletActionContext.getRequest();
			
			String empId = request.getParameter("emp_id");
			employee = empMapper.getEmployeeByEmpId(empId);
			ekiOperatorList = eoMapper.getEONameAndNumberList();
			monthlyTranspoList = tMapper.getMonthlyTranspoListByEmpId(Integer.valueOf(month), Integer.valueOf(year), empId);

			// 総チャージ金額を計算
			// 残ったチャージ金額計算
			// 支払い申請金額計算する
			try {
				int totalCharge = tMapper.totalCharge(Integer.valueOf(month), Integer.valueOf(year), employee.getEmp_id());
				int totalFee = tMapper.totalFee(Integer.valueOf(month), Integer.valueOf(year), employee.getEmp_id());
				employee.setTotal_charge(totalCharge);
				employee.setTotal_pay(totalFee);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return ActionSupport.SUCCESS;
		}

	public String createExcel() throws IOException, DocumentException {
		String headTitle[] = { "月日", "チャージ", "車種", "使った線", "乗車範囲", "", "", "目的", "通勤費", "備考" };
		int columnWidth[] = { 3000, 2500, 6000, 5000, 3000, 1500, 4000, 2000, 2000, 5000 };
		HttpServletRequest request = ServletActionContext.getRequest();
		month = request.getParameter("p2").split("/")[0];
		year = request.getParameter("p2").split("/")[1];
		String empId = request.getParameter("emp_id");

		ekiOperatorList = eoMapper.getEONameAndNumberList();// 駅オペレーターリストを取るために
		monthlyTranspoList = tMapper.getMonthlyTranspoListByEmpId(Integer.valueOf(month), Integer.valueOf(year), empId);
		employee = empMapper.getEmployeeByEmpId(empId);
		employee.setCurrentDate(month + "/" + year);
		filePath = getFilePath();
		String excelFilePath = filePath + "\\ExcelFiles\\" + employee.getName() + ".xls";
		File file = new File(excelFilePath);
		HSSFWorkbook hwb = null;
		HSSFSheet sheet = null;
		HSSFRow rowhead;
		Cell cell;

		/*
		 * 作成するファイルがあるかどうかチェックする。 エクセルファイルない場合は 新しい.xlsファイルを作って新しい「sheet」 を作ります。
		 * エクセルファイルある場合は エクセルファイルの中に次作成する「sheet」がもうあるかどうかチェックします。
		 * それから、作成する「sheet」がある場合は作成しなくてその「sheet」を取って更新します。 「sheet」がない場合は新しいのを作成します。
		 */
		if (file.exists()) {
			try {
				FileInputStream is = new FileInputStream(file);
				hwb = new HSSFWorkbook(is);

			} catch (Exception e) {
				// TODO: handle exception
			}
			int status = 0;
			for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
				if (hwb.getSheetName(i).equals(year + "年 " + month + "月")) {
					status = 1;
					continue;
				}
			}
			if (status == 1) {
				sheet = hwb.getSheet(year + "年 " + month + "月");
			} else {
				sheet = hwb.createSheet(year + "年 " + month + "月");
			}
		} else {
			hwb = new HSSFWorkbook();
			sheet = hwb.createSheet(year + "年 " + month + "月");

		}

		sheet.getPrintSetup().setLandscape(true);
		sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.A4_EXTRA_PAPERSIZE);
		sheet.setMargin(Sheet.TopMargin, 0.5);
		sheet.setMargin(Sheet.BottomMargin, 0.5);
		sheet.setMargin(Sheet.RightMargin, 0.3);
		sheet.setMargin(Sheet.LeftMargin, 0.3);
		sheet.setHorizontallyCenter(true);

		/* 「sheet」の中にある「cell」のstyleを作ること */
		HSSFFont titleFont = hwb.createFont();
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setFontHeight((short) (titleFont.getFontHeight() * 1.5));
		titleFont.setUnderline(HSSFFont.U_SINGLE);

		HSSFFont thFont = hwb.createFont();
		thFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		HSSFCellStyle titleStyle = hwb.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		titleStyle.setFont(titleFont);
		titleStyle.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);

		HSSFCellStyle thStyle = hwb.createCellStyle();
		thStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		thStyle.setFont(thFont);

		HSSFCellStyle alignRight = hwb.createCellStyle();
		alignRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		HSSFCellStyle alignCenter = hwb.createCellStyle();
		alignCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle v_center = hwb.createCellStyle();
		v_center.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle aRight_vCenter = hwb.createCellStyle();
		aRight_vCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		aRight_vCenter.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		HSSFCellStyle head_border = hwb.createCellStyle();
		head_border.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
		head_border.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);
		head_border.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);
		head_border.setBorderRight(HSSFBorderFormatting.BORDER_THIN);
		head_border.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		head_border.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle border = hwb.createCellStyle();
		border.setBorderTop(HSSFBorderFormatting.BORDER_THIN);
		border.setBorderBottom(HSSFBorderFormatting.BORDER_THIN);
		border.setBorderLeft(HSSFBorderFormatting.BORDER_THIN);
		border.setBorderRight(HSSFBorderFormatting.BORDER_THIN);
		border.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		border.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		rowhead = sheet.createRow(0);
		rowhead.setHeight((short) (rowhead.getHeight() * 3));
		cell = rowhead.createCell(0);
		cell.setCellValue("株式会社アンスール交通費支払申請書");
		cell.setCellStyle(titleStyle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));/* 「row 0」の「cell 0」から「cell 10」までをマージすること */

		rowhead = sheet.createRow(1);
		rowhead.setHeight((short) (rowhead.getHeight() * 2));
		cell = rowhead.createCell(0);
		cell.setCellValue("申請年月日：");
		cell.setCellStyle(v_center);
		cell = rowhead.createCell(2);
		cell.setCellValue(month + "/" + year);
		cell.setCellStyle(v_center);
		cell = rowhead.createCell(6);
		cell.setCellValue("総チャージ金額：");
		cell.setCellStyle(aRight_vCenter);
		cell = rowhead.createCell(9);
		int totalCharge = tMapper.totalCharge(Integer.valueOf(month), Integer.valueOf(year), empId);
		cell.setCellValue(totalCharge);
		cell.setCellStyle(v_center);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));/* 「row 0」の「cell 0」から「cell 10」までをマージすること */
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 8));/* 「row 0」の「cell 0」から「cell 10」までをマージすること */

		rowhead = sheet.createRow(2);
		rowhead.setHeight((short) (rowhead.getHeight() * 2));
		cell = rowhead.createCell(0);
		cell.setCellValue("申請者氏名：");
		cell.setCellStyle(v_center);
		cell = rowhead.createCell(2);
		cell.setCellValue(employee.getName());
		cell.setCellStyle(v_center);
		cell = rowhead.createCell(6);
		cell.setCellValue("支払い申請金額：");
		cell.setCellStyle(alignRight);
		cell.setCellStyle(aRight_vCenter);
		cell = rowhead.createCell(9);
		int totalFee = tMapper.totalFee(Integer.valueOf(month), Integer.valueOf(year), employee.getEmp_id());
		cell.setCellValue(totalFee);
		cell.setCellStyle(v_center);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));/* 「row 0」の「cell 0」から「cell 10」までをマージすること */
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 8));/* 「row 0」の「cell 0」から「cell 10」までをマージすること */

		/* 「row 0」を作ってその「row」の中に「cell」を作って「cell」の中にデータを書きこと */
		rowhead = sheet.createRow(4);
		rowhead.setHeight((short) (rowhead.getHeight() * 1.5));
		for (int i = 0; i < headTitle.length; i++) {
			cell = rowhead.createCell(i);
			cell.setCellValue(headTitle[i]);
			cell.setCellStyle(head_border);
		}
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 4, 6));/* 「row 4」の「cell 4」から「cell 6」までをマージすること */

		/* sheet.setColumnWidth(0, 3000); */
		/* 「row 0」の「cell」の幅（width）を設定すること */
		for (int i = 0; i < columnWidth.length; i++) {
			sheet.setColumnWidth(i, columnWidth[i]);
		}

		/* DBからデータを取って「row」を作って各「row」の中に「cell」を作って各「cell」の中にデータを入れること */
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		int index = 5;
		for (Transporation t : monthlyTranspoList) {
			rowhead = sheet.createRow(index);
			cell = rowhead.createCell(0);
			cell.setCellValue(df.format(t.getT_date()));
			cell.setCellStyle(border);
			cell = rowhead.createCell(1);
			cell.setCellValue(t.getT_charge());
			cell.setCellStyle(border);
			cell = rowhead.createCell(2);
			cell.setCellValue(t.getT_operator());
			cell.setCellStyle(border);
			cell = rowhead.createCell(3);
			cell.setCellValue(t.getT_line());
			cell.setCellStyle(border);
			cell = rowhead.createCell(4);
			cell.setCellValue(t.getT_kuru_eki());
			cell.setCellStyle(border);
			cell = rowhead.createCell(5);
			if (t.getT_commutingType().equals("1")) {
				cell.setCellValue("=>");
			} else {
				cell.setCellValue("<=>");
			}

			cell.setCellStyle(border);
			cell = rowhead.createCell(6);
			cell.setCellValue(t.getT_kaeru_eki());
			cell.setCellStyle(border);
			cell = rowhead.createCell(7);
			cell.setCellValue(t.getT_purpose());
			cell.setCellStyle(border);
			cell = rowhead.createCell(8);
			cell.setCellValue(t.getT_commutingFee());
			cell.setCellStyle(border);
			cell = rowhead.createCell(9);
			cell.setCellValue(t.getT_remarks());
			cell.setCellStyle(border);
			index++;
		}
		FileOutputStream fileOut = new FileOutputStream(excelFilePath);
		hwb.write(fileOut);
		fileOut.close();

		excelToPdf();

		// 作成したエクセルファイルをダウンロードする
		fileInputStream = new FileInputStream(new File(filePath + "\\PdfFiles\\" + employee.getName() + ".pdf"));
		filename = employee.getName() + ".pdf";// ダウンロードファイル名前のなめにstruts.xmlに送ること
		return ActionSupport.SUCCESS;
	}

	private void excelToPdf() throws IOException, DocumentException {
		// TODO Auto-generated method stub
		int columnWidth[] = { 3000, 2500, 6000, 5000, 3000, 1500, 3000, 2000, 2000, 2000, 6000 };
		String fontPath = getFilePath() + "\\Fonts\\sazanami-mincho.ttf";
		FileInputStream inputDocument = new FileInputStream(
				new File(getFilePath() + "\\ExcelFiles\\" + employee.getName() + ".xls"));

		HSSFWorkbook xlsWB = new HSSFWorkbook(inputDocument);

		Document newPdf = new Document(PageSize.A4.rotate());

		BaseFont bf;
		Font font;
		bf = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		font = new Font(bf, 10);
		PdfWriter.getInstance(newPdf,
				new FileOutputStream(getFilePath() + "\\PdfFiles\\" + employee.getName() + ".pdf"));
		newPdf.open();
		PdfPTable table = new PdfPTable(11);
		table.setWidths(columnWidth);
		PdfPCell table_cell;

		HSSFSheet xlsSheet = xlsWB.getSheetAt(0);

		Iterator<Row> rowIterator = xlsSheet.iterator();

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					table_cell = new PdfPCell(new Phrase(cell.getStringCellValue(), font));
					table.addCell(table_cell);
					break;
				case Cell.CELL_TYPE_NUMERIC:
					table_cell = new PdfPCell(new Phrase((float) cell.getNumericCellValue()));
					table.addCell(table_cell);
					break;

				}
			}
		}
		newPdf.add(table);

		newPdf.close();
		inputDocument.close();

	}

	private String getFilePath() {
		Properties p = new Properties();
		try {
			p.load(FileServlet.class.getClassLoader().getResourceAsStream("application.properties"));
			filePath = p.getProperty("label.path");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
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

	// 新社員を登録ために社員番号を作ること
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
		departmentList = depMapper.getDepartmentList();// DBから部門リストを取るために
		postList = pMapper.getPostList();// DBから役職リストを取るために
	}

	private void setLogoNameList(List<Transporation> transpoList2) {
		// TODO Auto-generated method stub
		for (Transporation t : transpoList) {
			List<String> logo = new ArrayList<String>(Arrays.asList(t.getT_line().split(",\\s*")));
			t.setT_logo_name(logo);
		}
	}

	/* ゲッター、セッター */
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

	public List<Transporation> getMonthlyTranspoList() {
		return monthlyTranspoList;
	}

	public void setMonthlyTranspoList(List<Transporation> monthlyTranspoList) {
		this.monthlyTranspoList = monthlyTranspoList;
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

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
