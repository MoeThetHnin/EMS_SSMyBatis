<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>

	<div style="width: 100%;">
		<div style="width: 80%; margin: 0% auto;">
			<div style="float: left; width: 30%;">
				<img alt=""
					src="<%=request.getContextPath()%>/image/ansurLogoSmall.jpg"
					style="width: 53%;">
				<h3 style="margin-top: -10px; color: gray;">株式会社アンスール</h3>
			</div>
			<div style="float: right; width: 30%;">
				<a
					style="display: block; color: gray; text-decoration: none; text-align: right;"
					href="<s:url action="logout.action"/>"> ログアウト</a>
			</div>

		</div>

		<div
			style="display: inline-block; width: 100%; margin: 1% auto; clear: left; clear: right;">
			<div style="width: 80%; margin: 0% auto;">
				<ul style="list-style: none;">
					<a href="<s:url action="empManage"/>"
						style="text-decoration: none; color: gray;">
						<li
						style="float: left; margin: 0% 0.5%; padding: 0.2%; border: 1px solid #2bbbbb; width: 15%; text-align: center; background-color: #2bbbbb; color: white;">社員管理</li>
					</a>
					<a href="<s:url action="departmentManage"/>"
						style="text-decoration: none; color: gray;">
						<li
						style="float: left; margin: 0% 0.5%; padding: 0.2%; border: 1px solid #2bbbbb; width: 15%; text-align: center;">部門管理と役職管理</li>
					</a>
					<a href="<s:url action="ekiManage"/>"
						style="text-decoration: none; color: gray;">
						<li
						style="float: left; margin: 0% 0.5%; padding: 0.2%; border: 1px solid #2bbbbb; width: 15%; text-align: center;">駅管理</li>
					</a>
					<a href="#" style="text-decoration: none; color: gray;">
						<li
						style="float: left; margin: 0% 0.5%; padding: 0.2%; border: 1px solid #2bbbbb; width: 15%; text-align: center;">OO管理</li>
					</a>
				</ul>

			</div>
			<div
				style="width: 80%; margin: 1% auto; border: 1px solid gray; border-top: 20px solid #2bbbbb; clear: left;">
				<div style="width: 100%; margin: 1% auto;">

					<h4 style="text-align: center;">社員管理</h4>

					<div style="width: 50%; margin: 1px auto; clear: right;">
						<s:form action="insert" enctype="multipart/form-data">
							<label
								style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_one}</label>
							<table
								style="border: 1px solid gray; width: 70%; margin: 2% auto;">
								<tr>
									<td><s:file name="employee.uploadImage" label="写真" /></td>
								</tr>
								<tr>
									<td><s:textfield name="employee.emp_id" label="従業員ID"
											readonly="true" /></td>
								</tr>
								<tr>
									<td><s:textfield name="employee.name" label="名前" /></td>
								</tr>
								<tr>
									<td><s:password name="employee.password" label="パスワード" /></td>

								</tr>

								<tr>
									<td><s:select name="employee.department"
											list="departmentList" listKey="dep_name" listValue="dep_name"
											headerKey="-1" headerValue="ーーー部門選択ーーー" label="部門" /></td>
								</tr>
								<tr>
									<td><s:select name="employee.post" list="postList"
											listKey="p_name" listValue="p_name" headerKey="-1"
											headerValue="ーーー役職選択ーーー " label="役職" /></td>
								</tr>
								<tr>
									<td><s:radio name="employee.status"
											list="#{'本社':'本社','派遣会社':'派遣会社'}" label="本社 / 派遣会社" /></td>
								</tr>

								<tr>
									<td><s:textfield name="employee.email" label="メール" /></td>
								</tr>


								<tr>
									<td><s:textfield name="employee.address" label="アドレス" /></td>
								</tr>
								<tr>
									<td><s:submit value="新規" style="width:100%;" /></td>
								</tr>
							</table>



						</s:form>
					</div>

				</div>
			</div>
			<a href="<s:url action="empManage"/>"
				style="text-decoration: none; color: gray;">
				<div
					style="width: 21%; margin: 1% 1% 1% 3%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; clear: left; float: left;">
					<h4>社員管理</h4>
				</div>
			</a> <a href="<s:url action="departmentManage"/>"
				style="text-decoration: none; color: gray;">
				<div
					style="width: 21%; margin: 1% 1% 1% 2%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
					<h4>部門管理と役職管理</h4>
				</div>
			</a> <a href="<s:url action="ekiManage"/>"
				style="text-decoration: none; color: gray;">
				<div
					style="width: 21%; margin: 1% 1% 1% 2%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
					<h4>駅管理</h4>
				</div>
			</a> <a href="#" style="text-decoration: none; color: gray;">
				<div
					style="width: 21%; margin: 1% 1% 1% 2%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
					<h4>OO管理</h4>
				</div>
			</a>


		</div>
		<div style="width: 80%; margin: 1% auto; clear: left;">
			<div style="width: 50%; float: left;">

				<h4 style="text-align: center;">コンバータ</h4>
				<div
					style="clear: left; width: 90%; margin: 1% auto; border: 1px solid gray;">

					<h5 style="text-align: center;">エクセルファイルをPDFファイルに変更する機能 (.xls
						to .pdf)</h5>
					<div style="width: 50%; margin: 1% auto;">
						<s:form action="excelToPdf" enctype="multipart/form-data">
							<s:file name="function.uploadImage" multiple="multiple"></s:file>
							<s:submit value="PDFファイルに変更"></s:submit>
						</s:form>
					</div>
				</div>
				<div
					style="clear: left; width: 90%; margin: 1% auto; border: 1px solid gray;">
					<h5 style="text-align: center;">エクセルファイルをSQLファイルに変更する機能 (.xls
						to .sql)</h5>
					<div style="width: 50%; margin: 1% auto;">
						<s:form action="excelToSql" enctype="multipart/form-data">
							<s:file name="function.uploadImage" multiple="multiple"></s:file>
							<s:submit value="SQLファイルに変更"></s:submit>
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<p>
		<s:property value="result" />
	</p>
</body>
</html>