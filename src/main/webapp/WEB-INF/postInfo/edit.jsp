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
				href="<s:url action="postManage.action"/>"> 戻る</a> <a
				style="float: right; color: gray; text-decoration: none;"
				href="<s:url action="logout.action"/>"> ログアウト</a>
		</div>

		<div style="width: 80%; margin: 1% auto; clear: left;"></div>
		<div style="width: 80%; margin: 1px auto; clear: right;">
			<h4 style="text-align: center;">役職情報編集</h4>
			<div
				style="width: 30%; margin: 1% auto; padding: 1%; border: 1px solid gray; text-align: center;">
				<s:form action="postUpdate" method="POST">

					<label
						style="display: block; color: blue; font-size: 12px; text-align: center; margin: 1%;">${message_one}</label>
					<label
						style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_one}</label>
					<table style="width: 100%; margin: 2% auto;">
						<s:hidden name="post.p_id" />
						<tr>
							<td><s:textfield name="post.p_number" label="役職番号"
									readonly="true" /></td>
						</tr>
						<tr>
							<td><s:textfield name="post.p_name" label="役職名"></s:textfield>
							</td>
						</tr>
						<tr>
							<td><s:submit value="更新" style="width:100%;" /></td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>




	</div>

</body>
</html>



