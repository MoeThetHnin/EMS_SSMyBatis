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
			<div style="width: 35%; height: 180px; float: left;">
				<img alt="" src="<%=request.getContextPath()%>/image/ansurLogo.jpg"
					style="width: 100%;">
				<h2 style="text-align: center; margin: -10px; color: gray;">株式会社アンスール</h2>
			</div>
			<div style="border: 1px solid gray; float: right; width: 40%;">
				<table border="0" style="width: 90%; margin: 1% auto;">
					<tr>
						<td><img
							style="vertical-align: middle; width: 100px; height: 100px;"
							src="file/EmployeeProfilePicture/${employee.img_name}"></td>
						<td valign="top">
							<h5 style="margin: 0px; color: #2bbbbb;">株式会社会社アンスール</h5> <label
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
				<a
					style="font-size: 12px; display: block; margin: 0% 2%; text-align: right; text-decoration: none; color:#b00000;"
					href="<s:url action="empEdit" ><s:param name="emp_id">${employee.emp_id}</s:param></s:url>">編集</a>
			</div>

		</div>
		<a
			style="display:block; clear: right; color: gray; font-size: 12px; text-decoration: none; width:90%; margin: 0% auto; text-align: right;"
			href="<s:url action="empLogout"/>"> ログアウト</a>
		<div
			style="width: 90%; margin: 1% auto; border: 1px solid gray; clear: right; border-top: 20px solid #2bbbbb;">

			<h4 style="text-align: center;">株式会社アンスール交通費支払登録</h4>
			<s:form action="tranPortInsert">
				<input type="hidden" name="transpo.emp_id"
					value="${employee.emp_id}" />
				<table border="0" style="margin: 2% auto; width: 70%;">
					<tr>
						<td style="width: 20%">月日</td>
						<td><input type="text" name="transpo.t_date" id="datepicker" /></td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td>チャージ</td>
						<td><input type="text" name="transpo.t_charge" /></td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td>使ったオペレータ</td>
						<td colspan="3"><c:forEach var="e" varStatus="s"
								items="${ekiOperatorList}">
								<input type="checkbox" name="transpo.t_operator"
									value="${e.eo_name}">
								<img style="width: 20px; height: 20px;"
									src="file/EkiOperatorLogo/${e.eo_logo_name}">
							</c:forEach></td>
					</tr>
					<tr>
						<td>使った線</td>
						<td><input type="text" name="transpo.t_line" /></td>
					</tr>
					<tr>
						<td></td>
						<td colspan="3"><c:forEach var="employee" varStatus="s"
								items="${ekiLineListToei}">
								<input type="checkbox" name="transpo.t_line"
									value="${employee.el_line_logo_name}">
								<img style="width: 20px; height: 20px;"
									src="file/${employee.el_line_logo_name}">
							</c:forEach></td>
					</tr>
					<tr>
						<td>乗車範囲</td>
						<td style="width: 20%;"><input type="text"
							name="transpo.t_kuru_eki" /></td>
						<td style="text-align: left;">から</td>
						<td><input type="text" name="transpo.t_kaeru_eki" />まで</td>


					</tr>
					<tr>

						<td>通勤費</td>
						<td colspan="3"><input type="text"
							name="transpo.t_commutingFee" />円</td>
					<tr>
					<tr>

						<td></td>
						<td colspan="3"><input type="radio"
							name="transpo.t_commutingType" value="1"> <img alt=""
							src="<%=request.getContextPath()%>/image/oneway.jpg"
							style="width: 50px;"> <input type="radio"
							name="transpo.t_commutingType" value="2"> <img alt=""
							src="<%=request.getContextPath()%>/image/twoway.jpg"
							style="width: 50px;"></td>
						<!-- <td><input type="text" name="transpo.t_kaeru_charge" /></td> -->
					<tr>
					<tr>
						<td>目標</td>
						<!-- <td colspan="6"><input  style="width:100%;" type="text" name="transporation.t_purpose" /></td> -->
						<td colspan="3"><textarea name="transpo.t_purpose"
								style="width: 100%;"></textarea></td>
					</tr>
					<tr>
						<td>備考</td>
						<!-- <td colspan="6"><input style="width:100%;" type="text" name="transporation.t_remarks" /></td> -->
						<td colspan="3"><textarea name="transpo.t_remarks"
								style="width: 100%;"></textarea></td>
					</tr>
				</table>
				<div style="margin: 2% auto; width: 50%; text-align: right;">
					<s:submit value="インサート" style="text-align:right;" />
				</div>
			</s:form>
		</div>

		<div
			style="width: 90%; margin: 1% auto; border: 1px solid gray; clear: right; border-top: 20px solid #2bbbbb;">
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
					<th style="width: 13%;">車種</th>
					<th style="width: 12%;">使った線</th>
					<th colspan="3">乗車範囲</th>
					<th style="width: 15%;">目的</th>
					<th style="width: 5%;">通勤費</th>
					<th style="width: 35%;">備考</th>

				</tr>
				<c:forEach var="e" varStatus="s" items="${monthlyTranspoList}">
					<tr style="text-align: center; font-size: 12px;">
						<td style="width: 10%;"><fmt:formatDate value="${e.t_date}"
								pattern="dd-MM-yy" /></td>
						<td style="width: 5%;">${e.t_charge}</td>
						<td style="width: 13%;">${e.t_operator }</td>
						<td style="width: 12%;">${e.t_line }</td>
						<td style="width: 5%;">${e.t_kuru_eki}</td>
						<td style="width: 5%;"><c:if
								test="${e.t_commutingType == 1 }">
								=&gt;
							</c:if> <c:if test="${e.t_commutingType == 2 }">
								&lt;=&gt;
							</c:if></td>
						<td style="width: 5%;">${e.t_kaeru_eki}</td>
						<td style="width: 15%;">${e.t_purpose}</td>
						<td style="width: 5%;">${e.t_commutingFee}</td>

						<td style="width: 35%;">${e.t_remarks }</td>
					</tr>
				</c:forEach>
			</table>
			<div style="width: 98%; margin: 1%; text-align: center;">
				<%
					for (int i = 1; i < 13; i++) {
				%>
				<a style="font-size: 12px; display: block; cursor: pointer;"
					href="<s:url action="getTranspoListByMonth" >
										<s:param name="p1">${employee.emp_id}</s:param>
										<s:param name="p2">0<%=i %></s:param>
									</s:url>
							">
					<label
					style="cursor: pointer; border: 1px solid gray; display: block; width: 6.1%; background-color: #d1d1d1; color: gray; float: left; margin: 1% 1% 1% 1%;"><%=i%>月</label>
				</a>
				<%
					}
				%>
			</div>

		</div>
	</div>
</body>
</html>