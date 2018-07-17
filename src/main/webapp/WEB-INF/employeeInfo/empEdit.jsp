<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
</head>
<body>


	<div style="widht: 100%;">
		<div style="width: 75%; margin: 1% auto;">
			<div style="width: 35%; height: 180px; float: left;">
				<img alt="" src="<%=request.getContextPath()%>/image/ansurLogo.jpg"
					style="width: 100%;">
				<h2 style="text-align: center; margin: -10px; color: gray;">株式会社アンスール</h2>
			</div>
			<div style="border: 1px solid gray; float: right; width: 40%;">
				<table border="0" style="width: 90%; margin: 1% auto;">
					<tr>
						<td><img
							style="vertical-align: middle; width: 100px; height: 100px;"
							src="file/EmployeeProfilePicture/${employee.img_name}"></td>
						<td valign="top">
							<h5 style="margin: 0px; color: #2bbbbb;">株式会社会社アンスール</h5> <label
							style="display: block; float: left; font-size: 10px; width: 30%;">名前：</label>
							<label style="display: block; font-size: 10px; width: 100%;">${employee.name}</label>
							<label
							style="display: block; float: left; font-size: 10px; width: 30%;">社員番号：</label><label
							style="display: block; font-size: 10px; width: 100%;">${employee.emp_id}</label>
							<label
							style="display: block; float: left; font-size: 10px; width: 30%;">部門：</label><label
							style="display: block; font-size: 10px; width: 100%;">${employee.department}</label>
							<label
							style="display: block; float: left; font-size: 10px; width: 30%;">役職：</label><label
							style="display: block; font-size: 10px; width: 100%;">${employee.post}(${employee.status })</label>
							<label
							style="display: block; float: left; font-size: 10px; width: 30%;">メール：</label><label
							style="display: block; font-size: 10px; width: 100%;">${employee.email}</label>
						</td>
					</tr>
				</table>				
			</div>
		</div>

		<div  style="width: 80%; margin: 0% auto; clear: right; clear:left;">
			<a style="color: gray; text-decoration: none;"
				href="<s:url action="back"><s:param name="emp_id">${employee.emp_id}</s:param></s:url>">戻る</a>
			<a style="float: right; color: gray; text-decoration: none;"
				href="<s:url action="empLogout"/>"> ログアウト</a>
		</div>
		<div style="width: 80%; margin: 1% auto; clear: right;">
			<hr />
			<h4 style="text-align: center;">社員編集画面</h4>
		</div>
		<div
			style="clear: right; width: 80%; margin: 1px auto;">
			<div
				style="float: left; width: 30%; margin: 1% 1% 1% 1%; border: 1px solid gray;  border-top: 20px solid #2bbbbb;">
				<label
					style="display: block; color: blue; font-size: 12px; text-align: center; margin: 1%;">${message_one}</label>
				<label
					style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_one}</label>
				<s:form action="empUpdate" enctype="multipart/form-data">
					<table style="width: 80%; margin: 2% auto;">
						<tr>
							<td><s:textfield name="employee.emp_id" label="従業員ID"
									readonly="true" /></td>
						</tr>
						<tr>
							<td><s:textfield name="employee.name" label="名前" /></td>
						</tr>
						<tr>
							<td><s:textfield name="employee.email" label="メール" /></td>
						</tr>
						<tr>
							<td><s:textfield name="employee.address" label="アドレス" /></td>
						</tr>
						<tr>
							<td><s:submit value="更新"
									style="width:25%; margin: 1% 1%; display:block; float:right;" />
							</td>
						</tr>
					</table>
				</s:form>
			</div>

			<div
				style="float: left; width: 33%; margin: 1% 1% 1% 1%; border: 1px solid gray; text-align: center;  border-top: 20px solid #2bbbbb;">
				<label
					style="display: block; color: blue; font-size: 12px; text-align: center; margin: 1%;">${message_two}</label>
				<label
					style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_two}</label>
				<s:form action="empPhotoChange" enctype="multipart/form-data">

					<s:hidden name="employee.emp_id" />
					<img style="width: 150px; height: 150px;"
						src="file/EmployeeProfilePicture/${employee.img_name}"
						alt="${employee.name}" title="${employee.name}">

					<s:file name="employee.uploadImage" />

					<s:submit value="更新"
						style="width:25%; margin: 1% 1%; display:block; float:right;" />
				</s:form>
			</div>

			<div
				style="float: left; width: 30%; margin: 1% 1% 1% 1%; border: 1px solid gray;  border-top: 20px solid #2bbbbb;">
				<label
					style="display: block; color: blue; font-size: 12px; text-align: center; margin: 1%;">${message_three}</label>
				<label
					style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_three}</label>
				<s:form action="empPasswordChange">
					<s:hidden name="employee.emp_id" />
					<s:password name="employee.old_password" label="以前のパスワード" />
					<s:password name="employee.password" label="新しいパスワード" />
					<s:password name="employee.confirm_password" label="確認パスワード" />
					<s:submit value="更新"
						style="width:25%; margin: 1% 1%; display:block; float:right;" />
				</s:form>
			</div>

		</div>



	</div>





</body>
</html>