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
	<div class="main_layout">
		<div class="client_header">
			<s:include value="/WEB-INF/common/header_logo.jsp"></s:include>
			<div class="info">
				<table>
					<tr>
						<td><img
							src="file/EmployeeProfilePicture/${employee.img_name}"></td>
						<td valign="top">
							<h5 class="m_color">株式会社会社アンスール</h5> <label class="key">名前：</label>
							<label class="value">${employee.name}</label> <label class="key">社員番号：</label>
							<label class="value">${employee.emp_id}</label> <label
							class="key">部門：</label> <label class="value">${employee.department}</label>
							<label class="key">役職：</label> <label class="value">${employee.post}(${employee.status })</label>
							<label class="key">メール：</label> <label class="value">${employee.email}</label>
						</td>
					</tr>
				</table>
				<a
					href="<s:url action="empEdit" >
							<s:param name="emp_id">${employee.emp_id}</s:param>
						</s:url>">
					編集 </a>
			</div>
		</div>
		<a
			style="display: block; clear: right; color: gray; font-size: 12px; text-decoration: none; width: 90%; margin: 0% auto; text-align: right;"
			href="<s:url action="empLogout"/>"> ログアウト</a>
		<div class="client_body">
			<h4>株式会社アンスール交通費支払登録</h4>
			<s:form action="tranPortInsert">
				<input type="hidden" name="transpo.emp_id"
					value="${employee.emp_id}" />
				<table class="table_one">
					<tr>
						<td>月日</td>
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
								<img class="img1" src="file/EkiOperatorLogo/${e.eo_logo_name}">
							</c:forEach></td>
					</tr>
					<tr>
						<td>使った線</td>
						<td><input type="text" name="transpo.t_line" /></td>
					</tr>
					<%-- <tr>
						<td>sdfas</td>
						<td colspan="3"><c:forEach var="employee" varStatus="s"
								items="${ekiLineListToei}">
								<input type="checkbox" name="transpo.t_line"
									value="${employee.el_line_logo_name}">
								<img style="width: 20px; height: 20px;"
									src="file/${employee.el_line_logo_name}">
							</c:forEach></td>
					</tr> --%>
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
							name="transpo.t_commutingType" value="1"> <img
							class="img2" alt=""
							src="<%=request.getContextPath()%>/image/katamachi.png"> <input
							type="radio" name="transpo.t_commutingType" value="2"> <img
							class="img2" alt=""
							src="<%=request.getContextPath()%>/image/oufuu.png"></td>
						<!-- <td><input type="text" name="transpo.t_kaeru_charge" /></td> -->
					<tr>
					<tr>
						<td>目標</td>
						<td colspan="3"><textarea name="transpo.t_purpose"
								style="width: 100%;"></textarea></td>
					</tr>
					<tr>
						<td>備考</td>
						<td colspan="3"><textarea name="transpo.t_remarks"
								style="width: 100%;"></textarea></td>
					</tr>
				</table>
				<div class="bottom">
					<s:submit value="インサート" style="text-align:right;" />
				</div>
			</s:form>
		</div>

		<div class="client_body">
			<h4>株式会社アンスール交通費支払申請書</h4>
			<table class="table_two">
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
			<table class="table_two">
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
			<div class="month">
				<%
					for (int i = 1; i < 13; i++) {
				%>
				<a href="<s:url action="getTranspoListByMonth" >
							<s:param name="p1">${employee.emp_id}</s:param>
							<s:param name="p2">0<%=i %></s:param>
						</s:url>">
						<label><%=i%>月</label>
				</a>
				<%
					}
				%>
			</div>

		</div>
	</div>
</body>
</html>