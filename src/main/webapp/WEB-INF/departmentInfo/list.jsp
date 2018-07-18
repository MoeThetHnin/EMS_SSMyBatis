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
						<li>社員管理</li>
					</a>
					<a href="<s:url action="deptManage"/>"
						style="text-decoration: none; color: gray;">
						<li class="current">部門管理と役職管理</li>
					</a>
					<a href="<s:url action="ekiManage"/>"
						style="text-decoration: none; color: gray;">
						<li>駅管理</li>
					</a>
					<a href="#" style="text-decoration: none; color: gray;">
						<li>OO管理</li>
					</a>
				</ul>
			</div>
			<div class="content">
				<div class="create_box">
					<div class="sub_nav">
						<ul>
							<a href="<s:url action="deptManage"/>">
								<li class="current">部門管理</li>
							</a>
							<a href="<s:url action="postManage"/>">
								<li>役職管理</li>
							</a>
						</ul>
					</div>
					<div class="cb_content">
						<s:form action="departmentInsert" method="POST"
							enctype="multipart/form-data">
							<label class="success_message">${message_one}</label>
							<label class="error_message">${errorMessage_one}</label>
							<s:textfield name="department.dep_number" label="部門番号"
								readonly="true" />
							<s:textfield name="department.dep_name" label="部門名"></s:textfield>
							<s:submit value="新規" style="width:100%;" />
						</s:form>
					</div>
				</div>
				<div class="list_layout">
					<h4>駅オペレーター</h4>
					<table>
						<tr class="th">
							<th>数</th>
							<th>部門</th>
							<th>部門番号</th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach var="e" varStatus="s" items="${departmentList}">
							<tr>
								<td style="text-align: center;">${s.index+1}</td>
								<td>${e.dep_name }</td>
								<td>${e.dep_number}</td>
								<td><a
									href="<s:url action="departmentEdit">
						<s:param name="id">${e.dep_id}</s:param>
						</s:url>">編集</a></td>
								<td><a
									href="<s:url action="departmentDelete">
						<s:param name="id">${e.dep_id}</s:param>
						</s:url>">削除</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>