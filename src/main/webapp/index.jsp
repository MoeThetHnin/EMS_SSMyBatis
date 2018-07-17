<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Refresh" content="7;URL=employee">
<style>
.loader {
	text-align: center;
	border: 30px solid #f3f3f3;
	border-radius: 50%;
	border-top: 30px solid #2bbbbb;
	width: 30%;
	height: 300px;
	margin: 5% auto 1% auto;
	padding: 3% 2% 0% 2%;
	animation:spin 7s linear infinite;
}

@keyframes spin { 
	0%{	transform: rotate(0deg); }
	100%{transform: rotate(360deg);}
}
h1, h2 {
	color: gray;
}
</style>
</head>
<body>
	<div class="loader">
		<div style="">
			<img alt="" src="<%=request.getContextPath()%>/image/ansurLogo.jpg"
				style="width: 70%; margin-top: 3%;">
			<h1>株式会社アンスール</h1>
			<h2 style="color: #2bbbbb;">従業員管理システム</h2>
		</div>
	</div>
</body>
</html>