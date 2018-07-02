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
			<a style="float: left; color: gray; text-decoration: none;" href="<s:url action="home.action"/>"> ホーム</a>

			<a style="float: right; color: gray; text-decoration: none;" href="<s:url action="logout.action"/>">
				ログアウト</a>
		</div>
		<div
			style="display: block; clear: right; width: 50%; margin: 1% auto;">
			<a style="text-decoration: none;"
				href="<s:url action="ekiOperatorManage.action"/>">
				<div
					style="float: left; width: 30%; margin: 1% 1% 1% 2%; border: 1px solid gray; text-align: center; color:white; background-color: gray;">
					駅オペレーター管理</div>
			</a> <%-- <a style="text-decoration: none;"
				href="<s:url action="ekiLineManage.action"/>"> --%>
				 <a style="text-decoration: none;"
				href="#">
				<div
					style="float: left; margin: 1% 1%; width: 31%; border: 1px solid gray; text-align: center;color: gray; background-color: #f1f1f1;">
					OO管理</div>
			</a> <a style="text-decoration: none;"
				href="#">
				<div
					style="float: left; margin: 1% 1%; width: 30%; border: 1px solid gray; text-align: center;color:gray; background-color: #f1f1f1;">
					OO管理</div>
			</a>

			<%-- <label style=" dispaly:block; width:100%; border: 1px solid gray;">sdafdsafsasf 駅オペレーター管理</label></a> --%>
			<%--  駅線管理</a>
			</a> --%>
		</div>
		<div style="width: 80%; margin: 1% auto; clear: left;">
			
			
		</div>
		<div style="width: 80%; margin: 1px auto; clear: right;">
			<s:form action="ekiOperatorInsert" method="POST"
				enctype="multipart/form-data">
				<label
					style="display: block; color: blue; font-size: 12px; text-align: center; margin: 1%;">${message_one}</label>
				<label
					style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_one}</label>
				<table style="border: 1px solid gray; width: 40%; margin: 2% auto;">

					<tr>
						<td><s:textfield name="ekiOperator.eo_number" label="オペレーター番号" readonly="true" /></td>
					</tr>
					<tr>
						<td><s:textfield name="ekiOperator.eo_name" label="オペレーター名"></s:textfield>
						</td>
					</tr>
					<tr>
						<td><s:file name="ekiOperator.uploadImage" label="オペレーターロゴ" /></td>
					</tr>					
					<tr>
						<td><s:submit value="新規" style="width:100%;" /></td>
					</tr>
				</table>
			</s:form>
		</div>
		
		<div style="width: 75%; margin: 2% auto; clear: right;">
			<hr />

		</div>
		<div style="width: 75%; margin: 1px auto; text-align: center;">
			<h4>駅オペレーター</h4>

			<table style="margin: 2% auto; width: 95%;">
				<tr style="background-color: #f1f1f1;">
					<th>数</th>
					<th>オペレーターロゴ</th>					
					<th>オペレータ名</th>
					<th>オペレータ番号</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="e" varStatus="s" items="${ekiOperatorList}">
					<tr>
						<td style="text-align: center;">${s.index+1}</td>
						<td><img style="width: 50px; height: 50px;"
							src="file/EkiOperatorLogo/${e.eo_logo_name }"></td>						
						<td>${e.eo_name }</td>
						<td>${e.eo_number}</td>
						<td><a
							href="<s:url action="ekiOperatorEdit.action">
						<s:param name="id">${e.eo_id}</s:param>
						</s:url>">編集</a></td>
						<td><a
							href="<s:url action="ekiOperatorDelete.action">
						<s:param name="id">${e.eo_id}</s:param>
						</s:url>">削除</a></td>
					</tr>
				</c:forEach>
			</table>		

		</div>


	</div>





</body>
</html>