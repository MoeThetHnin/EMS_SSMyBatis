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
				<a href="<s:url action="empManage"/>">戻り</a>
			</div>

			<div class="content">
				<div class="edit_box">
					<h4>社員編集画面</h4>
					<div class="eb_content">
						<s:form action="empUpdateByAdmin">
							<label class="success_message">${message_one}</label>
							<label class="error_message">${errorMessage_one}</label>
							<s:hidden name="employee.id" />
							<s:textfield name="employee.emp_id" label="従業員ID" readonly="true" />
							<s:textfield name="employee.name" label="名前" />
							<s:select name="employee.department" list="departmentList"
								listKey="dep_name" listValue="dep_name" headerKey="-1"
								headerValue="ーーー部門選択ーーー" label="部門" />
							<s:select name="employee.post" list="postList" listKey="p_name"
								listValue="p_name" headerKey="-1" headerValue="ーーー役職選択ーーー "
								label="役職" />
							<s:radio name="employee.status" list="#{'本社':'本社','派遣会社':'派遣会社'}"
								label="本社 / 派遣会社" />
							<s:submit value="更新"
								style="width:25%; margin: 1% 1%; display:block; float:right;" />
						</s:form>
					</div>
				</div>

				<div class="edit_box">
					<h5 style="text-align: center;">パスワード変更</h5>
					<div class="eb_content">
						<s:form action="empPasswordChangeByAdmin">

							<s:hidden name="employee.emp_id" />
							<label class="success_message">${message_three}</label>
							<label class="error_message">${errorMessage_three}</label>
							<s:password name="employee.password" label="パスワード" />
							<s:submit value="変更"
								style="width:25%; margin: 1% 1%; display:block; float:right;" />

						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>