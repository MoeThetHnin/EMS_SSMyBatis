<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
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
				style="font-size: 12px; display: block; margin: 0% 2%; text-align: right; text-decoration: none; color: #b00000;"
				href="<s:url action="empEdit" ><s:param name="emp_id">${employee.emp_id}</s:param></s:url>">編集</a>
		</div>

	</div>
</body>
</html>