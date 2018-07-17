<%@ taglib prefix="s" uri="/struts-tags"%>
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
			<!-- <h2>株式会社アンスール</h2>
			<h3>従業員管理システムアドミン側</h3> -->
			<img alt="" src="<%=request.getContextPath()%>/image/ansurLogo.jpg"
				style="width: 30%; margin-top: 3%;">
			<h2 style="margin: -10px; color: gray;">株式会社アンスール</h2>
		</div>
		<div
			style="width: 40%; border: 1px solid gray; margin: 5% auto; border-top: 30px solid #2bbbbb; border-radius:10px 10px 0% 0%;">
			<h3 style="text-align: center; margin-bottom: -10px; color: gray;">従業員管理システムアドミン側</h3>
			<s:form action="login" namespace="/admin">
				<label
					style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_one}</label>
				<table style="width: 50%; margin: 3% auto;">
					<tr>
						<td><s:textfield name="admin.admin_id" label="従業員 ID"></s:textfield></td>
					</tr>
					<tr>
						<td><s:password name="admin.admin_password" label="パスワード"></s:password></td>
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