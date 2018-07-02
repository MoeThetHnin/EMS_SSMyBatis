<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
</head>
<body>


	<div style="widht: 100%;">
		<div style="text-align: center;">
			<h2>株式会社アンスール</h2>
			<h3>従業員管理システムアドミン側</h3>
		</div>
		<div style="width: 80%; margin: 1% auto;">
			<a style="color: gray; text-decoration: none;"
				href="<s:url action="updateCancel.action"><s:param name="emp_id">${employee.emp_id}</s:param></s:url>">${employee.name }</a><a
				style="float: right; color: gray; text-decoration: none;"
				href="<s:url action="logout.action"/>"> ログアウト</a>
		</div>
		<div style="width: 80%; margin: 1% auto; clear: right;">
			<hr />
			<h4 style="text-align: center;">社員編集画面</h4>
		</div>
		<div
			style="clear: right; width: 80%; border: 1px solid gray; margin: 1px auto;">

			<div
				style="float: left; width: 30%; margin: 1% 1% 1% 1%; border: 1px solid gray;">
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
				style="float: left; width: 33%; margin: 1% 1% 1% 1%; border: 1px solid gray; text-align: center;">
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
				style="float: left; width: 30%; margin: 1% 1% 1% 1%; border: 1px solid gray;">
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