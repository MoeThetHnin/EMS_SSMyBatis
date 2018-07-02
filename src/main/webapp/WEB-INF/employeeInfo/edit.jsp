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
				href="<s:url action="empManage.action"/>">戻り</a><a
				style="float: right; color: gray; text-decoration: none;"
				href="<s:url action="logout.action"/>"> ログアウト</a>
		</div>
		<div style="width: 80%; margin: 1% auto; clear: right;">
			<hr />
			<h4 style="text-align: center;">社員編集画面</h4>
		</div>
		<div
			style="width: 50%; margin: 1px auto; clear: right; border: 1px solid gray;">
			<s:form action="empUpdateByAdmin">
				<label
					style="display: block; color: blue; font-size: 12px; text-align: center; margin: 1%;">${message_one}</label>
				<label
					style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_one}</label>
				<table style="width: 60%; margin: 2% auto;">					
					<tr>
						<td><s:textfield name="employee.emp_id" label="従業員ID"
								readonly="true" /></td>
					</tr>
					<tr>
						<td><s:textfield name="employee.name" label="名前" /></td>
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
						<td><s:submit value="更新"
								style="width:25%; margin: 1% 1%; display:block; float:right;" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<h5 style="text-align: center;">パスワード変更</h5>
		<div
			style="width: 50%; margin: 1px auto; clear: right; border: 1px solid gray;">
			<s:form action="empPasswordChangeByAdmin">

				<s:hidden name="employee.emp_id" />
				<label
					style="display: block; color: blue; font-size: 12px; text-align: center; margin: 1%;">${message_three}</label>
				<label
					style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_three}</label>
				<table style="width: 60%; margin: 2% auto;">
					<tr>
						<td><s:password name="employee.password" label="パスワード" /></td>
					</tr>
					<tr>
						<td><s:submit value="変更"
								style="width:25%; margin: 1% 1%; display:block; float:right;" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>

	</div>





</body>
</html>