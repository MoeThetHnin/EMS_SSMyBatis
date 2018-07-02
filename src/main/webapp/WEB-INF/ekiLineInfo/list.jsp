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
			<a style="float: left; color:gray; text-decoration: none;" href="<s:url action="home.action"/>"> ホーム</a>
			<a style="float: right; color: gray; text-decoration: none;" href="<s:url action="logout.action"/>">
				ログアウト</a>
		</div>
		<div
			style="display: block; clear: right; width: 50%; margin: 1% auto;">
			<a style="text-decoration: none;"
				href="<s:url action="ekiOperatorManage.action"/>">
				<div
					style="float: left; width: 30%; margin: 1% 1% 1% 2%; border: 1px solid gray; text-align: center; color: gray; background-color: #f1f1f1;">
					駅オペレーター管理</div>
			</a> <a style="text-decoration: none;"
				href="<s:url action="ekiLineManage.action"/>">
				<div
					style="float: left; margin: 1% 1%; width: 31%; border: 1px solid gray; text-align: center; color: white; background-color: gray;">
					駅線管理</div>
			</a> <a style="text-decoration: none;"
				href="#">
				<div
					style="float: left; margin: 1% 1%; width: 30%; border: 1px solid gray; text-align: center; color: gray; background-color: #f1f1f1;">
					OO管理</div>
			</a>

			<%-- <label style=" dispaly:block; width:100%; border: 1px solid gray;">sdafdsafsasf 駅オペレーター管理</label></a> --%>
			<%--  駅線管理</a>
			</a> --%>
		</div>
		<div style="width: 80%; margin: 1% auto; clear: right;"></div>
		<div style="width: 80%; margin: 1px auto; clear: right;">
			<s:form action="ekiLineInsert" method="POST"
				enctype="multipart/form-data">
				<table style="border: 1px solid gray; width: 40%; margin: 2% auto;">

					<tr>
						<td><s:textfield name="ekiLine.el_line" label="線名" /></td>
					</tr>
					<tr>
						<td><s:textfield name="ekiLine.el_number" label="線番号"></s:textfield>
						</td>
					</tr>
					<tr>
						<td><s:file name="ekiLine.uploadImage" label="線のロゴ" /></td>
					</tr>
					<tr>
						<td><s:select list="ekiOperatorList" label="オペレータ"
								name="ekiLine.eo_number" headerValue="オペレータを選択する" headerKey="-1"
								listKey="eo_number" listValue="eo_name"></s:select></td>
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
		<div style="width: 75%; margin: 1px auto;">
			<div style="text-align: center;">
				<c:forEach var="e" varStatus="s" items="${ekiLineListDistinct}">
					<img style="width: 40px; height: 40px;"
						src="file/EkiOperatorLogo/${e.eo_logo_name}" alt="${e.eo_name }" title="${e.eo_name }">
					
				</c:forEach>
				
				
				
			</div>
			<c:forEach var="e" varStatus="s" items="${ekiLineList}">
				<table border="0" style="width:70%; margin:1% auto;">
					<tr>
						
						<td style="width:10%; text-align: center;">
							<img style="width: 30px; height: 30px;"
						src="file/EkiLineLogo/${e.el_line_logo_name}" title="${e.el_line}" alt="${e.el_line}">
						</td>
						<td style="width:30%">
							${e.el_line}
						</td>
						<td style="width:50%">
							Line ${e.el_number}
						</td>
						<td style="width:10%">
							<img style="width: 30px; height: 30px;"
						src="file/EkiOperatorLogo/${e.eo_logo_name}" title="${e.eo_name}" alt="${e.eo_name}">
						</td>
						<td><a
							href="<s:url action="ekiLineEdit.action">
						<s:param name="id">${e.el_id}</s:param>
						</s:url>">編集</a></td>
						<td><a
							href="<s:url action="ekiLineDelete.action">
						<s:param name="id">${e.el_id}</s:param>
						</s:url>">削除</a></td>
					</tr>
				</table>
				
			</c:forEach>

			

		</div>


	</div>





</body>
</html>