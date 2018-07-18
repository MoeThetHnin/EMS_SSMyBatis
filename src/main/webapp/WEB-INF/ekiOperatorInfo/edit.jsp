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
				<a href="<s:url action="ekiManage"/>">戻り</a>
			</div>

			<div class="content">
				<div class="edit_box">
					<h4>オペレータ情報編集</h4>
					<div class="eb_content">
						<s:form action="ekiOperatorUpdate" method="POST">
							<label class="success_message">${message_one}</label>
							<label class="error_message">${errorMessage_one}</label>
							<s:hidden name="ekiOperator.eo_id" />
							<s:textfield name="ekiOperator.eo_number" label="オペレーター番号"
								readonly="true" />
							<s:textfield name="ekiOperator.eo_name" label="オペレーター名"></s:textfield>
							<s:submit value="更新" style="width:25%; float:right;" />
						</s:form>
					</div>
				</div>
				<div class="edit_box">
					<h4>オペレータロゴ編集</h4>
					<div class="eb_content">
						<s:form action="ekiOperatorPhotoChange" method="POST"
							enctype="multipart/form-data">
							<label class="success_message">${message_two}</label>
							<label class="error_message">${errorMessage_two}</label>
							<s:hidden name="ekiOperator.eo_id" />
							<tr>
								<td colspan="2" align="center"><img style="width: 50px; height: 50px;"
									src="file/EkiOperatorLogo/${ekiOperator.eo_logo_name }">
								</td>
							</tr>
							<s:file name="ekiOperator.uploadImage" />
							<s:submit value="更新" style="width:25%; float:right;" />
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>