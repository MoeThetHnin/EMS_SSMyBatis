<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="header">
			<s:include value="/WEB-INF/common/header.jsp"></s:include>
		</div>
		<div class="body">
			<div class="nav">
				<ul>
					<a href="<s:url action="empManage"/>">
						<li class="current">社員管理</li>
					</a>
					<a href="<s:url action="deptManage"/>">
						<li>部門管理と役職管理</li>
					</a>
					<a href="<s:url action="ekiManage"/>">
						<li>駅管理</li>
					</a>
					<a href="#" style="text-decoration: none; color: gray;">
						<li>OO管理</li>
					</a>
				</ul>
			</div>
			<div class="content">
				<div class="create_box">
					<h4>社員管理</h4>
					<div class="cb_content">
						<s:form action="insert" enctype="multipart/form-data">
							<label class="error_message">${errorMessage_one}</label>
							<s:file name="employee.uploadImage" label="写真" />
							<s:textfield name="employee.emp_id" label="従業員ID" readonly="true" />
							<s:textfield name="employee.name" label="名前" />
							<s:password name="employee.password" label="パスワード" />
							<s:select name="employee.department" list="departmentList"
								listKey="dep_name" listValue="dep_name" headerKey="-1"
								headerValue="ーーー部門選択ーーー" label="部門" />
							<s:select name="employee.post" list="postList" listKey="p_name"
								listValue="p_name" headerKey="-1" headerValue="ーーー役職選択ーーー "
								label="役職" />
							<s:radio name="employee.status" list="#{'本社':'本社','派遣会社':'派遣会社'}"
								label="本社 / 派遣会社" />
							<s:textfield name="employee.email" label="メール" />
							<s:textfield name="employee.address" label="アドレス" />
							<s:submit value="新規" style="width:100%;" />
						</s:form>
					</div>
				</div>
				<div class="list_layout">
					<table>
						<tr class="th">
							<th>数</th>
							<th>写真</th>
							<th>従業員ID</th>
							<th>名前</th>
							<th>部門</th>
							<th>役職</th>
							<th>本社 / 発見</th>
							<th>メール</th>
							<th>住所</th>
							<th colspan="2"></th>
						</tr>
						<c:forEach var="e" varStatus="s" items="${employeeList}">
							<tr>
								<td style="text-align: center;">${s.index+1}</td>
								<td><a
									href="<s:url action="empShow.action" >
												<s:param name="p1">${e.emp_id}</s:param>										
											</s:url>">
										<img src="file/EmployeeProfilePicture/${e.img_name}"
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
									href="<s:url action="empEditByAdmin" >
												<s:param name="emp_id">${e.emp_id}</s:param>										
											</s:url>">
										編集 </a></td>
								<td><a
									href="<s:url action="empDelete">
												<s:param name="id">${e.id}</s:param>
											</s:url>">
										削除 </a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>