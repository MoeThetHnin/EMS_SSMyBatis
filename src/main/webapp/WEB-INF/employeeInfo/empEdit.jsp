<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<div class="main_layout">
		<div class="client_header">
			<s:include value="/WEB-INF/common/header_logo.jsp"></s:include>
			<div class="info">
				<table>
					<tr>
						<td><img
							src="file/EmployeeProfilePicture/${employee.img_name}"></td>
						<td valign="top">
							<h5 class="m_color">株式会社会社アンスール</h5> <label class="key">名前：</label>
							<label class="value">${employee.name}</label> <label class="key">社員番号：</label><label
							class="value">${employee.emp_id}</label> <label class="key">部門：</label><label
							class="value">${employee.department}</label> <label class="key">役職：</label><label
							class="value">${employee.post}(${employee.status })</label> <label
							class="key">メール：</label><label class="value">${employee.email}</label>
						</td>
					</tr>
				</table>
			</div>
		</div>

		<div class="client_nav">
			<a style="color: gray; text-decoration: none;"
				href="<s:url action="back"><s:param name="emp_id">${employee.emp_id}</s:param></s:url>">戻る</a>
			<a style="float: right; color: gray; text-decoration: none;"
				href="<s:url action="empLogout"/>"> ログアウト</a>
		</div>

		<div class="client_body2">
			<div class="border">
				<div class="box1">
					<label class="success_message">${message_one}</label> <label
						class="error_message">${errorMessage_one}</label>
					<s:form action="empUpdate" enctype="multipart/form-data">
						<table>
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

				<div class="box2">
					<label class="success_message">${message_two}</label> <label
						class="error_message">${errorMessage_two}</label>
					<s:form action="empPhotoChange" enctype="multipart/form-data">

						<s:hidden name="employee.emp_id" />
						<img src="file/EmployeeProfilePicture/${employee.img_name}"
							alt="${employee.name}" title="${employee.name}">
						<s:file name="employee.uploadImage" />
						<s:submit value="更新"
							style="width:25%; margin: 1% 1%; display:block; float:right;" />
					</s:form>
				</div>

				<div class="box3">
					<label class="success_message">${message_three}</label> <label
						class="error_message">${errorMessage_three}</label>
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
	</div>
</body>
</html>