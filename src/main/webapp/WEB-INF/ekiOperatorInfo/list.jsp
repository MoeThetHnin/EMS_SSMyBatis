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
					<a href="<s:url action="deptManage"/>">
						<li>部門管理と役職管理</li>
					</a>
					<a href="<s:url action="ekiManage"/>">
						<li class="current">駅管理</li>
					</a>
					<a href="#">
						<li>OO管理</li>
					</a>
				</ul>
			</div>
			<div class="content">
				<div class="create_box">
					<%-- <div class="sub_nav">
						<ul>
							<a href="<s:url action="ekiOperatorManage"/>">
								<li>部門管理</li>
							</a>
							<a href="<s:url action="postManage"/>">
								<li class="current">役職管理</li>
							</a>
						</ul>
					</div> --%>

					<div class="cb_content">
						<s:form action="ekiOperatorInsert" method="POST"
							enctype="multipart/form-data">
							<label class="success_message">${message_one}</label>
							<label class="error_message">${errorMessage_one}</label>
							<s:textfield name="ekiOperator.eo_number" label="オペレーター番号"
								readonly="true" />
							<s:textfield name="ekiOperator.eo_name" label="オペレーター名"></s:textfield>
							<s:file name="ekiOperator.uploadImage" label="オペレーターロゴ" />
							<s:submit value="新規" style="width:100%;" />
						</s:form>
					</div>

				</div>
				<div class="list_layout">
					<h4>駅オペレーター</h4>
					<table>
						<tr class="th">
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
									href="<s:url action="ekiOperatorEdit">
						<s:param name="id">${e.eo_id}</s:param>
						</s:url>">編集</a></td>
								<td><a
									href="<s:url action="ekiOperatorDelete">
						<s:param name="id">${e.eo_id}</s:param>
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