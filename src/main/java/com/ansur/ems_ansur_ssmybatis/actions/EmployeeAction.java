package com.ansur.ems_ansur_ssmybatis.actions;

import java.io.ByteArrayOutputStream;
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
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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
	private String month;
	private String year;

	DateFormat monthFormat = new SimpleDateFormat("MM");
	DateFormat yearFormat = new SimpleDateFormat("yyyy");

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

		return ActionSupport.SUCCESS;
	}

	/* ログインチェック */
	public String empLogin() {
		if (employee.getEmp_id().equals("admin") && employee.getPassword().equals("@nsur")) {
			return ActionSupport.LOGIN;
		}
		employee = empMapper.checkLoginData(getEmployee());
		try {
			if (!employee.equals(null)) {
				ekiOperatorList = eoMapper.getEONameAndNumberList();// 駅オペレーターリストを取るために
				month = monthFormat.format(new Date());
				year = yearFormat.format(new Date());

				/* transpoList = tMapper.getTranspoListByEmpId(employee.getEmp_id()); */
				monthlyTranspoList = tMapper.getMonthlyTranspoListByEmpId(Integer.valueOf(month), Integer.valueOf(year),
						employee.getEmp_id());

				/* setLogoNameList(transpoList); */

				// 総チャージ金額を計算
				// 残ったチャージ金額計算
				// 支払い申請金額計算する
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

		month = monthFormat.format(new Date());
		year = yearFormat.format(new Date());
		String empId = request.getParameter("p1");

		employee = empMapper.getEmployeeByEmpId(empId);

		monthlyTranspoList = tMapper.getMonthlyTranspoListByEmpId(Integer.valueOf(month), Integer.valueOf(year), empId);

		// 総チャージ金額を計算
		// 残ったチャージ金額計算
		// 支払い申請金額計算する
		try {
			int totalCharge = tMapper.totalCharge();
			int total = tMapper.totalKuru(getEmployee().getEmp_id()) + tMapper.totalKaeru(getEmployee().getEmp_id());
			employee.setTotal_charge(totalCharge);
			employee.setLeft_charge(totalCharge - total);
			employee.setTotal_pay(total);
		} catch (Exception e) {
			// TODO: handle exception
		}

		employee.setCurrentDate(month + "/" + year);
		return ActionSupport.SUCCESS;
	}

	// 新社員を登録ために
	public String empInsert() throws IOException {

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

	public String createExcel() throws IOException {
		String headTitle[] = { "月日", "チャージ", "車種", "使った線", "乗車範囲", "", "", "目的", "来る費", "帰る費", "備考" };
		int columnWidth[] = { 3000, 2500, 6000, 5000, 3000, 1500, 3000, 2000, 2000, 2000, 6000 };
		HttpServletRequest request = ServletActionContext.getRequest();
		month = request.getParameter("p2").split("/")[0];
		year = request.getParameter("p2").split("/")[1];
		String empId = request.getParameter("emp_id");

		ekiOperatorList = eoMapper.getEONameAndNumberList();// 駅オペレーターリストを取るために
		monthlyTranspoList = tMapper.getMonthlyTranspoListByEmpId(Integer.valueOf(month), Integer.valueOf(year), empId);
		employee = empMapper.getEmployeeByEmpId(request.getParameter("emp_id"));
		employee.setCurrentDate(month + "/" + year);
		Properties p = new Properties();
		try {
			p.load(FileServlet.class.getClassLoader().getResourceAsStream("application.properties"));
			filePath = p.getProperty("label.path");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String excelFolderPath = filePath + "\\ExcelFiles\\" + employee.getName() + ".xls";

		File file = new File(excelFolderPath);
		HSSFWorkbook hwb = null;
		HSSFSheet sheet = null;
		HSSFRow rowhead;

		/*
		 * 作成するファイルがあるかどうかチェックする。 エクセルファイルない場合は 新しい.xlsファイルを作って新しい「sheet」 を作ります。
		 * エクセルファイルある場合は エクセルファイルの中に次作成する「sheet」がもうあるかどうかチェックします。
		 * それから、作成する「sheet」がある場合は作成しなくてその「sheet」を取って更新します。
		 * 「sheet」がない場合は新しいのを作成します。
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
		
		/*「sheet」の中にある「cell」のstyleを作ること*/
		HSSFCellStyle style = hwb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		/*「row　0」を作ってその「row」の中に「cell」を作って「cell」の中にデータを書きこと*/
		rowhead = sheet.createRow(0);
		for (int i = 0; i < headTitle.length; i++) {
			Cell cell = rowhead.createCell(i);
			cell.setCellValue(headTitle[i]);
			cell.setCellStyle(style);
		}

		/*「row 0」の「cell 4」から「cell 6」までをマージすること*/
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 6));

		/* sheet.setColumnWidth(0, 3000); */
		/*「row 0」の「cell」の幅（width）を設定すること*/
		for (int i = 0; i < columnWidth.length; i++) {
			sheet.setColumnWidth(i, columnWidth[i]);
		}

		/*DBからデータを取って「row」を作って各「row」の中に「cell」を作って各「cell」の中にデータを入れること*/
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		int index = 1;
		for (Transporation t : monthlyTranspoList) {
			rowhead = sheet.createRow(index);
			rowhead.createCell(0).setCellValue(df.format(t.getT_date()));
			rowhead.createCell(1).setCellValue(t.getT_charge());
			rowhead.createCell(2).setCellValue(t.getT_operator());
			rowhead.createCell(3).setCellValue(t.getT_line());
			rowhead.createCell(4).setCellValue(t.getT_kuru_eki());
			rowhead.createCell(5).setCellValue("<=>");
			rowhead.createCell(6).setCellValue(t.getT_kaeru_eki());
			rowhead.createCell(7).setCellValue(t.getT_purpose());
			rowhead.createCell(8).setCellValue(t.getT_kuru_charge());
			rowhead.createCell(9).setCellValue(t.getT_kaeru_charge());
			rowhead.createCell(10).setCellValue(t.getT_remarks());
			index++;
		}
		FileOutputStream fileOut = new FileOutputStream(excelFolderPath);
		hwb.write(fileOut);
		fileOut.close();

		// 作成したエクセルファイルをダウンロードする
		fileInputStream = new FileInputStream(new File(filePath + "\\ExcelFiles\\" + employee.getName() + ".xls"));
		filename = employee.getName() + ".xls";// ダウンロードファイル名前のなめにstruts.xmlに送ること
		return ActionSupport.SUCCESS;
	}

	// 更新するのキャンセルために
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
