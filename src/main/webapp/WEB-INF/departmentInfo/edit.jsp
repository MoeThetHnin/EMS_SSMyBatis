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
				<a href="<s:url action="deptManage"/>">戻り</a>
			</div>
			<div class="content">
				<div class="edit_box">
					<h4>部門情報編集</h4>
					<div class="eb_content">
						<s:form action="departmentUpdate" method="POST">
							<label class="success_message">${message_one}</label>
							<label class="error_message">${errorMessage_one}</label>
							<s:hidden name="department.dep_id" />
							<s:textfield name="department.dep_number" label="部門番号"
								readonly="true" />
							<s:textfield name="department.dep_name" label="部門名"></s:textfield>
							<s:submit value="更新" style="width:100%;" />
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>



