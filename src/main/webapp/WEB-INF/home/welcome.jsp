<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	
	<div style="width: 100%;">
		<div style="text-align: center;">
			<h2>株式会社アンスール</h2>
			<h3>従業員管理システムアドミン側</h3>
		</div>
		<div style="width:80%; margin:1% auto;">
			<a style="float:right; color:gray; text-decoration: none;" href="<s:url action="logout.action"/>"> ログアウト</a>
		</div>
		<div style="width: 80%; margin: 1% auto; border: 1px solid gray; clear:right;">
			<a href="<s:url action="empManage.action"/>"
				style="text-decoration: none; color:gray;">
				<div style="width: 21%; margin: 1% 1% 1% 3%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
					<h4>社員管理</h4>
				</div>
			</a>
			<a href="<s:url action="departmentManage.action"/>"
				style="text-decoration: none; color:gray;">
			<div
				style="width: 21%; margin: 1% 1% 1% 2%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
				<h4>部門管理と役職管理</h4>
			</div>
			</a>
			
			<a href="<s:url action="ekiManage.action"/>"
				style="text-decoration: none; color:gray;">
			<div
				style="width: 21%; margin: 1% 1% 1% 2%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
				<h4>駅管理</h4>
			</div>
			</a>
			<a href="#"
				style="text-decoration: none; color:gray;">
			<div
				style="width: 21%; margin: 1% 1% 1% 2%; border: 1px solid gray; background-color: #f1f1f1; text-align: center; float: left;">
				<h4>OO管理</h4>
			</div>
			</a>
			
			
		</div>
	</div>

	<p>
		<s:property value="result" />
	</p>
</body>
</html>