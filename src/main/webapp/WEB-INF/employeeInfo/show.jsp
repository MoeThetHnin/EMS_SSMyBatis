<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/jquery/jquery-ui.css">
<script src="<%=request.getContextPath()%>/jquery/jquery.js"></script>
<script src="<%=request.getContextPath()%>/jquery/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
</head>
<body>

	<div style="width: 100%;">
		<div style="width: 75%; margin: 1% auto;">
			<div
				style="width: 35%; height: 180px; float: left; border: 1px solid gray;">
				<table border="0">
					<tr>
						<td><img style="width: 150px; height: 150px;"
							src="file/EmployeeProfilePicture/${employee.img_name}"></td>
						<td valign="top">
							<h4>株式会社会社アンスール</h4> <label
							style="display: block; float: left; font-size: 10px; width: 30%;">名前：</label>
							<label style="display: block; font-size: 10px; width: 100%;">${employee.name}</label>
							<label
							style="display: block; float: left; font-size: 10px; width: 30%;">社員番号：</label><label
							style="display: block; font-size: 10px; width: 100%;">${employee.emp_id}</label>
							<label
							style="display: block; float: left; font-size: 10px; width: 30%;">部門：</label><label
							style="display: block; font-size: 10px; width: 100%;">${employee.department}</label>
							<label
							style="display: block; float: left; font-size: 10px; width: 30%;">役職：</label><label
							style="display: block; font-size: 10px; width: 100%;">${employee.post}(${employee.status })</label>
							<label
							style="display: block; float: left; font-size: 10px; width: 30%;">メール：</label><label
							style="display: block; font-size: 10px; width: 100%;">${employee.email}</label>
						</td>
					</tr>
				</table>
				<%-- <a style="font-size: 12px; text-align: right; display: block;"
					href="<s:url action="empEdit.action" ><s:param name="emp_id">${employee.emp_id}</s:param></s:url>">編集</a> --%>
			</div>
			<h3 style="text-align: center;">${employee.name}</h3>
			<h4 style="text-align: center;">${employee.emp_id}</h4>
			<h4 style="text-align: center;">${employee.department}</h4>
			<h4 style="text-align: center;">${employee.post}(${employee.status})</h4>


			<a style="float: right; color: gray; text-decoration: none;"
				href="<s:url action="logout.action"/>"> ログアウト</a>
		</div>

		<div
			style="width: 90%; margin: 1% auto; border: 1px solid gray; clear: right;">
			
		</div>
		<div
			style="width: 90%; margin: 1% auto; clear: right; text-align: right;">
			<a style="font-size: 12px; text-align: right; display: block;"
				href="<s:url action="createExcel.action" >
										<s:param name="emp_id">${employee.emp_id}</s:param>
										<s:param name="p2">${employee.currentDate}</s:param>
									</s:url>
							">Download
				Excel</a>

		</div>
		<div
			style="width: 90%; margin: 1% auto; border: 1px solid gray; clear: right;">
			<h4 style="text-align: center;">株式会社アンスール交通費支払申請書</h4>
			<table style="width: 98%; margin: 1% auto; border: 1px solid gray;">
				<tr>
					<td style="width: 15%;">申請年月日:</td>
					<td style="width: 30%;">${employee.currentDate}</td>
					<td style="width: 10%;"></td>
					<td style="width: 15%; text-align: right;">総チャージ金額：</td>
					<td style="width: 30%;">${employee.total_charge}</td>
				</tr>
				<tr>
					<td style="width: 15%;">申請者氏名：</td>
					<td style="width: 30%;">${employee.name}</td>
					<td style="width: 10%;"></td>
					<td style="width: 15%; text-align: right;">
						<!-- 残ったチャージ金額： -->
					</td>
					<td style="width: 30%;">
						<%-- ${employee.left_charge} --%>
					</td>
				</tr>
				<tr>
					<td style="width: 15%;"></td>
					<td style="width: 30%;"></td>
					<td style="width: 10%;"></td>
					<td style="width: 15%; text-align: right;">支払い申請金額：</td>
					<td style="width: 30%;">${employee.total_pay}</td>
				</tr>
			</table>
			<table style="width: 98%; margin: 1% auto; border: 1px solid gray;">
				<tr style="font-size: 12px; background-color: #d2d2d2;">
					<th style="width: 10%;">月日</th>
					<th style="width: 5%;">チャージ</th>
					<th style="width: 10%;">車種</th>
					<th style="width: 10%;">使った線</th>
					<th colspan="3">乗車範囲</th>
					<th style="width: 10%;">目的</th>
					<th style="width: 5%;">行く費</th>
					<th style="width: 5%;">帰る費</th>
					<th style="width: 40%;">備考</th>

				</tr>
				<c:forEach var="e" varStatus="s" items="${monthlyTranspoList}">
					<tr style="text-align: center; font-size: 12px;">
						<td style="width: 10%;"><fmt:formatDate value="${e.t_date}"
								pattern="dd-MM-yy" /></td>
						<td style="width: 5%;">${e.t_charge}</td>
						<td style="width: 10%;">${e.t_operator }</td>
						<td style="width: 10%;">${e.t_line }</td>
						<td style="width: 5%;">${e.t_kuru_eki}</td>
						<td style="width: 5%;">&lt;=&gt;</td>
						<td style="width: 5%;">${e.t_kaeru_eki}</td>
						<td style="width: 10%;">${e.t_purpose}</td>
						<td style="width: 5%;">${e.t_kuru_charge}</td>
						<td style="width: 5%;">${e.t_kaeru_charge }</td>
						<td style="width: 40%;">${e.t_remarks }</td>
					</tr>
				</c:forEach>
			</table>
			<div style="width: 98%; margin: 1%; text-align: center;">
				<%
					for (int i = 1; i < 13; i++) {
				%>
				<a style="font-size: 12px; display: block;"
					href="<s:url action="getTranspoListByMonth.action" >
										<s:param name="p1">${employee.emp_id}</s:param>
										<s:param name="p2">0<%=i %></s:param>
										<s:param name="p3">0</s:param>
									</s:url>
							">
					<label
					style="border: 1px solid gray; display: block; width: 6.1%; background-color: #d1d1d1; color: gray; float: left; margin: 1% 1% 1% 1%;"><%=i%>月</label>
				</a>
				<%
					}
				%>
			</div>

		</div>
	</div>
</body>
</html>