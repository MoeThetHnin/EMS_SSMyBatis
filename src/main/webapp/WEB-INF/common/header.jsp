<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="logo">
	<img alt=""
		src="<%=request.getContextPath()%>/image/ansurLogoSmall.jpg">
	<h3>株式会社アンスール</h3>
</div>
<div class="function">
	<a href='<s:url action="adminLogout"/>'> ログアウト</a>
</div>
