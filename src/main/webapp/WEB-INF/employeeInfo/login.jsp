<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
</head>
<body>
	<div class="main_layout">
		<div class="login_header">
			<img alt="Ansur"
				src="<%=request.getContextPath()%>/image/ansurLogo.jpg">
			<h2>株式会社アンスール</h2>
		</div>
		<div class="login_body">
			<s:form action="login" namespace="/employee">
				<label class="error_message">${errorMessage_one}</label>
				<table>
					<tr>
						<td><s:textfield name="employee.emp_id" label="従業員 ID"></s:textfield></td>
					</tr>
					<tr>
						<td><s:password name="employee.password" label="パスワード"></s:password></td>
					</tr>
					<tr>
						<td><s:submit value="ログイン" style="width:100%; height:25px;"></s:submit></td>
					</tr>
				</table>
			</s:form>
		</div>
	</div>




</body>
</html>