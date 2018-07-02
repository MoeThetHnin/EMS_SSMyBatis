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
			<a style="float: left; color: gray; text-decoration: none;"
				href="<s:url action="ekiOperatorManage.action"/>"> 戻る</a> <a
				style="float: right; color: gray; text-decoration: none;"
				href="<s:url action="logout.action"/>"> ログアウト</a>
		</div>

		<div style="width: 80%; margin: 1% auto; clear: left;"></div>
		<div style="width: 80%; margin: 1px auto; clear: right;">

			<div
				style="width: 30%; margin: 1% auto; padding: 1%; border: 1px solid gray; text-align: center;">
				<s:form action="ekiOperatorUpdate" method="POST">
					
					<label
						style="display: block; color: blue; font-size: 12px; text-align: center; margin: 1%;">${message_one}</label>
					<label
						style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_one}</label>
						<s:hidden name="ekiOperator.eo_id"/>
					<table style="width: 100%; margin: 2% auto;">

						<tr>
							<td><s:textfield name="ekiOperator.eo_number"
									label="オペレーター番号" readonly="true" /></td>
						</tr>
						<tr>
							<td><s:textfield name="ekiOperator.eo_name" label="オペレーター名"></s:textfield>
							</td>
						</tr>
						<tr>
							<td><s:submit value="更新" style="width:25%; float:right;" /></td>
						</tr>
					</table>
				</s:form>
			</div>
			<div
				style="width: 30%; margin: 1% auto; padding: 1%; clear: right; border: 1px solid gray; text-align: center;">
				<s:form action="ekiOperatorPhotoChange" method="POST"
					enctype="multipart/form-data">
					<label
						style="display: block; color: blue; font-size: 12px; text-align: center; margin: 1%;">${message_two}</label>
					<label
						style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_two}</label>
							<s:hidden name="ekiOperator.eo_id"/>
					<img style="width: 50px; height: 50px;"
						src="file/EkiOperatorLogo/${ekiOperator.eo_logo_name }">
					<s:file name="ekiOperator.uploadImage" />
					<s:submit value="更新" style="width:25%; float:right;" />
				</s:form>
			</div>
		</div>
	</div>





</body>
</html>