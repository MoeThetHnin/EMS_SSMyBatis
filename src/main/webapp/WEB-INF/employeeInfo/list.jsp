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
				href="<s:url action="home.action"/>"> ホーム</a> <a
				style="float: right; color: gray; text-decoration: none;"
				href="<s:url action="logout.action"/>"> ログアウト</a>
		</div>
		<div style="width: 80%; margin: 1% auto; clear: right;">
			<hr />
			<h4 style="text-align: center;">社員管理</h4>
		</div>
		<div style="width: 50%; margin: 1px auto; clear: right;">
			<s:form action="empInsert" enctype="multipart/form-data">
				<label
					style="display: block; color: red; font-size: 12px; text-align: center; margin: 1%;">${errorMessage_one}</label>
				<table style="border: 1px solid gray; width: 70%; margin: 2% auto;">
					<tr>
						<td><s:file name="employee.uploadImage" label="写真" /></td>
					</tr>
					<tr>
						<td><s:textfield name="employee.emp_id" label="従業員ID"
								readonly="true" /></td>
					</tr>
					<tr>
						<td><s:textfield name="employee.name" label="名前" /></td>
					</tr>
					<tr>
						<td><s:password name="employee.password" label="パスワード" /></td>

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
						<td><s:textfield name="employee.email" label="メール" /></td>
					</tr>


					<tr>
						<td><s:textfield name="employee.address" label="アドレス" /></td>
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

			<table style="margin: 2% auto; width: 95%;">
				<tr style="background-color: #f1f1f1;">
					<th>数</th>
					<th>写真</th>
					<th>従業員ID</th>
					<th>名前</th>
					<th>部門</th>
					<th>役職</th>
					<th>本社 / 発見</th>
					<th>メール</th>
					<th>住所</th>
					<th></th>
				</tr>
				<c:forEach var="e" varStatus="s" items="${employeeList}">
					<tr>
						<td style="text-align: center;">${s.index+1}</td>
						<td><a
							style="font-size: 12px; text-align: right; display: block;"
							href="<s:url action="empShow.action" >
										<s:param name="p1">${e.emp_id}</s:param>										
									</s:url>
							">
								<img style="width: 50px; height: 50px;"
								src="file/EmployeeProfilePicture/${e.img_name}"
								title="${e.name }">
						</a></td>
						<td>${e.emp_id }</td>
						<td>${e.name }</td>
						<td>${e.department }</td>
						<td>${e.post}</td>
						<td>${e.status}</td>
						<td><c:out value="${e.email }" /></td>
						<td><c:out value="${e.address }" /></td>
						<td><a
							style="font-size: 12px; text-align: right; display: block;"
							href="<s:url action="empEditByAdmin.action" >
										<s:param name="emp_id">${e.emp_id}</s:param>
										
									</s:url>
							">編集</a></td>
						<td><a
							href="<s:url action="empDelete.action">
						<s:param name="id">${e.id}</s:param>
						</s:url>">削除</a></td>

					</tr>
				</c:forEach>
			</table>

		</div>


	</div>





</body>
</html>